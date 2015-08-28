<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User Manager</title>

    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel = "stylesheet" type="text/css" href="javascripts/jquery/jquery-ui-1.11.4.custom/jquery-ui.css">
    <link rel = "stylesheet" type="text/css" href="javascripts/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css">
    <link rel = "stylesheet" type="text/css" href="javascripts/jquery/jquery-ui-1.11.4.custom/jquery-ui.structure.css">
    <link rel = "stylesheet" type="text/css" href="javascripts/jquery/jquery-ui-1.11.4.custom/jquery-ui.structure.min.css">
    <link rel = "stylesheet" type="text/css" href="javascripts/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.css">
    <link rel = "stylesheet" type="text/css" href="javascripts/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css">

    <script type = "text/javascript" src = "javascripts/jquery/jquery-1.11.3.min.js"></script>
    <script type = "text/javascript" src = "javascripts/jquery/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
    <script type = "text/javascript" src = "javascripts/jquery/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
    <script type = "text/javascript" src = "javascripts/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
    <script type = "text/javascript" src = "javascripts/jquery/inputMaskTel/inputmask.js"></script>
    <script type = "text/javascript" src = "javascripts/controller/controllAjax.js"></script>

</head>

<body>

<div id="box"  title="Add User" >
    <form id = "add_user_form" method="post">
        <fieldset>
            <label for="name">Ad:</label><br>
            <input type="text" name="name" id="name" value="" class="text ui-widget-content ui-corner-all"><br>
            <label for="name">Soyad:</label><br>
            <input type="text" name="lastName" id="lastName" value="" class="text ui-widget-content ui-corner-all"><br>
            <label for="name">Telefon:</label><br>
            <input type="tel" name = "phone" id ="phone"><br>

            <input type="submit" id ="add_user_submit" value="ADD">
        </fieldset>
    </form>
</div>

<div id="editBox"  title="Edit User" >
    <form id = "edit_user_form" method="post">
        <fieldset>
            <label for="name">Ad:</label><br>
            <input type="text" name="editName" id="editName" value="" class="text ui-widget-content ui-corner-all"><br>
            <label for="name">Soyad:</label><br>
            <input type="text" name="editLastName" id="editLastName" value="" class="text ui-widget-content ui-corner-all"><br>
            <label for="name">Telefon:</label><br>
            <input type="tel" name = "editPhone" value="" id ="editPhone"><br>
            <input type="submit" id ="edit_user_submit" value="EDIT">
        </fieldset>
    </form>
</div>

<div id="container">

    <div id="header">
        <h1>User Management</h1>
    </div>

    <div id="content">

            <table align="center">
                <tbody>
                <tr>
                    <th>NAME</th>
                    <th>LAST NAME</th>
                    <th>EDIT</th>
                    <th>DELETE</th>
                </tr>
                <c:forEach items="${sessionScope.users}" var="users">
                    <tr>
                        <td><c:out value="${users.name}"></c:out></td>
                        <td><c:out value="${users.lastName}"></c:out></td>
                        <td><c:out value="${users.telNumber}"></c:out></td>
                        <td><input type="button" class="editUser"  onclick="editUser('${users.id}')" value="Edit"></td>
                        <td><input type="button" class="deleteUser" onclick="deleteUser('${users.id}')" value="Delete"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

    </div>

    <div id="footer">
        <input type="button" value="Add User" id="add_user"/>

    </div>

</div>

</body>
</html>