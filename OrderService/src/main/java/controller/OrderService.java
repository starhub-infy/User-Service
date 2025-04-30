package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Transactional
	public void deleteOrder(Long orderId) {
		// Check if the order exists
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found"));

		// Delete associated order items
		orderItemRepository.deleteByOrder_OrderId(orderId);

		// Delete the order
		orderRepository.delete(order);
	}

	public Page<Order> getOrders(String status, Long customerId, Long tenantId, String sortBy, String sortOrder,
			int page, int size) {
		// Build filtering and sorting criteria
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

		Specification<Order> spec = Specification.where(null);

		if (status != null) {
			spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status));
		}
		if (customerId != null) {
			spec = spec
					.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("customerId"), customerId));
		}
		if (tenantId != null) {
			spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tenantId"), tenantId));
		}

		return orderRepository.findAll(spec, pageable);
	}
}