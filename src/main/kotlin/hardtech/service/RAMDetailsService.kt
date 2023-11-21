package hardtech.service

import hardtech.entity.RAMDetails
import hardtech.repository.RAMDetailsRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class RAMDetailsService(
    private val ramDetailsRepository: RAMDetailsRepository, private val productService: ProductService
) {

    fun findByProductId(productId: Long): RAMDetails {
        return ramDetailsRepository.findById(productId).orElseThrow {
            EntityNotFoundException("No se encontraron detalles de RAM para el producto con ID: $productId")
        }
    }

    fun save(ramDetails: RAMDetails): RAMDetails {
        val savedProduct = ramDetails.product?.let {
            productService.save(it)
        }
        ramDetails.product = savedProduct
        return ramDetailsRepository.save(ramDetails)
    }
}