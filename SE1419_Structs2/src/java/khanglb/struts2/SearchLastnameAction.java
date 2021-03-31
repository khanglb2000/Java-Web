/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.struts2;

import java.util.List;
import khanglb.registration.RegistrationDAO;
import khanglb.registration.RegistrationDTO;

/**
 *
 * @author HI
 */
public class SearchLastnameAction {
    private String searchValue;
    private List<RegistrationDTO> accountList;
    private final String SUCCESS = "success";
    
    public SearchLastnameAction() {
    }
    
    public String searchLastname() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        
        dao.searchLastname(searchValue);
        this.accountList = dao.getAccountList();
        
        return SUCCESS;
    }

    /**
     * @return the searchValue
     */
    public String getSearchValue() {
        return searchValue;
    }

    /**
     * @param searchValue the searchValue to set
     */
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    /**
     * @return the accountList
     */
    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }
    
}
