package hardtech.service

import hardtech.entity.OrderDetails
import hardtech.repository.OrderDetailsRepository
import org.springframework.stereotype.Service

@Service
class OrderDetailsService(private val orderDetailsRepository: OrderDetailsRepository) {

    fun findByOrderId(orderId: Long): OrderDetails {
        return orderDetailsRepository.findById(orderId).orElseThrow { RuntimeException("OrderDetails not found") }
    }

    fun save(orderDetails: OrderDetails): OrderDetails {
        return orderDetailsRepository.save(orderDetails)
    }
}