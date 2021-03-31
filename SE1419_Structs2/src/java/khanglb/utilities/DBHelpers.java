/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.utilities;

import java.io.Serializable;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author HI
 */
public class DBHelpers implements Serializable{
    public static Connection makeConnection()
        throws /*ClassNotFoundException*/ NamingException, SQLException{
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Tao connection string
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Khang";
//        //3. Open connection
//        Connection con = DriverManager.getConnection(url, "sa", "123123");
//        
//        return con;
        
        //1. Get current context
        Context context = new InitialContext();
        //2. Get server context
        Context tomcatContext = (Context)context.lookup("java:comp/env");//allias name = logical name
        //3. Get Data Source
        DataSource ds = (DataSource)tomcatContext.lookup("SE1419DS");
        //4. Make connection
        Connection con = ds.getConnection();
        
        return con;
    }
}
