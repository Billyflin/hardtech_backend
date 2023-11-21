package hardtech.service

import hardtech.entity.products.CoolingDetails
import hardtech.repository.CoolingDetailsRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class CoolingDetailsService(
    private val coolingDetailsRepository: CoolingDetailsRepository, private val productService: ProductService
) {

    fun findByProductId(productId: Long): CoolingDetails {
        return coolingDetailsRepository.findById(productId).orElseThrow {
            EntityNotFoundException("No se encontraron detalles de refrigeración para el producto con ID: $productId")
        }
    }

    fun save(coolingDetails: CoolingDetails): CoolingDetails {
        val savedProduct = coolingDetails.product?.let {
            productService.save(it)
        }
        coolingDetails.product = savedProduct
        return coolingDetailsRepository.save(coolingDetails)
    }
}