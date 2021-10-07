package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;


import entities.CashOut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;


import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "payOutServlet", value = { "/add-payout","/get-payout" ,"/delete-payout"})
public class PayOutServlet extends HttpServlet {
    SessionFactory sf = HibernateUtil.getSessionFactory();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int cashOid = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        try{
        String obj = req.getParameter("obj");
        Gson gson = new Gson();
        CashOut cout = gson.fromJson(obj, CashOut.class);
        cout.setPayOut_date(new Date());
        sesi.save(cout);
        tr.commit();
        sesi.close();
        cashOid = 1;
        }
        catch (Exception ex){

            System.out.println("Payout add error : " + ex);
        }
        finally {
            sesi.close();
        }
        resp.setContentType("application/json");
        resp.getWriter().write( "" +cashOid );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session sesi = sf.openSession();
        Gson gson = new Gson();
        List<Object[]> payOut = sesi.createNativeQuery("SELECT * FROM cashout")
                .getResultList();

        String stJson = gson.toJson(payOut);
        resp.setContentType("application/json");
        resp.getWriter().write(stJson);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int payOutId = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        try {
            int cashOut_id = Integer.parseInt(req.getParameter("cashOut_id"));
            CashOut cashOut = sesi.find(CashOut.class, cashOut_id);
            sesi.delete(cashOut);
            tr.commit();
             payOutId = cashOut.getCashOut_id();
        }
        catch (Exception ex) {
            System.out.println("PayOut delete error : " + ex);
        }
        finally {
            sesi.close();
        }
        resp.setContentType("application/json");
        resp.getWriter().write( ""+ payOutId );
    }

}
