package com.paranoidandroid.controller;

import com.mongodb.*;
import com.paranoidandroid.model.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aslý on 27.8.2015.
 */
public class MongoUserController {

    private DBCollection _collection;
    private BasicDBObject _document;


    public MongoUserController(MongoClient mongo){

        _collection =  mongo.getDB("UserManagement").getCollection("User");
    }

    public void AddUser(User user)
    {
        _document = new BasicDBObject();
        _document.put("name", ""+user.getName());
        _document.put("lastName", ""+user.getLastName());   //create table
        _document.put("phone", "" + user.getTelNumber());
        _document.put("id", "" + user.getId());

        _collection.insert(_document);  //insert document
        user.setId(_document.get("_id").toString());

    }

    public void UpdateUser(User user)
    {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(user.getId())).get();    //find user
        _document = new BasicDBObject();
        _document.put("name", ""+user.getName());                  //chance old user
        _document.put("lastName", ""+user.getLastName());
        _document.put("phone", "" + user.getTelNumber());
        _document.put("id", "" + user.getId());
        _collection.update(query, _document);

    }

    public User EditUser(User user)
    {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(user.getId())).get();
        DBObject data = _collection.findOne(query);              //find user
        User findUser = new User();
        findUser.setName(data.get("name").toString());
        findUser.setId(data.get("id").toString());
        findUser.setlastName(data.get("lastName").toString());
        findUser.setTelNumber(data.get("phone").toString());
        findUser.setId(data.get("id").toString());
        return findUser;  //return user for jsp
    }

    public void DeleteUser(User user)
    {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(user.getId())).get();  //find user
        _collection.remove(query); //delete user from table
    }

    public List<User>  getUsers()
    {
        List<User> users = new ArrayList<User>();
        DBCursor result = _collection.find();  //find user
        while( result.hasNext() )
        {
            User user = new User();
            DBObject temp = result.next();
            user.setId("" + temp.get("_id"));        //get all users
            user.setName(""+temp.get( "name" ));
            user.setlastName("" + temp.get("lastName"));
            user.setTelNumber("" + temp.get("phone"));
            users.add(user);
        }

        return users;
    }



}
