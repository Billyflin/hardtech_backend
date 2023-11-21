package hardtech.service

import hardtech.entity.products.PowerSupplyDetails
import hardtech.repository.PowerSupplyDetailsRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class PowerSupplyDetailsService(
    private val powerSupplyDetailsRepository: PowerSupplyDetailsRepository, private val productService: ProductService
) {

    fun findByProductId(productId: Long): PowerSupplyDetails {
        return powerSupplyDetailsRepository.findById(productId).orElseThrow {
            EntityNotFoundException("No se encontraron detalles de la fuente de alimentación para el producto con ID: $productId")
        }
    }

    fun save(powerSupplyDetails: PowerSupplyDetails): PowerSupplyDetails {
        val savedProduct = powerSupplyDetails.product?.let {
            productService.save(it)
        }
        powerSupplyDetails.product = savedProduct
        return powerSupplyDetailsRepository.save(powerSupplyDetails)
    }
}