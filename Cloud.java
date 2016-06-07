/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

/**
 *
 * @author akshay
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Cloud  {
    
    BigInteger answer,firstnumber,secondnumber,ans, mod_value,pr_key;
    String s1;
    public static void main(String args[]) throws Exception
    {
        
        Cloud c=new Cloud();
        System.out.println("Enter Id");
        Scanner sc=new Scanner(new InputStreamReader(System.in));
        int a=sc.nextInt();
       BigInteger ans=c.checklogin(a);
        String dec_result;
         dec_result = String.valueOf(c.decrypt(ans));
        System.out.println(dec_result);
    }
   
    private BigInteger checklogin(int x)throws Exception
    {
        
         // System.out.println("hello");
        URL url= new URL("http://localhost:8080/Server/Register");
       
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
       con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream writer= new DataOutputStream(con.getOutputStream());
      
        writer.writeBytes("first="+x);
        con.connect();
      //  System.out.println(con);
        InputStream is = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        
       String answer= reader.readLine();
       BigInteger ans =new BigInteger(answer.trim());
     String  private_key= reader.readLine();
         pr_key = new BigInteger(private_key.trim());
      //  String public_key= reader.readLine();
       String modulus_value= reader.readLine(); 
      
    mod_value =new BigInteger(modulus_value.trim());  
          
       
          System.out.println("Multiplication="+answer);
       // System.out.println("public_key="+public_key);
        System.out.println("private_key="+private_key);
        System.out.println("modulus_value="+mod_value);
        
 // System.out.println("return ans=s"+ans);
           return ans; 
     }
     BigInteger decrypt(BigInteger encrypted) {
    
      //   System.out.println(encrypted);
         return encrypted.modPow(pr_key, mod_value);
     }
}





   