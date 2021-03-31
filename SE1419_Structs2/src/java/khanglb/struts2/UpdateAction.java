/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.struts2;

import khanglb.registration.RegistrationDAO;

/**
 *
 * @author HI
 */
public class UpdateAction {
    private String username;
    private String password;
    private boolean admin;
    private String lastSearchValue;
    
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    
    public UpdateAction() {
    }
    
    public String updateAccount() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        boolean result = dao.updateAccount(username, password, admin);
        
        String url = FAIL;
        if(result){
            url = SUCCESS;
        }//end if update succcessfully
        
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
     * @return the lastSearchValue
     */
    public String getLastSearchValue() {
        return lastSearchValue;
    }

    /**
     * @param lastSearchValue the lastSearchValue to set
     */
    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
}
