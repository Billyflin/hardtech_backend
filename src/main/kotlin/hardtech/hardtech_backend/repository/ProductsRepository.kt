package hardtech.hardtech_backend.repository

import hardtech.hardtech_backend.models.*
import org.springframework.data.jpa.repository.JpaRepository

interface ProcessorRepository : JpaRepository<Processor, Long> {
    fun findByName(name: String): Processor?
}

interface GraphicsCardRepository : JpaRepository<GraphicsCard, Long> {
    fun findByName(name: String): GraphicsCard?

}

interface StorageRepository : JpaRepository<Storage, Long> {
    fun findByName(name: String): Storage?
}

interface CoolingRepository : JpaRepository<Cooling, Long> {
    fun findByName(name: String): Cooling?
}

interface PowerSupplyRepository : JpaRepository<PowerSupply, Long> {
    fun findByName(name: String): PowerSupply?
}

interface RamRepository : JpaRepository<Ram, Long> {
    fun findByName(name: String): Ram?
}

