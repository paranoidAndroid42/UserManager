package com.paranoidandroid.controller;

import com.mongodb.MongoClient;
import com.paranoidandroid.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EditUser extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");     //connect
        MongoUserController mongoUserController = new MongoUserController(mongo);
        User user = new User();
        user.setId(id);
        User editUser = mongoUserController.EditUser(user);
        HttpSession editSession = request.getSession();
        editSession.setAttribute("editUsers", editUser);  //save edit user for jsp
        editSession.setAttribute("editId", user.getId());  //save user id for update

        List<User> users  = mongoUserController.getUsers();  //read all users

        HttpSession session = request.getSession();
        session.setAttribute("users", users);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/index.jsp");
        rd.forward(request, response);

    }

}