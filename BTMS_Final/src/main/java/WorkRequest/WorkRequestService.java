/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WorkRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author anupbochare
 */
public class WorkRequestService {
    
private static WorkRequestService instance;
    private List<WorkRequest> workRequests;

    private WorkRequestService() {
        workRequests = new ArrayList<>();
        initializeTestData();
    }

    public static synchronized WorkRequestService getInstance() {
        if (instance == null) {
            instance = new WorkRequestService();
        }
        return instance;
    }

    private void initializeTestData() {
        // Cross-Organization Request Examples
        workRequests.add(new WorkRequest(
            "CROSS_ORG",
            "Request for additional tour guides for weekend event",
            "event1",
            "guide1",
            "EventManagement",
            "TourGuides",
            "TourismEnterprise",
            "TourismEnterprise"
        ));

        // Cross-Enterprise Request Examples
        workRequests.add(new WorkRequest(
            "CROSS_ENTERPRISE",
            "Request for hotel accommodation for event participants",
            "event1",
            "hotel1",
            "EventManagement",
            "HotelManagement",
            "TourismEnterprise",
            "HospitalityEnterprise"
        ));
    }

    public void createWorkRequest(WorkRequest request) {
        workRequests.add(request);
    }

    public List<WorkRequest> getRequestsForUser(String username) {
        return workRequests.stream()
            .filter(r -> r.getSenderUsername().equals(username) || 
                        r.getReceiverUsername().equals(username))
            .collect(Collectors.toList());
    }

    public void updateRequestStatus(String requestId, String status) {
        workRequests.stream()
            .filter(r -> r.getRequestId().equals(requestId))
            .findFirst()
            .ifPresent(r -> r.setStatus(status));
    }
}
