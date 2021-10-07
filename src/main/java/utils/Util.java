package utils;


import entities.Admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Util {
    public final static String base_url = "http://localhost:8080/depo_project_war_exploded/";

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static Admin isLogin(HttpServletRequest req, HttpServletResponse resp){
        if(req.getCookies()!=null) {
            Cookie[] cookies = req.getCookies();
           for(Cookie cookie:cookies){
               if(cookie.getName().equals("admin")){
                    String values = cookie.getValue();
                  try{
                       String[] array = values.split("_");
                       req.getSession().setAttribute("id", Integer.parseInt(array[0]));
                       req.getSession().setAttribute("name", array[1] + " " +array[2]);
                  }
                  catch (Exception ex){
                     Cookie cookiex = new Cookie("admin", "");
                     cookiex.setMaxAge(0);
                     resp.addCookie(cookiex);
                  }
                  break;
               }
           }
        }
       Object sesiObj = req.getSession().getAttribute("id");
       Admin admin = new Admin();

          if(sesiObj == null){
              try {
                  resp.sendRedirect(base_url);
              }catch (IOException ex){
                  System.err.println("Oturum Hatası :" + ex);
              }

          }
          else{
              int id = (int) req.getSession().getAttribute("id");
              String name = (String) req.getSession().getAttribute("name");
              String email = (String) req.getSession().getAttribute("email");

              admin.setId(id);
              admin.setName(name);
              admin.setEmail(email);

          }


        return admin;
    }


    }


