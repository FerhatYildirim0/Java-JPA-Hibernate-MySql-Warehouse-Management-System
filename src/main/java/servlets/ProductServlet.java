package servlets;

import com.google.gson.Gson;
import entities.Customer;
import entities.Products;
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

@WebServlet(name="productServlet", value = {"/product-insert", "/product-update","/product-delete"})
public class ProductServlet extends HttpServlet {

    SessionFactory sf = HibernateUtil.getSessionFactory();

    // Product Insert
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        try {
            //buadaki pro_obj key olarak product.js de add form'da data karşısındaki kısmı temsil ediyor
             //pro_obj burada string türde
            String pro_obj = req.getParameter("pro_obj");
            Gson gson = new Gson();
            Products product = gson.fromJson(pro_obj, Products.class);
            //pro_obj artık nesne. Gson json ı products sınıfından bir nesneye dönüştürdü
            sesi.saveOrUpdate(product);
            tr.commit();
            sesi.close();
            pid= 1;
        }catch ( Exception ex) {
            System.err.println("Save OR Update Error : " + ex);
        }finally {
            sesi.close();
        }
        resp.setContentType("application/json");
        resp.getWriter().write( "" + pid );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        List<Products> ls = sesi.createQuery("from Products ").getResultList();
        sesi.close();

        String stJson = gson.toJson(ls);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int return_did = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
         try{
             int pro_id = Integer.parseInt(req.getParameter("pro_id"));
              Products product = sesi.load(Products.class, pro_id);
              sesi.delete(product);
              tr.commit();
             return_did = product.getPro_id();

         }
         catch (Exception err){
             System.out.println("Ürün silme işlemi sırasında bir hata meydana geldi" + err);

         }
         finally {
             sesi.close();
         }
         resp.setContentType("application/json");
         resp.getWriter().write(""+ return_did);
    }
}
