package utils;

import entities.Admin;
import entities.Customer;
import entities.Orders;
import entities.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class DBUtil {
    SessionFactory sf = HibernateUtil.getSessionFactory();


    public boolean login(String email, String password, String rememberMe, HttpServletRequest req, HttpServletResponse resp){



     boolean status= false;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();

        try {

            List<Admin> adminList = sesi.createQuery("from Admin where email= :email and  password= :password")
                    .setParameter("email",email)
                    .setParameter("password",Util.MD5(password))
                    .getResultList();
            System.out.println("MD5 lı olması gereken şifre : " + password);
            System.out.println(" Admin listesi :    " + adminList);
              if(adminList.size() != 0){
                  status = true;
              adminList.forEach(item->{
                  int id = item.getId();
                  String name = item.getName();
                    req.getSession().setAttribute("id",id);
                    req.getSession().setAttribute("email",email);
                    req.getSession().setAttribute("name",name);

                     if(rememberMe != null && rememberMe.equals("on") ){
                         name = name.replaceAll(" ", "_");

                         String vales = id+"_"+name;

                         Cookie cookie = new Cookie("admin" , vales);
                         cookie.setMaxAge( 60*60 );
                         resp.addCookie(cookie);

                     }
              });
              }
        }
        catch (Exception ex)
        {
            System.out.println("Login işlemi başarısız : " + ex);
        }
        finally {
            sesi.close();
        }

       return status;
    }




    public List<Products> allProducts(){
        Session sesi= sf.openSession();
        List<Products> ls= sesi.createQuery("from Products ").getResultList();
        sesi.close();
        return ls;
    }

    public List<Customer> allCustomers(){
        Session sesi= sf.openSession();
        List<Customer> ls= sesi.createQuery("from Customer ").getResultList();
        sesi.close();
        return ls;
    }

    public Products singleProduct(int pid){
        Session sesi= sf.openSession();
        Products product = sesi.get(Products.class,pid);

        return product;
    }

    public Customer singleCustomer(int cid){
        Session sesi= sf.openSession();
        Customer customer=sesi.get(Customer.class,cid);

        return customer;
    }
    public List<Object[]> allFis(){
        Session sesi =sf.openSession();
        List<Object[]> order = sesi.createNativeQuery("SELECT ord.order_id,cus.cu_id,ord.fis_no, cus.cu_name,cus.cu_surname,pro.pro_title,ord.order_size,pro.pro_sale_price FROM products as pro INNER JOIN orders as ord on pro.pro_id=ord.products_pro_id INNER JOIN customer as cus ON cus.cu_id=ord.customer_cu_id WHERE cu_id and ord.`status`='1' ")
                .getResultList();

        sesi.close();
        return order;
    }

    public void addOrder(Products pro , Customer cus , int fis_number, Date date, int or_size){
        Session sesi= sf.openSession();
        Transaction tr=sesi.beginTransaction();
        int total=pro.getPro_amount()*pro.getPro_sale_price();
        Orders order =new Orders();
        order.setStatus(1);
        order.setProducts(pro);
        order.setDate(date);
        order.setCustomer(cus);
        order.setFis_no(fis_number);
        order.setOrder_size(or_size);
        order.setTotal(total);

        sesi.save(order);
        tr.commit();
        sesi.close();
    }

    public Long CashInTotal(){
        Session sesi=sf.openSession();
        Long sumOfAll = (Long) sesi.createQuery("SELECT sum(payInTotal) from CashIn ").getSingleResult();
        sesi.close();

        return sumOfAll;
    }
    public Long CashOutTotal(){
        Session sesi=sf.openSession();
        Long sumOfAllAges = (Long) sesi.createQuery("SELECT sum(payOutTotal) from CashOut ").getSingleResult();
        sesi.close();

        return sumOfAllAges;
    }

    public Long CountCustomer(){
        Session sesi=sf.openSession();
        Long cu_id = (Long) sesi.createQuery("SELECT count (cu_id) from Customer ").getSingleResult();

        sesi.close();
        return cu_id;
    }

    public Long CountOrder(){
        Session sesi=sf.openSession();
        Long order_id = (Long) sesi.createQuery("SELECT count (order_id) from Orders ").getSingleResult();

        sesi.close();
        return order_id;
    }

    public Long CountStock(){
        Session sesi=sf.openSession();
        Long countstock = (Long) sesi.createQuery("SELECT sum (pro_amount) from Products ").getSingleResult();
        sesi.close();

        return countstock;
    }

    public Long stockVariety(){
        Session sesi=sf.openSession();
        Long stockVar = (Long) sesi.createQuery("SELECT count (pro_id) from Products ").getSingleResult();

        sesi.close();
        return stockVar;
    }


    public Long stockBuyprice(){
        Session sesi=sf.openSession();
        Long size = (Long) sesi.createQuery("SELECT sum (pro_buying_price) from Products ").getSingleResult();

        sesi.close();
        return size;
    }
    public Long stockSaleprice(){
        Session sesi=sf.openSession();
        Long saleprice = (Long) sesi.createQuery("SELECT sum (pro_sale_price) from Products ").getSingleResult();
        sesi.close();

        return saleprice;
    }
}
