/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.struts2;

import com.opensymphony.xwork2.ActionSupport;
import khanglb.registration.RegistrationDAO;

/**
 *
 * @author HI
 */
public class SignUpAction extends ActionSupport{
    private String username;
    private String password;
    private String confirm;
    private String fullname;
    
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    
    public SignUpAction() {
    }
    
    public String createNewAccount() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        boolean result = dao.insertAccount(username, password, fullname, false);
        
        String url = FAIL;
        if(result){
            url = SUCCESS;
        }
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

    /**
     * @return the confirm
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
}
