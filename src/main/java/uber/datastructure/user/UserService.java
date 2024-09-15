package uber.datastructure.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uber.datastructure.ride.Ride;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }
    public User updateUser(Long id, User userDetails) {
        User existingUser = userRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        existingUser.setStatus(userDetails.getStatus());
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setLicensePlateNumber(userDetails.getLicensePlateNumber());
        existingUser.setModel(userDetails.getModel());
        existingUser.setTotalJourneyLength(userDetails.getTotalJourneyLength());
        existingUser.setProvince(userDetails.getProvince());
        existingUser.setLocationCity(userDetails.getLocationCity());

        return userRepository.save(existingUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getReferenceById(Math.toIntExact(id));
    }

    public void deleteUser(Long id) {
        if(userRepository.getReferenceById(Math.toIntExact(id)) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else {
            userRepository.deleteById(Math.toIntExact(id));
        }
    }
}
