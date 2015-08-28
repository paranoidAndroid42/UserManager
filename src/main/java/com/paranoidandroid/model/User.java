package com.paranoidandroid.model;



/**
 * Created by aslý on 19.8.2015.
 */


public class User {

    private String _id;
    private String _userName;
    private String _userLastName;
    private String _telNumber; //input mask



    public String getName() {
        return _userName;
    }



    public void setName(String name) {
        _userName = name;
    }
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id= id;
    }

    public String getLastName() {
        return _userLastName;
    }

    public void setlastName(String lastName) {
        _userLastName = lastName;
    }

    public String getTelNumber() {
        return _telNumber;
    }

    public void setTelNumber(String telNumber) { _telNumber =telNumber;  }


}
