/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;  
import java.math.BigInteger;
    import java.sql.*;  
    import javax.servlet.ServletException;  
    import javax.servlet.http.*;  
      
    public class Search extends HttpServlet {  
            public static final String MYSQL_USERNAME = System.getenv("admin4CCWtlh");
    public static final String MYSQL_PASSWORD = System.getenv("rgrrCAp731tZ");
    public static final String MYSQL_DATABASE_HOST = System.getenv("127.8.98.130");
    public static final String MYSQL_DATABASE_PORT = System.getenv("3306");
    public static final String MYSQL_DATABASE_NAME = "project";
    private static Connection con;

      String url = "jdbc:mysql://" + MYSQL_DATABASE_HOST + ":" + MYSQL_DATABASE_PORT + "/" + MYSQL_DATABASE_NAME;
                
      
        BigInteger mult;
        private final static BigInteger one = new BigInteger("1");
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
      
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
              
    String rollno=request.getParameter("roll");  
    int roll=Integer.valueOf(rollno);  
              
    try{  
    Class.forName("com.mysql.jdbc.Driver");  
   /* Connection con=DriverManager.getConnection(  
    "jdbc:mysql://127.8.98.130:3306/encryption","admin4CCWtlh","rgrrCAp731tZ");  
     */         
     con = DriverManager.getConnection(url, MYSQL_USERNAME, MYSQL_PASSWORD);    
    PreparedStatement ps=con.prepareStatement("select first_name,last_name from person where person_id=?");  
    ps.setInt(1,roll);  
                  
    out.print("<table width=50% border=1>");  
    out.print("<caption>Result:</caption>");  
      
    ResultSet rs=ps.executeQuery();  
                  
    /* Printing column names */  
    ResultSetMetaData rsmd=rs.getMetaData();  
    int total=rsmd.getColumnCount();  
    out.print("<tr>");  
    for(int i=1;i<=total;i++)  
    {  
    out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
    }  
      
    out.print("</tr>");  
                  
    /* Printing result */  
      /*
    while(rs.next())  
    {  
    out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+" </td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");  
                      
    }  
      */
    
    while(rs.next())  
    {  
    out.println(rs.getString(1));                   
    out.println(rs.getString(2));
    BigInteger x1 = new BigInteger(rs.getString(1));
BigInteger x2 = new BigInteger(rs.getString(2));
       BigInteger answer = x1.multiply(x2);
       out.println(answer);
    }
          /*      //String name = request.getParameter("name");
             String a = request.getParameter();
             String b = request.getParameter(rs.getString(2));
            double sum = 0;
            BigInteger x,y;
            x = BigInteger.valueOf(a);
            y = Integer.parseInt(b);
            sum = x*y;
                  
             BigInteger x1 = new BigInteger(rs.getString(1));
BigInteger x2 = new BigInteger(rs.getString(2));
       BigInteger answer = x1.multiply(x2);
       out.println(answer);*/
       out.print("</table>");  
                 
    }catch (Exception e2) {e2.printStackTrace();}  
              
    finally{out.close();}  
      
      }  
    } 
