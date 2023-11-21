package hardtech.service

import hardtech.entity.MotherboardDetails
import hardtech.repository.MotherboardDetailsRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class MotherboardDetailsService(
    private val motherboardDetailsRepository: MotherboardDetailsRepository, private val productService: ProductService
) {

    fun findByProductId(productId: Long): MotherboardDetails {
        return motherboardDetailsRepository.findById(productId).orElseThrow {
            EntityNotFoundException("No se encontraron detalles de la placa base para el producto con ID: $productId")
        }
    }

    fun save(motherboardDetails: MotherboardDetails): MotherboardDetails {
        val savedProduct = motherboardDetails.product?.let {
            productService.save(it)
        }
        motherboardDetails.product = savedProduct
        return motherboardDetailsRepository.save(motherboardDetails)
    }
}