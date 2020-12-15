package com.study.nodejsappchat.entities;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String phoneNum;
    private String userName;
    private String password;
    private List<User>  listFriend;
    private List<User> requestAddFr;
    private List<User> listSendRequest;

    public User(String phoneNum, String userName, String password) {
        this.phoneNum = phoneNum;
        this.userName = userName;
        this.password = password;

    }

    public User(String phoneNum, String userName, String password, List<User> listFriend, List<User> requestAddFr, List<User> listSendRequest) {
        this.phoneNum = phoneNum;
        this.userName = userName;
        this.password = password;
        this.listFriend = listFriend;
        this.requestAddFr = requestAddFr;
        this.listSendRequest = listSendRequest;
    }

    public User(String phoneNum, String userName) {
        this.phoneNum = phoneNum;
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User>  getListFriend() {
        return listFriend;
    }

    public void setListFriend(List<User>  listFriend) {
        this.listFriend = listFriend;
    }

    public List<User> getRequestAddFr() {
        return requestAddFr;
    }

    public void setRequestAddFr(List<User> requestAddFr) {
        this.requestAddFr = requestAddFr;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<User> getListSendRequest() {
        return listSendRequest;
    }

    public void setListSendRequest(List<User> listSendRequest) {
        this.listSendRequest = listSendRequest;
    }
}
