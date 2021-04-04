/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanglb.cart.CartObject;

/**
 *
 * @author HI
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

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
        
        try {
            //1. Cust goes to his/her cart place
            HttpSession session = request.getSession(false);//check xem session co con ton tai hay ko
            if(session != null){
                //2. Cust takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CUSTCART");
                if(cart != null){
                    //3. Cus takes items
                    Map<String, Integer> items = cart.getItems();
                    if(items != null){
                        //4. Cust takes all seleted removed items
                        String[] itemList = request.getParameterValues("chkItem");
                        if(itemList != null){
                            for (String item : itemList) {
                                cart.removeItemFromCart(item);
                            }//end for item
                            session.setAttribute("CUSTCART", cart);
                        }//End if having selected item
                    }// End if items are existed
                } //End if cart is exited
            } //End if session is existed
            
        } finally{
            String urlRewriting = "DispatchServlet"
                            + "?txtAction=View cart";
            response.sendRedirect(urlRewriting);
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
