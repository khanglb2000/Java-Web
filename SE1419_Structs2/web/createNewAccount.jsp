<%-- 
    Document   : createNewAccount
    Created on : Nov 5, 2020, 6:39:52 AM
    Author     : HI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <s:head/>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="signUp" method="POST">
            Username <input type="text" name="username" value="" /><br/>
            Password <input type="password" name="password" value="" /><br/>
            Confirm <input type="password" name="confirm" value="" /><br/>
            Fullname <input type="text" name="fullname" value="" /><br/>
            
            <input type="submit" value="Create New Account" />
            <input type="reset" value="Reset" />
        </form>
        
        <s:if test="%{exception.message.contains('duplicate')}">
            <s:property value="username"/> is existed!!!
        </s:if>
    </body>
</html>
