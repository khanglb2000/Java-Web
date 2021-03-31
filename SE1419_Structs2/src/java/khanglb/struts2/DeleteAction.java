/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.struts2;

import khanglb.registration.RegistrationDAO;
import khanglb.registration.RegistrationDTO;

/**
 *
 * @author HI
 */
public class DeleteAction {
    private String pk;
    private String lastSearchValue;
    
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public DeleteAction() {
    }
    
    public String deleteAccount() throws Exception{
        RegistrationDAO dao = new RegistrationDAO();
        boolean result = dao.deleteAccount(pk);
        
        String url = FAIL;
        if(result){
            url = SUCCESS;
        }//end if delete is successfull
        
        return url;
    }

    /**
     * @return the pk
     */
    public String getPk() {
        return pk;
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(String pk) {
        this.pk = pk;
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
    
}
