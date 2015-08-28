package com.paranoidandroid.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.UnknownHostException;

@WebListener
public class MongoController implements ServletContextListener {

    private DB _db;
    private DBCollection _collection;
    private BasicDBObject _document;
    private MongoClient _mongo;


    public void contextDestroyed(ServletContextEvent sce) {
        MongoClient mongo = (MongoClient) sce.getServletContext()
                .getAttribute("MONGO_CLIENT");
        mongo.close();
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext ctx = sce.getServletContext();

            _mongo = new MongoClient("localhost",27017);   //connect mongo

            _db = _mongo.getDB( "UserManagement" );   //create db
            _document = new BasicDBObject();

            if( !_db.collectionExists( "User" ) )   //if user not created
            {
                _collection = _db.createCollection( "User", _document);  //create table
            }
            else
                _collection = _db.getCollection( "User" );
            sce.getServletContext().setAttribute("MONGO_CLIENT", _mongo);

        } catch (UnknownHostException e) {
            throw new RuntimeException("MongoClient failed!");
        }
    }


}