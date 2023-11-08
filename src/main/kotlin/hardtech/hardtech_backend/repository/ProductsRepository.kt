package hardtech.hardtech_backend.repository

import hardtech.hardtech_backend.models.*
import org.springframework.data.jpa.repository.JpaRepository

interface ProcessorRepository : JpaRepository<Processor, Long>

interface GraphicsCardRepository : JpaRepository<GraphicsCard, Long>

interface StorageRepository : JpaRepository<Storage, Long>

interface CoolingRepository : JpaRepository<Cooling, Long>

interface PowerSupplyRepository : JpaRepository<PowerSupply, Long>

interface RamRepository : JpaRepository<Ram, Long>

// Haz lo mismo para las demás entidades (RAM, Storage, PowerSupply, Cooling)
