package uber.datastructure.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String status; //Driver or Passenger

    @Column(nullable = false)
    private String password; //User password

    @Column(nullable = false, unique = true)
    private String username;  // Username

    private String licensePlateNumber;  // License plate (nullable for passengers)

    private String model;  // Vehicle model (nullable for passengers)

    private Double totalJourneyLength;  // Total historical journey length

    private String province;

    private String locationCity;

    public User(String phoneNumber, String status, String key, String username) {
        this.phoneNumber = phoneNumber;
        if (status.equals("Driver") || status.equals("Passenger")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status");
        }
        this.password = key;
        this.username = username;
    }

    public User() {

    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        if (this.status.equals("Passenger")) {
            this.licensePlateNumber = null;
        } else {
            this.licensePlateNumber = licensePlateNumber;
        }
    }

    public void setModel(String model) {
        if (this.status.equals("Passenger")) {
            this.model = null;
        } else {
            this.model= model;
        }
    }

}