package uber.datastructure.ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uber.datastructure.user.User;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
}
