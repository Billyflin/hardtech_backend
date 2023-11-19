package hardtech.controller

import hardtech.entity.*
import hardtech.service.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun findAll(): List<Product> {
        return productService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Product {
        return productService.findById(id)
    }

    @PostMapping
    fun save(@RequestBody product: Product): Product {
        return productService.save(product)
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
        return motherboardDetailsService.findByProductId(productId)
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
        return powerSupplyDetailsService.findByProductId(productId)
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
        return processorDetailsService.findByProductId(productId)
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
        return ramDetailsService.findByProductId(productId)
    }

    @PostMapping
    fun save(@RequestBody ramDetails: RAMDetails): RAMDetails {
        return ramDetailsService.save(ramDetails)
    }
}
