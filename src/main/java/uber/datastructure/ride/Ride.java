package uber.datastructure.ride;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import uber.datastructure.order.Order;
import uber.datastructure.track.Track;
import uber.datastructure.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ride")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;  // The passenger for the ride

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User driver;

    private String mqttChannelName;
    private String type;  // Economy, Comfort, Luxury

    private Double departureLat;
    private Double departureLng;
    private String departureAddress;

    private Double endLat;
    private Double endLng;
    private String endAddress;

    @Column(nullable = false)
    private String status;  // Trip status (e.g., Create, Traveling, Completed)

    private LocalDateTime driverAcceptanceTime;
    private LocalDateTime pickUpTime;
    private LocalDateTime arrivalTime;
    private LocalDateTime cancellationTime;

    private Double totalStrokeLength;

    @OneToOne(mappedBy = "trip")
    private Order tripOrderId;

    @OneToOne(mappedBy = "trip")
    private Track trackId;

    private Integer tripEvaluationScore;
    private String tripEvaluationContent;
//    private String alarmProcessStatus;
//    private String afterSalesProcessingStatus; //(None, complaint, complaint being processed, complaint has been processed)

    public Ride(User passenger) {
    }

    public Ride() {

    }

}