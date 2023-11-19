package hardtech.service


import hardtech.entity.*
import hardtech.repository.*
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    fun findById(id: Long): Product {
        return productRepository.findById(id).orElseThrow { RuntimeException("Product not found") }
    }

    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    fun deleteById(id: Long) {
        return productRepository.deleteById(id)
    }
}

@Service
class MotherboardDetailsService(private val motherboardDetailsRepository: MotherboardDetailsRepository) {

    fun findByProductId(productId: Long): MotherboardDetails {
        return motherboardDetailsRepository.findById(productId).orElseThrow { RuntimeException("MotherboardDetails not found") }
    }

    fun save(motherboardDetails: MotherboardDetails): MotherboardDetails {
        return motherboardDetailsRepository.save(motherboardDetails)
    }
}

@Service
class PowerSupplyDetailsService(private val powerSupplyDetailsRepository: PowerSupplyDetailsRepository) {

    fun findByProductId(productId: Long): PowerSupplyDetails {
        return powerSupplyDetailsRepository.findById(productId).orElseThrow { RuntimeException("PowerSupplyDetails not found") }
    }

    fun save(powerSupplyDetails: PowerSupplyDetails): PowerSupplyDetails {
        return powerSupplyDetailsRepository.save(powerSupplyDetails)
    }
}

@Service
class ProcessorDetailsService(private val processorDetailsRepository: ProcessorDetailsRepository) {

    fun findByProductId(productId: Long): ProcessorDetails {
        return processorDetailsRepository.findById(productId).orElseThrow { RuntimeException("ProcessorDetails not found") }
    }

    fun save(processorDetails: ProcessorDetails): ProcessorDetails {
        return processorDetailsRepository.save(processorDetails)
    }
}

@Service
class RAMDetailsService(private val ramDetailsRepository: RAMDetailsRepository) {

    fun findByProductId(productId: Long): RAMDetails {
        return ramDetailsRepository.findById(productId).orElseThrow { RuntimeException("RAMDetails not found") }
    }

    fun save(ramDetails: RAMDetails): RAMDetails {
        return ramDetailsRepository.save(ramDetails)
    }
}
