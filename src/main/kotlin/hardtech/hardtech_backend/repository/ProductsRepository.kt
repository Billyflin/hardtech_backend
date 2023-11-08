package hardtech.hardtech_backend.repository

import hardtech.hardtech_backend.models.*
import org.springframework.data.jpa.repository.JpaRepository

interface ProcessorRepository : JpaRepository<Processor, Long>

interface GraphicsCardRepository : JpaRepository<GraphicsCard, Long>

// Haz lo mismo para las demás entidades (RAM, Storage, PowerSupply, Cooling)
