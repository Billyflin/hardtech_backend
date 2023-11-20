package hardtech.controller

import hardtech.entity.*
import hardtech.service.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun findAll(): List<Product> {
        return productService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Product {
        try {
            return productService.findById(id)
        } catch (e: RuntimeException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "Product not found", e)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) {
        productService.deleteById(id)
    }
}

@RestController
@RequestMapping("/motherboardDetails")
class MotherboardDetailsController(private val motherboardDetailsService: MotherboardDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): MotherboardDetails {
        try {
            return motherboardDetailsService.findByProductId(productId)
        } catch (e: RuntimeException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "MotherboardDetails not found", e)
        }
    }

    @PostMapping
    fun save(@RequestBody motherboardDetails: MotherboardDetails): MotherboardDetails {
        return motherboardDetailsService.save(motherboardDetails)
    }
}


@RestController
@RequestMapping("/powerSupplyDetails")
class PowerSupplyDetailsController(private val powerSupplyDetailsService: PowerSupplyDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): PowerSupplyDetails {
        try {
            return powerSupplyDetailsService.findByProductId(productId)
        } catch (e: RuntimeException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "PowerSupplyDetails not found", e)
        }
    }

    @PostMapping
    fun save(@RequestBody powerSupplyDetails: PowerSupplyDetails): PowerSupplyDetails {
        return powerSupplyDetailsService.save(powerSupplyDetails)
    }
}

@RestController
@RequestMapping("/processorDetails")
class ProcessorDetailsController(private val processorDetailsService: ProcessorDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): ProcessorDetails {
        try {
            return processorDetailsService.findByProductId(productId)
        } catch (e: RuntimeException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ProcessorDetails not found", e)
        }
    }

    @PostMapping
    fun save(@RequestBody processorDetails: ProcessorDetails): ProcessorDetails {
        return processorDetailsService.save(processorDetails)
    }
}

@RestController
@RequestMapping("/ramDetails")
class RAMDetailsController(private val ramDetailsService: RAMDetailsService) {

    @GetMapping("/{productId}")
    fun findByProductId(@PathVariable productId: Long): RAMDetails {
        try {
            return ramDetailsService.findByProductId(productId)
        } catch (e: RuntimeException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "RAMDetails not found", e)
        }
    }

    @PostMapping
    fun save(@RequestBody ramDetails: RAMDetails): RAMDetails {
        return ramDetailsService.save(ramDetails)
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
                HttpStatus.NOT_FOUND, "SalesHistory not found", e)
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
                HttpStatus.NOT_FOUND, "Orders not found", e)
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
                HttpStatus.NOT_FOUND, "OrderDetails not found", e)
        }
    }

    @PostMapping
    fun save(@RequestBody orderDetails: OrderDetails): OrderDetails {
        return orderDetailsService.save(orderDetails)
    }
}
