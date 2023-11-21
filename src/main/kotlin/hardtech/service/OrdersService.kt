package hardtech.service

import hardtech.entity.Orders
import hardtech.repository.OrdersRepository
import org.springframework.stereotype.Service

@Service
class OrdersService(private val ordersRepository: OrdersRepository) {

    fun findByUserId(userId: Long): Orders {
        return ordersRepository.findById(userId).orElseThrow { RuntimeException("Orders not found") }
    }

    fun save(orders: Orders): Orders {
        return ordersRepository.save(orders)
    }
}