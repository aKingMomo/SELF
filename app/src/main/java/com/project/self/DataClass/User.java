package com.project.self.DataClass;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mohitosh on 11/29/2017.
 */

public class User {

    private String username;
    private String password;
    private String FirstName;
    private String LastName;
    private String email;
    private String Birthday;
    private String Gender;
    private String phone;
    private String jobTitle;
    private String country;
    private String city;
    private String state;
    private String userID;

    public JSONObject createJson(){

        JSONObject newJson = new JSONObject();


        try {
            newJson.put(Consts.userID,getUserID());
            newJson.put(Consts.userKey,getUsername());
            newJson.put(Consts.passKey,getPassword());
            newJson.put(Consts.fName,getFirstName());
            newJson.put(Consts.lName,getLastName());
            newJson.put(Consts.eMail,getEmail());
            newJson.put(Consts.birth,getBirthday());
            newJson.put(Consts.gender,getGender());
            newJson.put(Consts.phone,getPhone());
            newJson.put(Consts.jTitle,getJobTitle());
            newJson.put(Consts.country,getCountry());
            newJson.put(Consts.city,getCity());
            newJson.put(Consts.state,getState());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newJson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
