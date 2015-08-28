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

/**
 * Created by asl? on 19.8.2015.
 */

public class DeleteUser extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoUserController mongoUserController = new MongoUserController(mongo);
        User user = new User();
        user.setId(id);
        mongoUserController.DeleteUser(user);   //delete user
    //    System.out.println("Person deleted successfully with id=" + id);
     //   request.setAttribute("success", "Person deleted successfully");
        List<User> users  = mongoUserController.getUsers();  //read all users

        HttpSession session = request.getSession();
        session.setAttribute("users", users);  //save users for jsp

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/index.jsp");
        rd.forward(request, response);
    }


}