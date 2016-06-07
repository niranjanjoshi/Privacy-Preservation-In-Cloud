/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author akshay
 */
public class Register extends HttpServlet {
           String ids;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
           BigInteger x1,x2,x3,x4,x5;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException  {
     
       
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
        ids = request.getParameter("first");
        
        Class.forName("com.mysql.jdbc.Driver");  
    Connection con=DriverManager.getConnection(  
    "jdbc:mysql://localhost:3306/encryption","root","");  
        
   PreparedStatement ps=con.prepareStatement("SELECT * FROM data where person_id=?");
   
    PreparedStatement ps_key=con.prepareStatement("SELECT * FROM key_data where person_id=?");
    ps.setInt(1,Integer.parseInt(ids));
    ps_key.setInt(1,Integer.parseInt(ids));

  
    ResultSet rs=ps.executeQuery();
    
    ResultSet rs_key=ps_key.executeQuery();
   
   // ResultSetMetaData rsmd=rs.getMetaData();
 
       
   
   rs.next();
   rs_key.next();
    //out.println(rs.getString(1));                   
    //out.println(rs.getString(2));
    String s=rs.getString("first_num");
    String t=rs.getString("second_num");
    String pt=rs_key.getString("private_key");
   // String p=rs_key.getString("public_key");
     String mod_value=rs_key.getString("modulus_value");
    BigInteger answer;
  
    x1= new BigInteger(s);
     x2= new BigInteger(t);
     x3=new BigInteger(mod_value);
     x4= new BigInteger(pt);
       answer=x1.multiply(x2);
        out.println(answer);
        out.println(x4);
        out.println(x3);
       //x1= new BigInteger(s);   
      //x2= new BigInteger(t);
    
      //out.println(x4);
      
     // out.println(x3);    
    }
    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               try {
                   processRequest(request, response);
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
               }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               try {
                   processRequest(request, response);
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
               }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
