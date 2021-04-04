/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HI
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_CONTROLLER = "DeleteServlet";
    private final String STARTUP_CONTROLLER = "StartupServlet";
    private final String UPDATE_CONTROLLER = "UpdateServlet";
    private final String ADD_TO_CART = "AddItemToCartServlet";
    private final String VIEW_CART = "viewCart.jsp";
    private final String REMOVE_ITEM_FROM_CART = "RemoveItemFromCartServlet";
    private final String CHECK_OUT_CART = "CheckOutServlet";
    private final String SIGN_UP = "SignUpServlet";
    
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
        
        String url = LOGIN_PAGE;
        String button = request.getParameter("txtAction");
        
        try{
            if(button == null){
                url = STARTUP_CONTROLLER;
            } else if(button.equals("Login")){
                url = LOGIN_CONTROLLER;
            } else if(button.equals("Logout")){
                url = LOGOUT_CONTROLLER;
            } else if(button.equals("Submit")){
                url = SEARCH_CONTROLLER;
            } else if(button.equals("Delete")){
                url = DELETE_CONTROLLER;
            } else if(button.equals("Update")){
                url = UPDATE_CONTROLLER;
            } else if("Add book".equals(button)){
                url = ADD_TO_CART;
            } else if("View cart".equals(button)){
                url = VIEW_CART;
            } else if("Remove selected Books".equals(button)){
                url = REMOVE_ITEM_FROM_CART;
            } else if("Check Out".equals(button)){
                url = CHECK_OUT_CART;
            } else if("Create New Account".equals(button)){
                url = SIGN_UP;
            }
            
        } finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
