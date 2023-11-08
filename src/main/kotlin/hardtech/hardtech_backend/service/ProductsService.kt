package hardtech.hardtech_backend.service

import hardtech.hardtech_backend.models.*
import hardtech.hardtech_backend.repository.*
import org.slf4j.LoggerFactory
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class ProcessorService(private val processorRepository: ProcessorRepository) {

    private val logger = LoggerFactory.getLogger(ProcessorService::class.java)

    fun getAllProcessors(): List<Processor> {
        logger.info("Getting all processors")
        return processorRepository.findAll()
    }

    fun getProcessorById(id: Long): Processor {
        logger.info("Getting processor with id: $id")
        return processorRepository.findById(id).orElseThrow {
            logger.error("Processor with id: $id not found")
            ResourceNotFoundException("Processor not found")
        }
    }

    fun createProcessor(processor: Processor): Processor {
        logger.info("Creating processor: $processor")
        validateProcessor(processor)
        return processorRepository.save(processor)
    }

    fun updateProcessor(id: Long, processor: Processor): Processor {
        logger.info("Updating processor with id: $id")
        val existingProcessor = getProcessorById(id)
        existingProcessor.brand = processor.brand
        existingProcessor.name = processor.name
        // ... actualizar los demás campos ...
        validateProcessor(existingProcessor)
        return processorRepository.save(existingProcessor)
    }

    fun deleteProcessor(id: Long) {
        logger.info("Deleting processor with id: $id")
        processorRepository.deleteById(id)
    }

    private fun validateProcessor(processor: Processor) {
        // Validar que la marca no esté en blanco
        if (processor.brand.isNullOrBlank()) {
            throw IllegalArgumentException("La marca es obligatoria")
        }
        // Validar que el nombre no esté en blanco
        if (processor.name.isNullOrBlank()) {
            throw IllegalArgumentException("El nombre es obligatorio")
        }
        // Validar que el precio sea mayor a 0
        if (processor.price <= 0) {
            throw IllegalArgumentException("El precio debe ser mayor a 0")
        }
        // Validar que la generación sea mayor a 0
        if (processor.gen <= 0) {
            throw IllegalArgumentException("La generación debe ser mayor a 0")
        }
        // Validar que los núcleos sean mayores a 0
        if (processor.cores <= 0) {
            throw IllegalArgumentException("Los núcleos deben ser mayores a 0")
        }
        // Validar que los hilos sean mayores a 0
        if (processor.threads <= 0) {
            throw IllegalArgumentException("Los hilos deben ser mayores a 0")
        }
        // Validar que la velocidad sea mayor a 0
        if (processor.speed <= 0) {
            throw IllegalArgumentException("La velocidad debe ser mayor a 0")
        }
        // Validar que el turbo sea mayor a 0
        if (processor.turbo <= 0) {
            throw IllegalArgumentException("El turbo debe ser mayor a 0")
        }
        // Validar que el TDP sea mayor a 0
        if (processor.tdp <= 0) {
            throw IllegalArgumentException("El TDP debe ser mayor a 0")
        }
        // Validar que el socket no esté en blanco
        if (processor.socket.isBlank()) {
            throw IllegalArgumentException("El socket es obligatorio")
        }
    }
}

@Service
class StorageService(private val storageRepository: StorageRepository) {

    private val logger = LoggerFactory.getLogger(StorageService::class.java)

    fun getAllStorages(): List<Storage> {
        logger.info("Getting all storages")
        return storageRepository.findAll()
    }

    fun getStorageById(id: Long): Storage {
        logger.info("Getting storage with id: $id")
        return storageRepository.findById(id).orElseThrow {
            logger.error("Storage with id: $id not found")
            ResourceNotFoundException("Storage not found")
        }
    }

    fun createStorage(storage: Storage): Storage {
        logger.info("Creating storage: $storage")
        validateStorage(storage)
        return storageRepository.save(storage)
    }

    fun updateStorage(id: Long, storage: Storage): Storage {
        logger.info("Updating storage with id: $id")
        val existingStorage = getStorageById(id)
        existingStorage.brand = storage.brand
        existingStorage.name = storage.name
        // ... actualizar los demás campos ...
        validateStorage(existingStorage)
        return storageRepository.save(existingStorage)
    }

    fun deleteStorage(id: Long) {
        logger.info("Deleting storage with id: $id")
        storageRepository.deleteById(id)
    }

