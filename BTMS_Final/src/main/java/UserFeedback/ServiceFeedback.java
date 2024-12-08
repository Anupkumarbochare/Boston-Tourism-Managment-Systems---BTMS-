/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserFeedback;

/**
 *
 * @author kaushikj
 */
public class ServiceFeedback {
    private String service;
    private String problem;
    private String qualityOfService;
    private String howCanWeImprove;
    private String problemDescription;
private String status;
    
    public ServiceFeedback(String service, String problem, String qualityOfService, String howCanWeImprove, String problemDescription, boolean isActive) {
        this.service = service;
        this.problem = problem;
        this.qualityOfService = qualityOfService;
        this.howCanWeImprove = howCanWeImprove;
        this.problemDescription = problemDescription;
    this.status = isActive ? "Active" : "Inactive";

    }
    
    // Getters and Setters
    public String getService() {
        return service;
    }
    
    public void setService(String service) {
        this.service = service;
    }
    
    public String getProblem() {
        return problem;
    }
    
    public void setProblem(String problem) {
        this.problem = problem;
    }
    
    public String getQualityOfService() {
        return qualityOfService;
    }
    
    public void setQualityOfService(String qualityOfService) {
        this.qualityOfService = qualityOfService;
    }
    
    public String getHowCanWeImprove() {
        return howCanWeImprove;
    }
    
    public void setHowCanWeImprove(String howCanWeImprove) {
        this.howCanWeImprove = howCanWeImprove;
    }
    
    public String getProblemDescription() {
        return problemDescription;
    }
    
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

   public String getStatus() {
    return status;
}

public void setStatus(boolean isActive) {
    this.status = isActive ? "Active" : "Inactive";
}
}
