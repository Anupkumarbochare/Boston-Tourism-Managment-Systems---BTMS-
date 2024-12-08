/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WorkRequest;

import java.util.Date;

/**
 *
 * @author anupbochare
 */
public class WorkRequest {

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getSenderOrganization() {
        return senderOrganization;
    }

    public void setSenderOrganization(String senderOrganization) {
        this.senderOrganization = senderOrganization;
    }

    public String getReceiverOrganization() {
        return receiverOrganization;
    }

    public void setReceiverOrganization(String receiverOrganization) {
        this.receiverOrganization = receiverOrganization;
    }

    public String getSenderEnterprise() {
        return senderEnterprise;
    }

    public void setSenderEnterprise(String senderEnterprise) {
        this.senderEnterprise = senderEnterprise;
    }

    public String getReceiverEnterprise() {
        return receiverEnterprise;
    }

    public void setReceiverEnterprise(String receiverEnterprise) {
        this.receiverEnterprise = receiverEnterprise;
    }
 private String requestId;
    private String type;        // CROSS_ORG, CROSS_ENTERPRISE
    private String status;      // PENDING, APPROVED, REJECTED
    private String description;
    private Date requestDate;
    private String senderUsername;
    private String receiverUsername;
    private String senderOrganization;
    private String receiverOrganization;
    private String senderEnterprise;
    private String receiverEnterprise;

    public WorkRequest(String type, String description, String senderUsername, 
            String receiverUsername, String senderOrg, String receiverOrg,
            String senderEnt, String receiverEnt) {
        this.requestId = "WR-" + System.currentTimeMillis();
        this.type = type;
        this.status = "PENDING";
        this.description = description;
        this.requestDate = new Date();
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.senderOrganization = senderOrg;
        this.receiverOrganization = receiverOrg;
        this.senderEnterprise = senderEnt;
        this.receiverEnterprise = receiverEnt;
    }   
}
