<%-- 
    Document   : viewCart
    Created on : Oct 19, 2020, 7:24:23 AM
    Author     : HI
--%>

<%@page import="java.util.Map"%>
<%@page import="khanglb.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CUSTCART}"/>
        <c:set var="itemList" value="${cart.items}"/>
        <c:if test="${not empty cart}">
            <c:if test="${not empty itemList}">
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${itemList}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${item.key}
                                    </td>
                                    <td>
                                        ${item.value}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${item.key}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            
                            <tr>
                                <td colspan="3">
                                    <a href="shopping.html">Add more books to cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove selected Books" name="txtAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </form>
                
                <form action="DispatchServlet">
                    <input type="submit" value="Check Out" name="txtAction" />
                </form>
            </c:if>
            
            <c:if test="${empty itemList}">
                <h2>No book is in your cart</h2>
                <a href="shopping.html">Back to shopping page</a>
            </c:if>
        </c:if>
        
        <c:if test="${empty cart}">
            <h2>No book is in your cart</h2>
            <a href="shopping.html">Back to shopping page</a>
        </c:if>
        <%--
        <%
            //1.Cust goes to your cart place
            if(session != null){
                //2.Cust takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CUSTCART");
                if(cart != null){
                    //3. Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if(items != null){
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Title</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <form action="DispatchServlet">
                                <tbody>
                                <%
                                    int count = 0;
                                    for (String itemKey : items.keySet()) {
                                        %>
                                 <tr>
                                    <td>
                                        <%= ++count %>        
                                     </td>
                                     <td>
                                         <%= itemKey %>
                                     </td>
                                     <td>
                                         <%= items.get(itemKey) %>
                                     </td>
                                     <td>
                                         <input type="checkbox" name="chkItem" 
                                                value="<%= itemKey %>" />
                                     </td>
                                </tr>
                                <%
                                    }// End for itemKey
                                %>
                                <tr>
                                    <td colspan="3">
                                        <a href="shopping.html">Add More Books</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove selected Books" name="txtAction" />
                                    </td>
                                </tr>
                                
                            </tbody>
                            </form>
                        </table>
                                
                            <form action="DispatchServlet">
                                <input type="submit" value="Check Out" name="txtAction" />
                            </form>

        <%
                        return;
                    }//End if items is existed
                } //End if cart is existed
            }//End if session is not existed
        %>
        --%>
    </body>
</html>
