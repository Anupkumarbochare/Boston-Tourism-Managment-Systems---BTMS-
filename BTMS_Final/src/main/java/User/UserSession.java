/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import java.util.Map;

/**
 *
 * @author anupbochare
 */
public class UserSession {
  private static UserSession instance;
    private String username;
        private String organization;
    private String enterprise;

    
    private UserSession() {}
    
    public static synchronized UserSession getInstance() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
 public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    public String getOrganization() {
        return organization;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }
    
    public String getEnterprise() {
        return enterprise;
    }

    public void clearSession() {
        username = null;
        organization = null;
        enterprise = null;
    }

}
