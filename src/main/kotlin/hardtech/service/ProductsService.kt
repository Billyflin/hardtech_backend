package hardtech.service


import hardtech.entity.*
import hardtech.repository.*
import hardtech.validator.ProductValidator
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.web.multipart.MultipartFile
import java.util.*

data class ImageDTO(
    val imageId: Long,
    val imageDataBase64: String
)

data class ProductDTO(
    val productId: Long,
    val productName: String,
    val brand: String,
    val price: Double,
    val category: Categories,
    val images: List<ImageDTO>
)
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

@Service
class ImageService(private val imageRepository: ImageRepository) {

    fun saveImage(image: MultipartFile, product: Product): Image {
        val bytes = image.bytes
        val imageEntity = Image(imageData = bytes, product = product)
        return imageRepository.save(imageEntity)
    }
}

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

// Similarmente para ProcessorDetails, RAMDetails y GraphicsCardDetails

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

@Service
class SalesHistoryService(private val salesHistoryRepository: SalesHistoryRepository) {

    fun findByProductId(productId: Long): SalesHistory {
        return salesHistoryRepository.findById(productId).orElseThrow { RuntimeException("SalesHistory not found") }
    }

    fun save(salesHistory: SalesHistory): SalesHistory {
        return salesHistoryRepository.save(salesHistory)
    }
}

@Service
class OrdersService(private val ordersRepository: OrdersRepository) {

    fun findByUserId(userId: Long): Orders {
        return ordersRepository.findById(userId).orElseThrow { RuntimeException("Orders not found") }
    }

    fun save(orders: Orders): Orders {
        return ordersRepository.save(orders)
    }
}

@Service
class OrderDetailsService(private val orderDetailsRepository: OrderDetailsRepository) {

    fun findByOrderId(orderId: Long): OrderDetails {
        return orderDetailsRepository.findById(orderId).orElseThrow { RuntimeException("OrderDetails not found") }
    }

    fun save(orderDetails: OrderDetails): OrderDetails {
        return orderDetailsRepository.save(orderDetails)
    }
}
