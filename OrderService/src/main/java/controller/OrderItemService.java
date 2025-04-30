package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	public Page<OrderItem> getOrderItems(Long orderId, String categoryType, String sortBy, String sortOrder, int page,
			int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));

		Specification<OrderItem> spec = Specification.where(null);

		if (orderId != null) {
			spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("orderId"), orderId));
		}
		if (categoryType != null) {
			spec = spec.and(
					(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryType"), categoryType));
		}

		return orderItemRepository.findAll(spec, pageable);
	}
}