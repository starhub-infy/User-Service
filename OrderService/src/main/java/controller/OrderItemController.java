package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;

	// GET - View order items with filtering, sorting, and pagination
	@GetMapping
	public ResponseEntity<Page<OrderItem>> getOrderItems(@RequestParam(required = false) Long orderId,
			@RequestParam(required = false) String categoryType,
			@RequestParam(defaultValue = "createdAt") String sortBy,
			@RequestParam(defaultValue = "desc") String sortOrder, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<OrderItem> orderItems = orderItemService.getOrderItems(orderId, categoryType, sortBy, sortOrder, page,
				size);
		return ResponseEntity.ok(orderItems);
	}
}