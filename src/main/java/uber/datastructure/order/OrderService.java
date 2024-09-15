package uber.datastructure.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uber.datastructure.ride.Ride;
import uber.datastructure.ride.RideRepository;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RideRepository rideRepository;

    public OrderDTO createOrderForRide(Ride ride) {
        // Calculate the cost or any other fees (for example)
        double totalCost = calculateTotalCost(ride);

        // Create a new Order entity
        Order order = new Order();
        order.setTrip(ride);
        order.setTotalCost(totalCost);
        order.setStatus("Pending");  // Set initial status as "Pending"
        order.setCreationTime(LocalDateTime.now());

        // Save the order to the database
        Order savedOrder = orderRepository.save(order);

        // Convert to DTO and return
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus(savedOrder.getStatus());
        orderDTO.setCreationTime(savedOrder.getCreationTime());

        return orderDTO;
    }

    private double calculateTotalCost(Ride ride) {
        return 100.0;
    }

    public OrderDTO completePayment(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        System.out.println("Completing payment for order ID: " + orderId);
        order.setStatus("Paid");
        Order savedOrder = orderRepository.save(order);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus(savedOrder.getStatus());
        orderDTO.setCreationTime(savedOrder.getCreationTime());
        return orderDTO;
    }

    public String completePaymentTest(Long orderId) {
        return "Test successful!";
    }
}
