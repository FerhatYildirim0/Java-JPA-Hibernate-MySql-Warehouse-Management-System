package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entities.CashIn;
import entities.Orders;
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
import java.util.Date;
import java.util.List;

@WebServlet(name = "payinServlet", value = {"/add-payin", "/delete-payin", "/get-payin"})
public class PayInServlet extends HttpServlet {
    SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        String obj = req.getParameter("obj");
        Gson gson = new Gson();
        CashIn cashIn = gson.fromJson(obj, CashIn.class);
        System.out.println(cashIn);
        try {
            int order_id = cashIn.getOrder_id();
            cashIn.setCashIn_date(new Date());
            Orders orders = (Orders) sesi.createQuery("from Orders where order_id=?1")
                    .setParameter(1, order_id)
                    .getSingleResult();

            cashIn.setOrder(orders);
            cashIn.setCash_status(1);
            sesi.save(cashIn);
            tr.commit();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } finally {
            sesi.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session sesi= sf.openSession();
        Gson gson=new Gson();
        List<Object[]> payInList= sesi.createNativeQuery("SELECT cashin.cash_id, customer.cu_name,customer.cu_surname, orders.fis_no,cashin.payInTotal,customer.cu_id,cashin.payInDetail FROM cashin INNER JOIN orders on cashin.order_id =orders.order_id INNER JOIN customer on customer.cu_id=orders.customer_cu_id ")
                .getResultList();

        String stJson = gson.toJson(payInList);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int payInId=0;
        Session sesi=sf.openSession();
        Transaction tr=sesi.beginTransaction();
        try{
            int cash_id = Integer.parseInt(req.getParameter("cash_id"));
            CashIn cashIn= sesi.find(CashIn.class,cash_id);
            sesi.delete(cashIn);
            tr.commit();

            payInId=1;
        }catch (Exception e){
            System.out.println("Payin Delete Error " + e );

        }finally {
            sesi.close();
        }
        resp.setContentType("application/json");
        resp.getWriter().write( ""+payInId );
    }
    }
