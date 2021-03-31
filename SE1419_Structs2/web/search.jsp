<%-- 
    Document   : search
    Created on : Nov 2, 2020, 7:30:00 AM
    Author     : HI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, <s:property value="%{#session.FULLNAME}" /><br/>
        <%--<s:property value="username"/>
        <s:property value="%{#attr.USERNAME}"/>--%>
        </font>
        <a href="signout">Sign Out</a><br/>
        <h1>Search Page</h1>
        <s:form action="searchLastname">
            <s:textfield name="searchValue" label="Search Value"/>
            <s:submit value="Search"/>
        </s:form>
        
        <s:if test="%{!searchValue.isEmpty()}">
            <s:if test="%{accountList != null}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Fullname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:form action="update" method="GET" theme="simple">
                        <s:iterator value="accountList" status="counter">
                            <tr>
                                <td>
                                    <s:property value="%{#counter.count}" />
                                </td>
                                <td>
                                    <s:property value="username" />
                                    <s:hidden name="username" value="%{username}"/>
                                </td>
                                <td>
                                    <%--<s:property value="password" />--%>
                                    <s:textfield name="password" value="%{password}"/>
                                </td>
                                <td>
                                    <s:property value="fullname" />
                                </td>
                                <td>
                                    <%--<s:property value="role" />--%>
                                    <s:checkbox name="admin" value="%{role}"/>
                                </td>
                                <td>
                                    <s:url id="urlRewriting" action="delete">
                                        <s:param name="pk" value="username" />
                                        <s:param name="lastSearchValue" value="searchValue" />
                                    </s:url>
                                    <s:a href="%{urlRewriting}" >Delete</s:a>
                                </td>
                                <td>
                                <s:submit value="Update"/>
                                <s:hidden name="lastSearchValue" value="%{searchValue}"/>
                                </td>
                            </tr>
                        </s:iterator>
                        </s:form>
                    </tbody>
                </table>

            </s:if>
            <s:else>
                <h2>
                    No record is matched!!!
                </h2>
            </s:else>
        </s:if>
    </body>
</html>
