package servlets;

import com.google.gson.Gson;
import entities.Orders;
import entities.SaverObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="osChanger", value="/osChange")
public class OSChangerServlet extends HttpServlet {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int cid=Integer.parseInt(req.getParameter("cid"));
        String saveObject = req.getParameter("saveObj");
        Gson gson=new Gson();
        SaverObject saveObj = gson.fromJson(saveObject, SaverObject.class);
        System.out.println("----"+saveObj.getPrice()+"-"+saveObj.getFis_obj_no()+"-"+saveObj.getOr_obj_id());
        Session sesi =sf.openSession();



        List<Orders>  statusList=sesi.createQuery("from Orders WHERE fis_no=?1")
                .setParameter(1,cid).getResultList();


        statusList.forEach(item->{
            Transaction tr1= sesi.beginTransaction();
            System.out.println( item.getFis_no()+"--"+item.getProducts().getPro_title()+"--"+ item.getOrder_id());
            int total= item.getTotal();
            int saleprice=item.getProducts().getPro_sale_price();
            int buyAmounth=saveObj.getPrice();
            int newTotal=saleprice*buyAmounth;
            newTotal=total-newTotal;
            item.setTotal(newTotal);
            int status =2;
            item.setStatus(status);
            tr1.commit();


        });

        sesi.close();
        resp.setContentType("application/json");
        resp.getWriter().write( "" +cid );

    }



}
