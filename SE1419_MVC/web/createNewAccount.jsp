<%-- 
    Document   : createNewAccount
    Created on : Oct 26, 2020, 7:46:00 AM
    Author     : HI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Create new Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATEERROR}" />
            Username <input type="text" name="txtUsername" value="${param.txtUsername}" />(e.g. 6 - 30 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                    ${errors.usernameLengthError}
                </font>
            </c:if><br/>
            Password <input type="password" name="txtPassword" value="" />(e.g. 6 - 20 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}
                </font>
            </c:if><br/>
            Confirm <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${errors.confirmNotMatched}
                </font>
            </c:if><br/>
            Fullname <input type="text" name="txtFullname" value="${param.txtFullname}" />(e.g. 6 - 50 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                    ${errors.fullnameLengthError}
                </font>
            </c:if><br/>
            
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                </font>
            </c:if><br/>
            
            <input type="submit" value="Create New Account" name="txtAction" /><br/>
            <input type="reset" value="Reset" /><br/>
        </form>
            
    </body>
</html>