    private fun validateStorage(storage: Storage) {
        // Agrega aquí tu lógica de validación
    }
}

@Service
class CoolingService(private val coolingRepository: CoolingRepository) {

    fun getAllCoolings(): List<Cooling> = coolingRepository.findAll()

    fun getCoolingById(id: Long): Cooling =
        coolingRepository.findById(id).orElseThrow { ResourceNotFoundException("Cooling not found") }

    fun createCooling(cooling: Cooling): Cooling = coolingRepository.save(cooling)

    fun updateCooling(id: Long, cooling: Cooling): Cooling {
        val existingCooling = getCoolingById(id)
        existingCooling.brand = cooling.brand
        existingCooling.name = cooling.name
        // ... actualizar los demás campos ...
        return coolingRepository.save(existingCooling)
    }

    fun deleteCooling(id: Long) = coolingRepository.deleteById(id)
}

@Service
class PowerSupplyService(private val powerSupplyRepository: PowerSupplyRepository) {

    fun getAllPowerSupplies(): List<PowerSupply> = powerSupplyRepository.findAll()

    fun getPowerSupplyById(id: Long): PowerSupply =
        powerSupplyRepository.findById(id).orElseThrow { ResourceNotFoundException("PowerSupply not found") }

    fun createPowerSupply(powerSupply: PowerSupply): PowerSupply = powerSupplyRepository.save(powerSupply)

    fun updatePowerSupply(id: Long, powerSupply: PowerSupply): PowerSupply {
        val existingPowerSupply = getPowerSupplyById(id)
        existingPowerSupply.brand = powerSupply.brand
        existingPowerSupply.name = powerSupply.name
        // ... actualizar los demás campos ...
        return powerSupplyRepository.save(existingPowerSupply)
    }

    fun deletePowerSupply(id: Long) = powerSupplyRepository.deleteById(id)
}

@Service
class GraphicsCardService(private val graphicsCardRepository: GraphicsCardRepository) {

    private val logger = LoggerFactory.getLogger(GraphicsCardService::class.java)

    fun getAllGraphicsCards(): List<GraphicsCard> {
        logger.info("Getting all graphics cards")
        return graphicsCardRepository.findAll()
    }

    fun getGraphicsCardById(id: Long): GraphicsCard {
        logger.info("Getting graphics card with id: $id")
        return graphicsCardRepository.findById(id).orElseThrow {
            logger.error("Graphics card with id: $id not found")
            ResourceNotFoundException("Graphics card not found")
        }
    }

    fun createGraphicsCard(graphicsCard: GraphicsCard): GraphicsCard {
        logger.info("Creating graphics card: $graphicsCard")
        validateGraphicsCard(graphicsCard)
        return graphicsCardRepository.save(graphicsCard)
    }

    fun updateGraphicsCard(id: Long, graphicsCard: GraphicsCard): GraphicsCard {
        logger.info("Updating graphics card with id: $id")
        val existingGraphicsCard = getGraphicsCardById(id)
        existingGraphicsCard.brand = graphicsCard.brand
        existingGraphicsCard.name = graphicsCard.name
        // ... actualizar los demás campos ...
        validateGraphicsCard(existingGraphicsCard)
        return graphicsCardRepository.save(existingGraphicsCard)
    }

    fun deleteGraphicsCard(id: Long) {
        logger.info("Deleting graphics card with id: $id")
        graphicsCardRepository.deleteById(id)
    }

    private fun validateGraphicsCard(graphicsCard: GraphicsCard) {
        // Agrega aquí tu lógica de validación
    }
}


@Service
class RamService(private val ramRepository: RamRepository) {

    fun getAllRams(): List<Ram> = ramRepository.findAll()

    fun getRamById(id: Long): Ram =
        ramRepository.findById(id).orElseThrow { ResourceNotFoundException("Ram not found") }

    fun createRam(ram: Ram): Ram = ramRepository.save(ram)
    fun updateRam(id: Long, ram: Ram): Ram {
        val existingRam = getRamById(id)
        existingRam.brand = ram.brand
        existingRam.name = ram.name
        // ... actualizar los demás campos ...
        return ramRepository.save(existingRam)
    }

    fun deleteRam(id: Long) = ramRepository.deleteById(id)
}


// Haz lo mismo para las demás entidades (RAM, Storage, PowerSupply, Cooling)
