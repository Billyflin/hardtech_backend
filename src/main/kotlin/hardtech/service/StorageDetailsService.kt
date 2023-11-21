package hardtech.service

import hardtech.entity.StorageDetails
import hardtech.repository.StorageDetailsRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class StorageDetailsService(
    private val storageDetailsRepository: StorageDetailsRepository, private val productService: ProductService
) {
    fun findByProductId(productId: Long): StorageDetails {
        return storageDetailsRepository.findById(productId).orElseThrow {
            EntityNotFoundException("No se encontraron detalles del almacenamiento para el producto con ID: $productId")
        }
    }

    fun save(storageDetails: StorageDetails): StorageDetails {
        val savedProduct = storageDetails.product?.let {
            productService.save(it)
        }
        storageDetails.product = savedProduct
        return storageDetailsRepository.save(storageDetails)
    }

}