package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	// DELETE - Delete an order and its associated order items
	@DeleteMapping("/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
		orderService.deleteOrder(orderId);
		return ResponseEntity.ok("Order and associated items deleted successfully");
	}

	// GET - View orders with filtering, sorting, and pagination
	@GetMapping
	public ResponseEntity<Page<Order>> getOrders(@RequestParam(required = false) String status,
			@RequestParam(required = false) Long customerId, @RequestParam(required = false) Long tenantId,
			@RequestParam(defaultValue = "createdAt") String sortBy,
			@RequestParam(defaultValue = "desc") String sortOrder, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<Order> orders = orderService.getOrders(status, customerId, tenantId, sortBy, sortOrder, page, size);
		return ResponseEntity.ok(orders);
	}
}