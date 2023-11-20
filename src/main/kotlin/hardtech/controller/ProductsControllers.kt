package hardtech.controller

import hardtech.entity.*
import hardtech.service.*
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<ProductDTO>> {
        val products = productService.findAll()
        return if (products.isNotEmpty()) {
            ResponseEntity.ok(products)
        } else {
            ResponseEntity.noContent().build()
        }
    }


    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Product> {
        return productService.findById(id).let {
            ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        return productService.deleteById(id).let {
            ResponseEntity.noContent().build()
        }
    }
}

@RestController
@RequestMapping("/motherboardDetails")
class MotherboardDetailsController(
    private val motherboardDetailsService: MotherboardDetailsService,
    private val productService: ProductService,
    private val imageService: ImageService
) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): ResponseEntity<MotherboardDetails> {
        return try {
            val motherboardDetails = motherboardDetailsService.findByProductId(productId)
            ResponseEntity.ok(motherboardDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/batch")
    fun save(@Valid @RequestBody motherboardDetailsList: List<MotherboardDetails>): ResponseEntity<Any> {
        return try {
            val savedMotherboardDetailsList = motherboardDetailsList.map { motherboardDetailsService.save(it) }
            ResponseEntity.ok(savedMotherboardDetailsList)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }


    @PostMapping
    fun save(
        @Valid @RequestPart("motherboardDetails") motherboardDetails: MotherboardDetails,
        @RequestPart("images") images: List<MultipartFile>
    ): ResponseEntity<Any> {
        return try {
            val savedProduct = productService.save(motherboardDetails.product!!)
            val imageEntities = images.map { imageService.saveImage(it, savedProduct) }
            savedProduct.images.addAll(imageEntities)
            val savedMotherboardDetails = motherboardDetailsService.save(motherboardDetails)

            ResponseEntity.ok(savedMotherboardDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }


    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(): ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Falta un campo requerido en la solicitud")
    }

}

@RestController
@RequestMapping("/coolingDetails")
class CoolingDetailsController(private val coolingDetailsService: CoolingDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): ResponseEntity<CoolingDetails> {
        return try {
            val coolingDetails = coolingDetailsService.findByProductId(productId)
            ResponseEntity.ok(coolingDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/batch")
    fun save(@Valid @RequestBody coolingDetailsList: List<CoolingDetails>): ResponseEntity<Any> {
        return try {
            val savedCoolingDetailsList = coolingDetailsList.map { coolingDetailsService.save(it) }
            ResponseEntity.ok(savedCoolingDetailsList)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping
    fun save(@Valid @RequestBody coolingDetails: CoolingDetails): ResponseEntity<Any> {
        return try {
            val savedCoolingDetails = coolingDetailsService.save(coolingDetails)
            ResponseEntity.ok(savedCoolingDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}

@RestController
@RequestMapping("/powerSupplyDetails")
class PowerSupplyDetailsController(private val powerSupplyDetailsService: PowerSupplyDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): ResponseEntity<PowerSupplyDetails> {
        return try {
            val powerSupplyDetails = powerSupplyDetailsService.findByProductId(productId)
            ResponseEntity.ok(powerSupplyDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/batch")
    fun save(@Valid @RequestBody powerSupplyDetailsList: List<PowerSupplyDetails>): ResponseEntity<Any> {
        return try {
            val savedPowerSupplyDetailsList = powerSupplyDetailsList.map { powerSupplyDetailsService.save(it) }
            ResponseEntity.ok(savedPowerSupplyDetailsList)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping
    fun save(@Valid @RequestBody powerSupplyDetails: PowerSupplyDetails): ResponseEntity<Any> {
        return try {
            val savedPowerSupplyDetails = powerSupplyDetailsService.save(powerSupplyDetails)
            ResponseEntity.ok(savedPowerSupplyDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}

@RestController
@RequestMapping("/processorDetails")
class ProcessorDetailsController(private val processorDetailsService: ProcessorDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): ResponseEntity<ProcessorDetails> {
        return try {
            val processorDetails = processorDetailsService.findByProductId(productId)
            ResponseEntity.ok(processorDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/batch")
    fun save(@Valid @RequestBody processorDetailsList: List<ProcessorDetails>): ResponseEntity<Any> {
        return try {
            val savedProcessorDetailsList = processorDetailsList.map { processorDetailsService.save(it) }
            ResponseEntity.ok(savedProcessorDetailsList)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping
    fun save(@Valid @RequestBody processorDetails: ProcessorDetails): ResponseEntity<Any> {
        return try {
            val savedProcessorDetails = processorDetailsService.save(processorDetails)
            ResponseEntity.ok(savedProcessorDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}

@RestController
@RequestMapping("/RAMDetails")
class RAMDetailsController(private val ramDetailsService: RAMDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): ResponseEntity<RAMDetails> {
        return try {
            val ramDetails = ramDetailsService.findByProductId(productId)
            ResponseEntity.ok(ramDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/batch")
    fun save(@Valid @RequestBody ramDetailsList: List<RAMDetails>): ResponseEntity<Any> {
        return try {
            val savedRAMDetailsList = ramDetailsList.map { ramDetailsService.save(it) }
            ResponseEntity.ok(savedRAMDetailsList)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping
    fun save(@Valid @RequestBody ramDetails: RAMDetails): ResponseEntity<Any> {
        return try {
            val savedRAMDetails = ramDetailsService.save(ramDetails)
            ResponseEntity.ok(savedRAMDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}

@RestController
@RequestMapping("/graphicsCardDetails")
class GraphicsCardDetailsController(private val graphicsCardDetailsService: GraphicsCardDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): ResponseEntity<GraphicsCardDetails> {
        return try {
            val graphicsCardDetails = graphicsCardDetailsService.findByProductId(productId)
            ResponseEntity.ok(graphicsCardDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/batch")
    fun save(@Valid @RequestBody graphicsCardDetailsList: List<GraphicsCardDetails>): ResponseEntity<Any> {
        return try {
            val savedGraphicsCardDetailsList = graphicsCardDetailsList.map { graphicsCardDetailsService.save(it) }
            ResponseEntity.ok(savedGraphicsCardDetailsList)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping
    fun save(@Valid @RequestBody graphicsCardDetails: GraphicsCardDetails): ResponseEntity<Any> {
        return try {
            val savedGraphicsCardDetails = graphicsCardDetailsService.save(graphicsCardDetails)
            ResponseEntity.ok(savedGraphicsCardDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}

@RestController
@RequestMapping("/storageDetails")
class StorageDetailsController(private val storageDetailsService: StorageDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): ResponseEntity<StorageDetails> {
        return try {
            val storageDetails = storageDetailsService.findByProductId(productId)
            ResponseEntity.ok(storageDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/batch")
    fun save(@Valid @RequestBody storageDetailsList: List<StorageDetails>): ResponseEntity<Any> {
        return try {
            val savedStorageDetailsList = storageDetailsList.map { storageDetailsService.save(it) }
            ResponseEntity.ok(savedStorageDetailsList)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping
    fun save(@Valid @RequestBody storageDetails: StorageDetails): ResponseEntity<Any> {
        return try {
            val savedStorageDetails = storageDetailsService.save(storageDetails)
            ResponseEntity.ok(savedStorageDetails)
        } catch (e: RuntimeException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}


@RestController
@RequestMapping("/salesHistory")
class SalesHistoryController(private val salesHistoryService: SalesHistoryService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): SalesHistory {
        try {
            return salesHistoryService.findByProductId(productId)
        } catch (e: RuntimeException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "SalesHistory not found", e
            )
        }
    }

    @PostMapping
    fun save(@RequestBody salesHistory: SalesHistory): SalesHistory {
        return salesHistoryService.save(salesHistory)
    }
}

@RestController
@RequestMapping("/orders")
class OrdersController(private val ordersService: OrdersService) {

    @GetMapping("/{userId}")
    fun findByUserId(@PathVariable userId: Long): Orders {
        try {
            return ordersService.findByUserId(userId)
        } catch (e: RuntimeException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "Orders not found", e
            )
        }
    }

    @PostMapping
    fun save(@RequestBody orders: Orders): Orders {
        return ordersService.save(orders)
    }
}

@RestController
@RequestMapping("/orderDetails")
class OrderDetailsController(private val orderDetailsService: OrderDetailsService) {

    @GetMapping("/{orderId}")
    fun findByOrderId(@PathVariable orderId: Long): OrderDetails {
        try {
            return orderDetailsService.findByOrderId(orderId)
        } catch (e: RuntimeException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "OrderDetails not found", e
            )
        }
    }

    @PostMapping
    fun save(@RequestBody orderDetails: OrderDetails): OrderDetails {
        return orderDetailsService.save(orderDetails)
    }
}
