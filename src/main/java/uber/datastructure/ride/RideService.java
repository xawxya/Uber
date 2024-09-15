package uber.datastructure.ride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uber.datastructure.order.OrderDTO;
import uber.datastructure.order.OrderService;
import uber.datastructure.user.UserRepository;
import uber.datastructure.user.User;
import java.time.LocalDateTime;

@Service
public class RideService {
    @Autowired
    RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    // Create a new ride by passenger
    public Ride createRide(Long passengerId, Ride ride) {
        // Find the passenger by ID
        User passenger = userRepository.findById(Math.toIntExact(passengerId))
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        ride.setPassenger(passenger);
        ride.setCreationTime(LocalDateTime.now());
        ride.setStatus("Created");

        return rideRepository.save(ride);
    }
    // Assign a driver to the ride
    public RideDTO assignDriverToRide(Long rideId, Long driverId) {
        Ride ride = rideRepository.findById(Math.toIntExact(rideId))
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        User driver = userRepository.findById(Math.toIntExact(driverId))
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        ride.setDriver(driver);
        ride.setStatus("Driver Accepted");

        Ride savedRide = rideRepository.save(ride);
        // Convert to DTO
        RideDTO rideDTO = new RideDTO();
        rideDTO.setId(savedRide.getId());
        rideDTO.setStatus(savedRide.getStatus());
        rideDTO.setDriverName(driver.getUsername());  // Assume you have a username field in User

        return rideDTO;
    }

    public RideDTO cancelTrip(Long rideId, Long userId) {
        Ride ride = rideRepository.findById(Math.toIntExact(rideId))
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        // Ensure that the user who is canceling the trip is the passenger
        if (!ride.getPassenger().getUid().equals(userId)) {
            throw new IllegalArgumentException("Only the passenger can cancel the trip");
        }

        ride.setStatus("Cancelled");
        ride.setCancellationTime(LocalDateTime.now());

        Ride savedRide = rideRepository.save(ride);
        // Convert to DTO
        RideDTO rideDTO = new RideDTO();
        rideDTO.setId(savedRide.getId());
        rideDTO.setStatus(savedRide.getStatus());

        return rideDTO;
    }

    //Use similar method to other ride status change, such as "Driver arrived", "Travel ended"
    public RideDTO driverPicksUpPassenger(Long rideId) {
        Ride ride = rideRepository.findById(Math.toIntExact(rideId))
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (ride.getStatus().equals("Cancelled")) {
            throw new IllegalArgumentException("Trip is cancelled");
        }

        ride.setStatus("Picked Up");
        ride.setPickUpTime(LocalDateTime.now());  // Track when the passenger was picked up

        Ride savedRide = rideRepository.save(ride);
        RideDTO rideDTO = new RideDTO();
        rideDTO.setId(savedRide.getId());
        rideDTO.setStatus(savedRide.getStatus());
        rideDTO.setPickUpTime(LocalDateTime.now());

        return rideDTO;
    }

    public RideDTO completeRide(Long rideId) {
        Ride ride = rideRepository.findById(Math.toIntExact(rideId))
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        if (ride.getStatus().equals("Cancelled")) {
            throw new IllegalArgumentException("Ride is already canceled");
        }

        ride.setStatus("Completed");
        ride.setArrivalTime(LocalDateTime.now());

        Ride savedRide = rideRepository.save(ride);
        OrderDTO createdOrder = orderService.createOrderForRide(savedRide);

        RideDTO rideDTO = new RideDTO();
        rideDTO.setId(savedRide.getId());
        rideDTO.setStatus(savedRide.getStatus());
        rideDTO.setArrivalTime(LocalDateTime.now());

        return rideDTO;
    }
}
