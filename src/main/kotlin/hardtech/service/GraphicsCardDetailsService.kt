package hardtech.service

import hardtech.entity.GraphicsCardDetails
import hardtech.repository.GraphicsCardDetailsRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class GraphicsCardDetailsService(
    private val graphicsCardDetailsRepository: GraphicsCardDetailsRepository, private val productService: ProductService
) {

    fun findByProductId(productId: Long): GraphicsCardDetails {
        return graphicsCardDetailsRepository.findById(productId).orElseThrow {
            EntityNotFoundException("No se encontraron detalles de la tarjeta gráfica para el producto con ID: $productId")
        }
    }

    fun save(graphicsCardDetails: GraphicsCardDetails): GraphicsCardDetails {
        val savedProduct = graphicsCardDetails.product?.let {
            productService.save(it)
        }
        graphicsCardDetails.product = savedProduct
        return graphicsCardDetailsRepository.save(graphicsCardDetails)
    }
}