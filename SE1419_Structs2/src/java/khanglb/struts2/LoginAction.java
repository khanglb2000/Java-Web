/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import khanglb.registration.RegistrationDAO;
import khanglb.registration.RegistrationDTO;

/**
 *
 * @author HI
 */
public class LoginAction {
    private String username;
    private String password;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    
    public LoginAction() {
    }
    
    public String checkLogin() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        boolean result = dao.checkLogin(username, password);
        RegistrationDTO result2 = dao.checkLogin2(username, password);
        
        String url = FAIL;
        if(result){
            url = SUCCESS;
            Map session = ActionContext.getContext().getSession();
            session.put("USERNAME", username);
        }//end if result is existed
        
        if(result2 != null){
            url = SUCCESS;
            Map session = ActionContext.getContext().getSession();
            session.put("FULLNAME", result2.getFullname());
        }//end if result is existed
        
        return url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
