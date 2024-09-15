package uber.datastructure.ride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import uber.datastructure.user.User;

@RestController
@RequestMapping("api/uber/rides")
public class RideController {
    @Autowired
    private RideService rideService;

    @PostMapping("/create/{passengerId}")
    public ResponseEntity<Ride> createRide(@PathVariable Long passengerId) {
        Ride ride1 = new Ride();
        Ride saveRide = rideService.createRide(passengerId, ride1);
        return ResponseEntity.ok(saveRide);
    }

    @PutMapping("/{rideId}/assign-driver/{driverId}")
    public ResponseEntity<RideDTO> assignDriver(@PathVariable Long rideId, @PathVariable Long driverId) {
        RideDTO updatedRide = rideService.assignDriverToRide(rideId, driverId);
        return ResponseEntity.ok(updatedRide);
    }

    @PutMapping("/{rideId}/cancel/{userId}")
    public ResponseEntity<RideDTO> cancelTrip(@PathVariable Long rideId, @PathVariable Long userId) {
        RideDTO updatedRide = rideService.cancelTrip(rideId, userId);
        return ResponseEntity.ok(updatedRide);
    }

    @PutMapping("/{rideId}/pick-up/{driverId}")
    public ResponseEntity<RideDTO> driverPicksUpPassenger(@PathVariable Long rideId) {
        RideDTO updatedRide = rideService.driverPicksUpPassenger(rideId);
        return ResponseEntity.ok(updatedRide);
    }

    @PutMapping("/{rideId}/complete")
    public ResponseEntity<RideDTO> completeRide(@PathVariable Long rideId) {
        RideDTO updatedRide = rideService.completeRide(rideId);
        return ResponseEntity.ok(updatedRide);
    }
}
