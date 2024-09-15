package uber.datastructure.order;

import uber.datastructure.ride.Ride;

import java.time.LocalDateTime;

public class OrderDTO {
    private String status;
    private LocalDateTime creationTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }


}