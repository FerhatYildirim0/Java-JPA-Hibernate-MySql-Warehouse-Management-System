package servlets;
import com.google.gson.Gson;
import entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.DBUtil;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "orderServlet" , value = {"/add-order-servlet","/order-list","/delete-order"})
public class OrderServlet extends HttpServlet{
    SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cid=Integer.parseInt(req.getParameter("cid"));
        Session sesi =sf.openSession();

        List<Object[]> orders = sesi.createNativeQuery("SELECT cus.cu_id, cus.cu_name, cus.cu_surname, ord.fis_no, pro.pro_title, ord.order_size, pro.pro_sale_price ,ord.order_id, ord.total FROM products as pro INNER JOIN orders as ord on pro.pro_id=ord.products_pro_id INNER JOIN customer as cus ON cus.cu_id=ord.customer_cu_id WHERE cu_id=?1 and ord.status=1 ")
                .setParameter(1,cid)
                .getResultList();
        sesi.close();

        Gson gson=new Gson();
        String stJson= gson.toJson(orders);
        resp.setContentType("application/json");
        resp.getWriter().write(stJson);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session sesi=sf.openSession();
        int pid=0;

        try {
            String orderObj=req.getParameter("orderObj");
            Gson gson = new Gson();
            OSObject order = gson.fromJson(orderObj, OSObject.class);

            DBUtil dbUtil = new DBUtil();

            int pidx = Integer.parseInt(order.getProduct());
            int cid = Integer.parseInt(order.getCustomer());

            Products px= dbUtil.singleProduct(pidx);
            Customer cx=dbUtil.singleCustomer(cid);

            dbUtil.addOrder(px,cx, order.getFis_num(), new Date(),order.getOr_size());
            System.out.println(order);

            pid=1;
        }catch (Exception e){
            System.out.println("Order Insert Error"+e);
        }

        resp.setContentType("application/json");
        resp.getWriter().write( "" +pid );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int order_id= Integer.parseInt(req.getParameter("order_id"));
        Session sesi= sf.openSession();
        Transaction tr=sesi.beginTransaction();

        Orders deletedOrder= (Orders) sesi.createQuery("from Orders where order_id=?1 ")
                .setParameter(1,order_id)
                .getSingleResult();
        sesi.delete(deletedOrder);
        tr.commit();
        sesi.close();
    }
}
