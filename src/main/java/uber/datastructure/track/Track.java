package uber.datastructure.track;

import jakarta.persistence.*;
import lombok.Data;
import uber.datastructure.ride.Ride;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Ride trip;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    private Float velocityTrace;
    private Float altitudeTrace;
}