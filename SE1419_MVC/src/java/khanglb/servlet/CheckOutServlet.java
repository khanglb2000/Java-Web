/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanglb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanglb.book.BookDAO;
import khanglb.cart.CartObject;

/**
 *
 * @author HI
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    private final String ERROR_PAGE = "error.html";
    private final String SHOPPING_PAGE = "shopping.html";
    
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
        
        String url = ERROR_PAGE;
        
        try {
            HttpSession session = request.getSession(false);
            if(session != null){
                CartObject cart = (CartObject)session.getAttribute("CUSTCART");
                if(cart != null){
                    Map<String, Integer> items = cart.getItems();
                    if(items != null){
                        BookDAO dao = new BookDAO();
                        for (String title : items.keySet()) {
                            if(dao.checkBook(title) == false){
                                dao.insertBook(title, items.get(title));
                            } else{
                                dao.updateBook(title, items.get(title));
                            }
                        }
                        url = SHOPPING_PAGE;
                        
                    }//End if items are not null
                    
                }//End if cart is not null
                
                session.removeAttribute("CUSTCART");
            }//End if session is not null
        } catch(NamingException ex){
            ex.printStackTrace();
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally{
            response.sendRedirect(url);
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
