package hardtech.service

import hardtech.dto.ImageDTO
import hardtech.dto.ProductDTO
import hardtech.entity.Product
import hardtech.repository.ProductRepository
import hardtech.validator.ProductValidator
import org.springframework.stereotype.Service
import org.springframework.validation.BeanPropertyBindingResult
import java.util.*

@Service
class ProductService(private val productRepository: ProductRepository, private val productValidator: ProductValidator) {

    fun findAll(): List<ProductDTO> {
        val products = productRepository.findAll()
        return products.map { product ->
            val images = product.images.map { image ->
                val imageDataBase64 = Base64.getEncoder().encodeToString(image.imageData)
                ImageDTO(image.imageId, imageDataBase64)
            }
            ProductDTO(product.productId, product.productName, product.brand, product.price, product.category, images)
        }
    }
    fun findById(id: Long): Product {
        return productRepository.findById(id).orElseThrow { RuntimeException("Product not found") }
    }

    fun save(product: Product): Product {
        val errors = BeanPropertyBindingResult(product, "product")
        productValidator.validate(product, errors)
        if (errors.hasErrors()) {
            throw RuntimeException(errors.allErrors.joinToString(", "))
        }
        return productRepository.save(product)
    }

    fun deleteById(id: Long) {
        return productRepository.deleteById(id)
    }
}