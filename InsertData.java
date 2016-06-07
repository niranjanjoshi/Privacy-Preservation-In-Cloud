/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class InsertData extends HttpServlet {
     public static final String MYSQL_USERNAME = System.getenv("admin4CCWtlh");
    public static final String MYSQL_PASSWORD = System.getenv("rgrrCAp731tZ");
    public static final String MYSQL_DATABASE_HOST = System.getenv("127.8.98.130");
    public static final String MYSQL_DATABASE_PORT = System.getenv("3306");
    public static final String MYSQL_DATABASE_NAME = "project";
    private static Connection con;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String firstnum,secondnum,publickey,privatekey,modulusvalue,homomorphic_ans;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        firstnum = request.getParameter("first_num");
        secondnum = request.getParameter("second_num");
       homomorphic_ans = request.getParameter("homomorphic");  
        privatekey = request.getParameter("private_key");
        publickey = request.getParameter("public_key");
        modulusvalue = request.getParameter("modulus_value");
       
      // firstnum="hi";
      // secondnum="h";
      // modulusvalue="i";
      out.println("Data ");
      // Class.forName("com.mysql.jdbc.Driver");  
 //   Connection con=DriverManager.getConnection("","admin4CCWtlh","rgrrCAp731tZ");  
   String url = "jdbc:mysql://" + MYSQL_DATABASE_HOST + ":" + MYSQL_DATABASE_PORT + "/" + MYSQL_DATABASE_NAME;
                con = DriverManager.getConnection(url, MYSQL_USERNAME, MYSQL_PASSWORD);
      
      out.println(con);
            String sql = "INSERT INTO data(first_num, second_num,result) values (?, ?, ?)";
            String sql_key = "INSERT INTO key_data(private_key,public_key,modulus_value) values (?, ?, ?)";
            
              PreparedStatement statement = con.prepareStatement(sql);
             PreparedStatement statement_key = con.prepareStatement(sql_key);
    //   out.println(statement);     
                statement.setString(1, firstnum);
            statement.setString(2, secondnum);
          statement.setString(3, modulusvalue);
            statement_key.setString(1, privatekey);
             statement_key.setString(2, publickey);
              statement_key.setString(3, modulusvalue);
            // out.println(firstnum);
            int row = statement.executeUpdate();
              int row_key = statement_key.executeUpdate();
            if (row > 0 && row_key > 0 ) {
                out.println("Data Inserted");
            }
            con.close();
        
       // try {
            /* TODO output your page here. You may use following sample code. */
         /*   out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertData</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertData at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
        
    }*/
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
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

