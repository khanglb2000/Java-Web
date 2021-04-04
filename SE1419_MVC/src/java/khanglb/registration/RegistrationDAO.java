/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khanglb.utilities.DBHelpers;

/**
 *
 * @author HI
 */
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            //1. make connection
            con = DBHelpers.makeConnection();
            //2 Create sql string
            if(con != null){
                String sql = "Select Username "
                        + "From Registration "
                        + "Where Username = ? And Password = ?";
                //3.create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                if(rs.next()){
                    return true;
                }
            } //end if connection is connected
        
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
    
    public RegistrationDTO checkLogin2(String username, String password)
        throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO dto = null;
        
        try{
            //1. make connection
            con = DBHelpers.makeConnection();
            //2 Create sql string
            if(con != null){
                String sql = "Select Username, Lastname "
                        + "From Registration "
                        + "Where Username = ? And Password = ?";
                //3.create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                if(rs.next()){
                    String fullName = rs.getString("Lastname");
                    dto = new RegistrationDTO(null, null, fullName, false);
                }
            } //end if connection is connected
        
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
        
        return dto;
    }
    
    private List<RegistrationDTO> accountList;
    
    //getA --> ctrl + spacebar
    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }
    
    public void searchLastname(String searchValue)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            //1. make connection
            con = DBHelpers.makeConnection();
            //2 Create sql string
            if(con != null){
                String sql = "Select Username, Password, Lastname, Admin "
                        + "From Registration "
                        + "Where Lastname Like ?";
                //3.create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                while(rs.next()){
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String fullname = rs.getString("Lastname");
                    boolean role = rs.getBoolean("Admin");
                    
                    RegistrationDTO dto = new RegistrationDTO(username, 
                                            password, fullname, role);
                    
                    if(this.accountList == null){
                        this.accountList = new ArrayList<>();
                    } //End if accountList is not existed
                    
                    this.accountList.add(dto);
                }
            } //end if connection is connected
        
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
    }
    
    public boolean deleteAccount(String username)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
            //1. make connection
            con = DBHelpers.makeConnection();
            //2 Create sql string
            if(con != null){
                String sql = "Delete From Registration "
                        + "Where Username = ?";
                //3.create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. execute query
                int row = stm.executeUpdate();
                //5. process result
                if(row > 0){
                    return true;
                }
            } //end if connection is connected
        
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
    
    public boolean updateAccount(String username, String password, boolean role)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
            //1. make connection
            con = DBHelpers.makeConnection();
            //2 Create sql string
            if(con != null){
                String sql = "Update Registration "
                        + "Set Password = ?, Admin = ? "
                        + "Where Username = ?";
                //3.create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4. execute query
                int row = stm.executeUpdate();
                //5. process result
                if(row > 0){
                    return true;
                }
            } //end if connection is connected
        
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
    
    public boolean insertAccount(String username, String password, String fullname, boolean role)
        throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
            //1. make connection
            con = DBHelpers.makeConnection();
            //2 Create sql string
            if(con != null){
                String sql = "Insert Into Registration(Username, Password, Lastname, Admin) "
                        + "Values(?, ?, ?, ?)";
                //3.create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                //4. execute query
                int row = stm.executeUpdate();
                //5. process result
                if(row > 0){
                    return true;
                }
            } //end if connection is connected
        
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
