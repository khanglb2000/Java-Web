/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;


/**
 *
 * @author HI
 */
public class SignoutAction {
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    
    public SignoutAction() {
    }
    
    public String signout() throws Exception {
        String url = SUCCESS;
        Map session = ActionContext.getContext().getSession();
        if(session != null){
            session.clear();
        } else{
            url = FAIL;
        }
        
        return url;
    }
    
}
