package uber.datastructure.order;

import jakarta.persistence.*;
import lombok.Data;
import uber.datastructure.ride.Ride;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Ride trip;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    private Double totalCost;
    private Double startingPrice;
    private Double travelFees;
    private Double timeFee;
    private Double specialLocationFee;
    private Double dynamicPricing;

    @Column(nullable = false)
    private String status;  // Not Paid, Paid, Refunding, Refunded
    private String paymentPlatform;
    private String paymentPlatformSerialNumber;
    private String paymentResult;


}