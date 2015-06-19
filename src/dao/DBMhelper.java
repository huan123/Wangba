/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huamyn
 */
public class DBMhelper {
     Connection con;
       ArrayList al;
    
   public DBMhelper(){
        
    }
    
   //加载驱动
   public Connection getCon() throws SQLException
   {
         
       //创建数据库连接对象
       Connection con = null;
        try {
            //加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");        
            //编写数据库连接字符串，用来指定连接的数据库
           // String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
//            String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
            //使用驱动对象进行数据库连接对象
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");

        } catch (Exception ex) {
//            Logger.getLogger(DBMhelper.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.fillInStackTrace());
        }
        ///返回数据库连接实例对象
        return con;
   }
   public ArrayList getMid() 
   {
     try
       {
       //获得连接
       con= getCon();
       al = new ArrayList();
       //所有的数据库连接必须放在try catch中  
           //编写要执行功能的数据库操作语句
           String str =  "select macId from mac where state=0";
           //创建一个能够执行语句对象
            PreparedStatement ps = con.prepareStatement(str);   
            //执行语句
            ResultSet rs = ps.executeQuery();
            //遍历结果集 将数据取出并保存
            while(rs.next())
            {
                al.add(rs.getObject(1));     
            }
            
       }catch(Exception e){
       }
       finally
       {
           try{
               if(!con.isClosed())
               {
                   con.close();
               }
           }
           catch(Exception e)
           {
               
           }
               
       }
               
       return al;
   }
   
   
   public String getUserInfo(int userId,String pwd) throws SQLException
   {
       Connection con = getCon();
       String blance="";
       
       try{
           
           String str  = "select blance from userInfo where userId=? and pwd=?";
           PreparedStatement ps = con.prepareStatement(str);
           ps.setInt(1,userId);
           ps.setString(2,pwd);
           ResultSet rs = ps.executeQuery();
           while(rs.next())
           {
               blance = rs.getObject(1).toString();
           }
       }catch(Exception exp)
       {
           
       }
       finally
       {
           try{
               if(!con.isClosed())
               {
                   con.close();
               }
              }catch(Exception e)
              {
               
              }
         
             }
         return blance;
       }
  
    
    public void updateMIdState(int macId) throws SQLException
    {
        Connection con = getCon();
       try{
            String str = "update mac set state = 1 where macId = ?";
        
            PreparedStatement ps = con.prepareStatement(str);
            ps.setInt(1, macId);
            ps.executeUpdate();
           
       }
       catch(Exception e)
       {
           
       }
       finally
       {
           try{
               if(!con.isClosed())
               {
                   con.close();
               }
           }
           catch(Exception e)
           {
               
           }
       }
        
        
    }
    
}
