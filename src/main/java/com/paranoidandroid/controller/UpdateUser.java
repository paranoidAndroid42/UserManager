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

public class UpdateUser extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        HttpSession editSession = request.getSession();
        String id = editSession.getAttribute("editId").toString();
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoUserController mongoUserController = new MongoUserController(mongo);
        User user = new User();
        user.setId(id);
        user.setName(request.getParameter("editName"));
        user.setlastName(request.getParameter("editLastName"));   //get user
        user.setTelNumber(request.getParameter("editPhone"));

        if((user.getName()!=null || ! !"".equals(user.getName() )) && (user.getLastName()!=null || !"".equals(user.getLastName())))
        mongoUserController.UpdateUser(user);  //update user

        List<User> users  = mongoUserController.getUsers();  //read all user

        HttpSession session = request.getSession();
        session.setAttribute("users", users);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/index.jsp");
        rd.forward(request, response);

    }

}