package hardtech.service

import hardtech.entity.ProcessorDetails
import hardtech.repository.ProcessorDetailsRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class ProcessorDetailsService(
    private val processorDetailsRepository: ProcessorDetailsRepository, private val productService: ProductService
) {

    fun findByProductId(productId: Long): ProcessorDetails {
        return processorDetailsRepository.findById(productId).orElseThrow {
            EntityNotFoundException("No se encontraron detalles del procesador para el producto con ID: $productId")
        }
    }

    fun save(processorDetails: ProcessorDetails): ProcessorDetails {
        val savedProduct = processorDetails.product?.let {
            productService.save(it)
        }
        processorDetails.product = savedProduct
        return processorDetailsRepository.save(processorDetails)
    }
}