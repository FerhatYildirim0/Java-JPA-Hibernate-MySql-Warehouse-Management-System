package servlets;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "dashboardQeryServlet", value = "/dashboard-servlet")
public class DashboardQueryServlet extends HttpServlet {
    SessionFactory sf= HibernateUtil.getSessionFactory();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        Session sesi = sf.openSession();

        List<Object[]> ord = sesi.createNativeQuery("SELECT ord.order_id,ord.fis_no,cus.cu_name,cus.cu_surname,pro.pro_tax_status,ord.total\n" +
                        "FROM orders as ord \n" +
                        "INNER JOIN customer as cus \n" +
                        "ON cus.cu_id=ord.customer_cu_id INNER JOIN product as pro on ord.product_pid=pro.pro_id\n" +
                        "ORDER BY ord.or_id DESC LIMIT 0,5")

                .getResultList();
        sesi.close();

        String stJson = gson.toJson(ord);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );

    }
}
