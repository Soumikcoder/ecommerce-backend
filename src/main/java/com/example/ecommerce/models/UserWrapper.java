
package com.example.ecommerce.models;



public class UserWrapper {
    private int userId;
    private String username;
    
    public int getUserId() {
        return userId;
    }
    @Override
    public String toString() {
        return "UserWrapper [userId=" + userId + ", username=" + username + "]";
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
