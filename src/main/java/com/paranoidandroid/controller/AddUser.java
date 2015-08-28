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

public class AddUser extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        User user = new User();
        user.setName(request.getParameter("name"));                 //getparameter
        user.setlastName(request.getParameter("lastName"));
        user.setTelNumber(request.getParameter("phone"));

        MongoUserController mongoUserController = new MongoUserController(mongo);   //connect mongo controller

        if((user.getName()!=null || ! !"".equals(user.getName() )) && (user.getLastName()!=null || !"".equals(user.getLastName())))
              mongoUserController.AddUser(user);   //add user

        List<User> users  = mongoUserController.getUsers(); //read all users

        HttpSession session = request.getSession();
        session.setAttribute("users", users);     //save users for index.jsp

                RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/index.jsp");
        rd.forward(request, response);


    }
}
