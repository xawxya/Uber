package uber.datastructure.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uber.datastructure.ride.Ride;

@RestController
@RequestMapping("api/uber/orderss")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PutMapping("/paid/{orderId}")
    public ResponseEntity<OrderDTO> paid(@PathVariable Long orderId) {
        OrderDTO paidOrder = orderService.completePayment(orderId);
        return ResponseEntity.ok(paidOrder);
    }

//    @PutMapping("/paid/{orderId}")
//    public String paid(@PathVariable Long orderId) {
//        return orderService.completePaymentTest(orderId);
//    }
}
