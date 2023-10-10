package com.houserentalapp.models;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "users")
public class UserModel {

    public String _id;
    public String username;
    public String email;
    public String usertype;
    public String password;

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel() {
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "_id='" + _id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", usertype='" + usertype + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
