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
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
   

public class Admin {
  private final static BigInteger one = new BigInteger("1");
  private final static SecureRandom random = new SecureRandom();

    

  private BigInteger privateKey;
  private BigInteger publicKey;
  private BigInteger modulus;

  // generate an N-bit (roughly) public and private key
  Admin(int N) {
  BigInteger p = BigInteger.probablePrime(N/2, random);
  BigInteger q = BigInteger.probablePrime(N/2, random);
  BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

  modulus = p.multiply(q);  
 // publicKey = new BigInteger("65537"); // common value in practice = 2^16 + 1
 publicKey=p; 
 privateKey = publicKey.modInverse(phi);
 System.out.println("Private_Key="+privateKey);
  System.out.println("Public_Key="+publicKey);
  }
  BigInteger encrypt(BigInteger message) {
  return message.modPow(publicKey, modulus);
  }

  BigInteger decrypt(BigInteger encrypted) {
  return encrypted.modPow(privateKey, modulus);
  }

  public String toString1() {
  String s = "";
  s +=publicKey;
  return s;
  }
  public String toString() {
  String s1 = "";
  s1 += privateKey;
 
  return s1;
  }
  public String toString2() {
  String s2 = "";

  s2 += modulus;
  return s2;
  }
 
 
   public static void main(String[] args) throws IOException {
      
       String url = "jdbc:mysql://localhost:3306/encryption";
        String user = "root";
        String password = "";
 
  Admin key = new Admin(100);
  //System.out.println(key);
System.out.println("Enter two number"); 
Scanner sc=new Scanner(new InputStreamReader(System.in)); 
int a =sc.nextInt();
int b=sc.nextInt();
   
  BigInteger x1 = new BigInteger(String.valueOf(a));
  BigInteger x2 = new BigInteger(String.valueOf(b));

  BigInteger enc_x1 = key.encrypt(x1);
  BigInteger enc_x2 = key.encrypt(x2);

 
  BigInteger homomorphic = enc_x1.multiply(enc_x2);

   
  System.out.println("x1 = " + x1);
  System.out.println("x2 = " + x2);
   
  System.out.println("E ( x1 ) = " + enc_x1);
  System.out.println("E ( x2 ) = " + enc_x2);
  
  
   
    insertData(enc_x1,enc_x2,homomorphic,key.privateKey,key.publicKey,key.modulus);
  

 /* 
  try {
            Connection conn = DriverManager.getConnection(url, user, password);
 
            String sql = "INSERT INTO data(first_num, second_num,result) values (?, ?, ?)";
            String sql_key = "INSERT INTO key_data(private_key,public_key,modulus_value) values (?, ?, ?)";
            
            
            PreparedStatement statement = conn.prepareStatement(sql);
            PreparedStatement statement_key = conn.prepareStatement(sql_key); 
            statement.setString(1, enc_x1.toString());
            statement.setString(2, enc_x2.toString());
          statement.setString(3, homomorphic.toString());
            statement_key.setString(1, key.toString());
             statement_key.setString(2, key.toString1());
              statement_key.setString(3, key.toString2());
            
            int row = statement.executeUpdate();
              int row_key = statement_key.executeUpdate();
            if (row > 0 && row_key > 0) {
                System.out.println("Data Inserted");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
  */
  }
   
   private static void insertData(BigInteger enc_x1, BigInteger enc_x2,BigInteger homomorphic,BigInteger privateKey,BigInteger publicKey,BigInteger modulus) throws IOException {
       
    
   URL url= new URL("http://project-niranjanjoshi.rhcloud.com/InsertData");
       
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
       System.out.println(con);
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream writer= new DataOutputStream(con.getOutputStream());
   // System.out.println(privateKey);
        writer.writeBytes("first_num="+enc_x1+"&second_num="+enc_x2+"&private_key="+privateKey+"&public_key="+publicKey+"&modulus_value="+modulus);
       // writer.w
       
        con.connect();
      
       InputStream is = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        
       String answer= reader.readLine();
       System.out.println(answer);
  
   }
}
