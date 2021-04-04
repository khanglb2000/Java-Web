<%-- 
    Document   : search
    Created on : Oct 10, 2020, 10:10:01 AM
    Author     : HI
--%>

<%@page import="khanglb.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
        Welcome. ${sessionScope.USERNAME}<br/>
        <c:set var="account" value="${sessionScope.ACCOUNT}"/>
        <font color="blue">
            Welcome, ${account.fullname}
        </font>
        
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}"/><br/>
            <input type="submit" value="Submit" name="txtAction"/>
        </form>
            
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>FullName</th>
                        <th>Role</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="DispatchServlet">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.username}
                                <input type="hidden" name="txtUsername" value="${dto.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" value="${dto.password}" />
                            </td>
                            <td>
                                ${dto.fullname}
                            </td>
                            <td>
                                <input type="checkbox" name="chkAdmin" value="ON" 
                                       <c:if test="${dto.role}">
                                           checked="checked"
                                       </c:if>
                                       />
                            </td>
                            <td>
                                <c:url var="urlRewriting" value="DispatchServlet">
                                    <c:param name="txtAction" value="Delete"/>
                                    <c:param name="pk" value="${dto.username}"/>
                                    <c:param name="lastSearchValue" value="${searchValue}"/>
                                </c:url>
                                <a href="${urlRewriting}">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="txtAction" />
                                <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                            </td>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
            </c:if>
            
            <c:if test="${empty result}">
                <h2>
                    No record is matched!!!
                </h2>
            </c:if>
        </c:if>
        <br/>
        <form action="DispatchServlet">
            <input type="submit" value="Logout" name="txtAction" /><br/><br/>
        </form>
        <%--<%
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                String username = null;
                for (Cookie cookie : cookies) {
                    String temp = cookie.getName();
                    if(!temp.equals("JSESSIONID")){
                        username = temp;
                    }
                }
                %>
                <font color="red">
                    Welcome, <%= username %>
                </font>
                <%
            }// end if cookie is existed
        %>
        <form action="DispatchServlet">
            <input type="submit" value="Logout" name="txtAction" /><br/><br/>
        </form>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue") %>"/><br/>
            <input type="submit" value="Submit" name="txtAction"/>
        </form>
        
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if(searchValue != null){
                List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCHRESULT");
                if(result != null){
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                
                                int count = 0;
                                for(RegistrationDTO dto: result){
                                    String urlRewriting = "DispatchServlet"
                                            + "?txtAction=Delete"
                                            + "&pk=" + dto.getUsername()
                                            + "&lastSearchValue=" 
                                            + searchValue;
                                    %>
                        <form action="DispatchServlet">
                                    <tr>
                                <td>
                                    <%= ++count %>
                                </td>
                                <td>
                                    <%= dto.getUsername() %>
                                    <input type="hidden" name="txtUsername" 
                                           value="<%= dto.getUsername() %>" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="<%= dto.getPassword() %>" />
                                </td>
                                <td>
                                    <%= dto.getFullname() %>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ADMIN" 
                                           <%
                                           if(dto.isRole()){
                                               %>
                                               checked ="checked"
                                               <%
                                           }    
                                           %>
                                           />
                                </td>
                                <td>
                                    <a href="<%=  urlRewriting %>">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="txtAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="<%= searchValue %>" />
                                </td>
                            </tr>
                        </form>
                            <%
                                } //end for dto
                            %>
                        </tbody>
                    </table>

        <%
                } else{
                    %>
                    <h2>
                        No record is matched!!!
                    </h2>
        <%
                }
            }// end if search has value
        %>
        --%>
    </body>
</html>
