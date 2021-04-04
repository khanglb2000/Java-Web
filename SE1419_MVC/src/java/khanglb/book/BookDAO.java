/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.book;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khanglb.utilities.DBHelpers;

/**
 *
 * @author HI
 */
public class BookDAO implements Serializable{
    public boolean checkBook(String title)
        throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select Title "
                        + "From Book "
                        + "Where Title = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, title);
                rs = stm.executeQuery();
                if(rs.next()){
                    return true;
                }
            } //End if con is exsited
        } finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        
        return false;
    }
    
    public boolean insertBook(String title, int quantity)
        throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Insert Into Book(Title, Quantity) "
                        + "Values(?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, title);
                stm.setInt(2, quantity);
                
                int row = stm.executeUpdate();
                if(row > 0){
                    return true;
                }
            }//End if con is existed
        } finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        
        return false;
    }
    
    public boolean updateBook(String title, int quantity)
        throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Update Book "
                        + "Set Quantity = ? "
                        + "Where Title = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, title);
                
                int row = stm.executeUpdate();
                if(row > 0){
                    return true;
                }
            }
        } finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        
        return false;
    }
    
}
