/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khanglb.registration.RegistrationCreateError;
import khanglb.registration.RegistrationDAO;

/**
 *
 * @author HI
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {
    private final String ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        
        String url = ERROR_PAGE;
        
        boolean foundError = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        
        try {
           //1. CHeck 4 faults of user
           if(username.trim().length() < 6 || username.trim().length() > 30){
               foundError = true;
               errors.setUsernameLengthError("Username is requiring typing form 6 - 30 characters");
           }
           if(password.trim().length() < 6 || password.trim().length() > 20){
               foundError = true;
               errors.setPasswordLengthError("Password is requiring typing form 6 - 20 characters");
           } else if(!confirm.trim().equals(password.trim())){
               foundError = true;
               errors.setConfirmNotMatched("Confirm must match password");
           }
           if(fullname.trim().length() < 6 || fullname.trim().length() > 50){
               foundError = true;
               errors.setFullnameLengthError("Fullname is requiring typing form 6 - 50 characters");
           }
           
           if(foundError){
               //2. Put error in scope
               request.setAttribute("CREATEERROR", errors);
           } else{
               //3. Call DAO to insert
               RegistrationDAO dao = new RegistrationDAO();
               boolean result = dao.insertAccount(username, password, fullname, false);
               
               if(result){
                   url = LOGIN_PAGE;
               }// end if result
           }
        } catch(NamingException ex){
            log("SignupServlet _ Naming: " + ex.getMessage());
        } catch(SQLException ex){
            String errMsg = ex.getMessage();
            log("SignupServlet _ SQL: " + errMsg);
            if(errMsg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATEERROR", errors);
            }
        }
        finally{
            RequestDispatcher ds = request.getRequestDispatcher(url);
            ds.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
