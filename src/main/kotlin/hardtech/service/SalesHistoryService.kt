package hardtech.service

import hardtech.entity.SalesHistory
import hardtech.repository.SalesHistoryRepository
import org.springframework.stereotype.Service

@Service
class SalesHistoryService(private val salesHistoryRepository: SalesHistoryRepository) {

    fun findByProductId(productId: Long): SalesHistory {
        return salesHistoryRepository.findById(productId).orElseThrow { RuntimeException("SalesHistory not found") }
    }

    fun save(salesHistory: SalesHistory): SalesHistory {
        return salesHistoryRepository.save(salesHistory)
    }
}