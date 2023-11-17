package hardtech.service

import hardtech.entity.*
import hardtech.repository.*
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
        existingProcessor.price = processor.price
        existingProcessor.gen = processor.gen
        existingProcessor.cores = processor.cores
        existingProcessor.threads = processor.threads
        existingProcessor.speed = processor.speed
        existingProcessor.turbo = processor.turbo
        existingProcessor.tdp = processor.tdp
        existingProcessor.socket = processor.socket
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
        // Validar que el nombre no esté en uso
        val existingProcessor = processorRepository.findByName(processor.name!!)
        if (existingProcessor != null && existingProcessor.id != processor.id) {
            throw IllegalArgumentException("El nombre ya está en uso")
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
        existingStorage.price = storage.price
        existingStorage.type = storage.type
        existingStorage.capacity = storage.capacity
        existingStorage.readSpeed = storage.readSpeed
        existingStorage.writeSpeed = storage.writeSpeed
        validateStorage(existingStorage)
        return storageRepository.save(existingStorage)
    }


    fun deleteStorage(id: Long) {
        logger.info("Deleting storage with id: $id")
        storageRepository.deleteById(id)
    }

    private fun validateStorage(storage: Storage) {
        // Validar que la marca no esté en blanco
        if (storage.brand.isBlank()) {
            throw IllegalArgumentException("La marca es obligatoria")
        }

        // Validar que el nombre no esté en blanco
        if (storage.name.isBlank()) {
            throw IllegalArgumentException("El nombre es obligatorio")
        }

        // Validar que el precio sea mayor a 0
        if (storage.price <= 0) {
            throw IllegalArgumentException("El precio debe ser mayor a 0")
        }

        // Validar que el tipo no esté en blanco
        if (storage.type.isNullOrBlank()) {
            throw IllegalArgumentException("El tipo es obligatorio")
        }

        // Validar que la capacidad sea mayor a 0
        if (storage.capacity <= 0) {
            throw IllegalArgumentException("La capacidad debe ser mayor a 0")
        }

        // Validar que la velocidad de lectura sea mayor a 0
        if (storage.readSpeed <= 0) {
            throw IllegalArgumentException("La velocidad de lectura debe ser mayor a 0")
        }

        // Validar que la velocidad de escritura sea mayor a 0
        if (storage.writeSpeed <= 0) {
            throw IllegalArgumentException("La velocidad de escritura debe ser mayor a 0")
        }

        // Validar que el nombre no esté en uso
        val existingStorage = storageRepository.findByName(storage.name)
        if (existingStorage != null && existingStorage.id != storage.id) {
            throw IllegalArgumentException("El nombre ya está en uso")
        }
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
        existingCooling.price = cooling.price
        existingCooling.type = cooling.type
        existingCooling.fans = cooling.fans
        existingCooling.fanSize = cooling.fanSize
        existingCooling.fanRpm = cooling.fanRpm
        existingCooling.noiseLevel = cooling.noiseLevel
        existingCooling.color = cooling.color
        existingCooling.rgb = cooling.rgb
        existingCooling.rgbHeader = cooling.rgbHeader
        existingCooling.blockRgb = cooling.blockRgb
        validateCooling(existingCooling)
        return coolingRepository.save(existingCooling)
    }

    private fun validateCooling(cooling: Cooling) {
        // Validar que la marca no esté en blanco
        if (cooling.brand.isBlank()) {
            throw IllegalArgumentException("La marca es obligatoria")
        }

        // Validar que el nombre no esté en blanco
        if (cooling.name.isBlank()) {
            throw IllegalArgumentException("El nombre es obligatorio")
        }

        // Validar que el precio sea mayor a 0
        if (cooling.price <= 0) {
            throw IllegalArgumentException("El precio debe ser mayor a 0")
        }

        // Validar que el tipo no esté en blanco
        if (cooling.type.isBlank()) {
            throw IllegalArgumentException("El tipo es obligatorio")
        }

        // Validar que los ventiladores sean mayores a 0
        if (cooling.fans <= 0) {
            throw IllegalArgumentException("Los ventiladores deben ser mayores a 0")
        }

        // Validar que el tamaño del ventilador sea mayor a 0
        if (cooling.fanSize <= 0) {
            throw IllegalArgumentException("El tamaño del ventilador debe ser mayor a 0")
        }

        // Validar que las RPM del ventilador sean mayores a 0
        if (cooling.fanRpm <= 0) {
            throw IllegalArgumentException("Las RPM del ventilador deben ser mayores a 0")
        }

        // Validar que el nivel de ruido sea mayor a 0
        if (cooling.noiseLevel <= 0) {
            throw IllegalArgumentException("El nivel de ruido debe ser mayor a 0")
        }

        // Validar que el color no esté en blanco
        if (cooling.color.isBlank()) {
            throw IllegalArgumentException("El color es obligatorio")
        }
        // Validar que el nombre no esté en uso
        val existingCooling = coolingRepository.findByName(cooling.name)
        if (existingCooling != null && existingCooling.id != cooling.id) {
            throw IllegalArgumentException("El nombre ya está en uso")
        }
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
        existingPowerSupply.price = powerSupply.price
        existingPowerSupply.watts = powerSupply.watts
        existingPowerSupply.efficiency = powerSupply.efficiency
        existingPowerSupply.modular = powerSupply.modular
        validatePowerSupply(existingPowerSupply)
        return powerSupplyRepository.save(existingPowerSupply)
    }

    private fun validatePowerSupply(powerSupply: PowerSupply) {
        // Validar que la marca no esté en blanco
        if (powerSupply.brand.isBlank()) {
            throw IllegalArgumentException("La marca es obligatoria")
        }

        // Validar que el nombre no esté en blanco
        if (powerSupply.name.isBlank()) {
            throw IllegalArgumentException("El nombre es obligatorio")
        }

        // Validar que el precio sea mayor a 0
        if (powerSupply.price <= 0) {
            throw IllegalArgumentException("El precio debe ser mayor a 0")
        }

        // Validar que los watts sean mayores a 0
        if (powerSupply.watts <= 0) {
            throw IllegalArgumentException("Los watts deben ser mayores a 0")
        }

        // Validar que la eficiencia no esté en blanco
        if (powerSupply.efficiency.isBlank()) {
            throw IllegalArgumentException("La eficiencia es obligatoria")
        }

        // Validar que el modular no esté en blanco
        if (powerSupply.modular.isBlank()) {
            throw IllegalArgumentException("El modular es obligatorio")
        }
        // Validar que el nombre no esté en uso
        val existingPowerSupply = powerSupplyRepository.findByName(powerSupply.name)
        if (existingPowerSupply != null && existingPowerSupply.id != powerSupply.id) {
            throw IllegalArgumentException("El nombre ya está en uso")
        }
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
        existingGraphicsCard.price = graphicsCard.price
        existingGraphicsCard.chipset = graphicsCard.chipset
        existingGraphicsCard.memoryType = graphicsCard.memoryType
        existingGraphicsCard.memory = graphicsCard.memory
        existingGraphicsCard.speed = graphicsCard.speed
        existingGraphicsCard.tdp = graphicsCard.tdp
        existingGraphicsCard.power = graphicsCard.power
        existingGraphicsCard.length = graphicsCard.length
        existingGraphicsCard.fans = graphicsCard.fans
        existingGraphicsCard.displayPorts = graphicsCard.displayPorts
        existingGraphicsCard.hdmi = graphicsCard.hdmi
        existingGraphicsCard.vga = graphicsCard.vga
        validateGraphicsCard(existingGraphicsCard)
        return graphicsCardRepository.save(existingGraphicsCard)
    }


    fun deleteGraphicsCard(id: Long) {
        logger.info("Deleting graphics card with id: $id")
        graphicsCardRepository.deleteById(id)
    }

    private fun validateGraphicsCard(graphicsCard: GraphicsCard) {
        // Validar que la marca no esté en blanco
        if (graphicsCard.brand.isBlank()) {
            throw IllegalArgumentException("La marca es obligatoria")
        }

        // Validar que el nombre no esté en blanco
        if (graphicsCard.name.isBlank()) {
            throw IllegalArgumentException("El nombre es obligatorio")
        }

        // Validar que el precio sea mayor a 0
        if (graphicsCard.price <= 0) {
            throw IllegalArgumentException("El precio debe ser mayor a 0")
        }

        // Validar que el chipset no esté en blanco
        if (graphicsCard.chipset.isBlank()) {
            throw IllegalArgumentException("El chipset es obligatorio")
        }

        // Validar que el tipo de memoria no esté en blanco
        if (graphicsCard.memoryType.isBlank()) {
            throw IllegalArgumentException("El tipo de memoria es obligatorio")
        }

        // Validar que la memoria sea mayor a 0
        if (graphicsCard.memory <= 0) {
            throw IllegalArgumentException("La memoria debe ser mayor a 0")
        }

        // Validar que la velocidad sea mayor a 0
        if (graphicsCard.speed <= 0) {
            throw IllegalArgumentException("La velocidad debe ser mayor a 0")
        }

        // Validar que el TDP sea mayor a 0
        if (graphicsCard.tdp <= 0) {
            throw IllegalArgumentException("El TDP debe ser mayor a 0")
        }

        // Validar que la potencia sea mayor a 0
        if (graphicsCard.power <= 0) {
            throw IllegalArgumentException("La potencia debe ser mayor a 0")
        }

        // Validar que la longitud sea mayor a 0
        if (graphicsCard.length <= 0) {
            throw IllegalArgumentException("La longitud debe ser mayor a 0")
        }

        // Validar que los ventiladores sean mayores a 0
        if (graphicsCard.fans <= 0) {
            throw IllegalArgumentException("Los ventiladores deben ser mayores a 0")
        }

        // Validar que los puertos de pantalla sean mayores a 0
        if (graphicsCard.displayPorts <= 0) {
            throw IllegalArgumentException("Los puertos de pantalla deben ser mayores a 0")
        }

        // Validar que HDMI no sea negativo
        if (graphicsCard.hdmi <= -1) {
            throw IllegalArgumentException("HDMI debe ser mayor a 0")
        }

        // Validar que VGA no sea negativo
        if (graphicsCard.vga <= -1) {
            throw IllegalArgumentException("VGA debe ser mayor a 0")
        }
        val existingGraphicsCard = graphicsCardRepository.findByName(graphicsCard.name)
        if (existingGraphicsCard != null && existingGraphicsCard.id != graphicsCard.id) {
            throw IllegalArgumentException("El nombre ya está en uso")
        }
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
        existingRam.price = ram.price
        existingRam.type = ram.type
        existingRam.speed = ram.speed
        existingRam.size = ram.size
        existingRam.modules = ram.modules
        existingRam.cas = ram.cas
        existingRam.voltage = ram.voltage
        validateRam(existingRam)
        return ramRepository.save(existingRam)
    }

    private fun validateRam(ram: Ram) {
        // Validar que la marca no esté en blanco
        if (ram.brand.isBlank()) {
            throw IllegalArgumentException("La marca es obligatoria")
        }

        // Validar que el nombre no esté en blanco
        if (ram.name.isBlank()) {
            throw IllegalArgumentException("El nombre es obligatorio")
        }

        // Validar que el precio sea mayor a 0
        if (ram.price <= 0) {
            throw IllegalArgumentException("El precio debe ser mayor a 0")
        }

        // Validar que el tipo no esté en blanco
        if (ram.type.isBlank()) {
            throw IllegalArgumentException("El tipo es obligatorio")
        }

        // Validar que la velocidad sea mayor a 0
        if (ram.speed <= 0) {
            throw IllegalArgumentException("La velocidad debe ser mayor a 0")
        }

        // Validar que el tamaño sea mayor a 0
        if (ram.size <= 0) {
            throw IllegalArgumentException("El tamaño debe ser mayor a 0")
        }

        // Validar que los módulos sean mayores a 0
        if (ram.modules <= 0) {
            throw IllegalArgumentException("Los módulos deben ser mayores a 0")
        }

        // Validar que CAS sea mayor a 0
        if (ram.cas <= 0) {
            throw IllegalArgumentException("CAS debe ser mayor a 0")
        }

        // Validar que el voltaje sea mayor a 0
        if (ram.voltage <= 0) {
            throw IllegalArgumentException("El voltaje debe ser mayor a 0")
        }
        // Validar que el nombre no esté en uso
        val existingRam = ramRepository.findByName(ram.name)
        if (existingRam != null && existingRam.id != ram.id) {
            throw IllegalArgumentException("El nombre ya está en uso")
        }

    }

    fun deleteRam(id: Long) = ramRepository.deleteById(id)
}
