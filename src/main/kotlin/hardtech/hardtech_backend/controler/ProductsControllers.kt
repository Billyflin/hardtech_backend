package hardtech.hardtech_backend.controler

import hardtech.hardtech_backend.models.*
import hardtech.hardtech_backend.service.*
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/processors")
class ProcessorController(private val processorService: ProcessorService) {

    private val logger = LoggerFactory.getLogger(ProcessorController::class.java)

    @GetMapping
    fun getAllProcessors(): ResponseEntity<List<Processor>> {
        logger.info("Getting all processors")
        val processors = processorService.getAllProcessors()
        return ResponseEntity.ok(processors)
    }

    @GetMapping("/{id}")
    fun getProcessorById(@PathVariable id: Long): ResponseEntity<Processor> {
        logger.info("Getting processor with id: $id")
        val processor = processorService.getProcessorById(id)
        return ResponseEntity.ok(processor)
    }

    @PostMapping
    fun createProcessor(@Valid @RequestBody processor: Processor): ResponseEntity<Processor> {
        logger.info("Creating processor: $processor")
        val createdProcessor = processorService.createProcessor(processor)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcessor)
    }

    @PutMapping("/{id}")
    fun updateProcessor(@PathVariable id: Long, @Valid @RequestBody processor: Processor): ResponseEntity<Processor> {
        logger.info("Updating processor with id: $id")
        val updatedProcessor = processorService.updateProcessor(id, processor)
        return ResponseEntity.ok(updatedProcessor)
    }

    @DeleteMapping("/{id}")
    fun deleteProcessor(@PathVariable id: Long): ResponseEntity<Void> {
        logger.info("Deleting processor with id: $id")
        processorService.deleteProcessor(id)
        return ResponseEntity.noContent().build()
    }
}

@RestController
@RequestMapping("/api/graphicsCards")
class GraphicsCardController(private val graphicsCardService: GraphicsCardService) {

    @GetMapping
    fun getAllGraphicsCards(): ResponseEntity<List<GraphicsCard>> {
        val graphicsCards = graphicsCardService.getAllGraphicsCards()
        return ResponseEntity.ok(graphicsCards)
    }

    @GetMapping("/{id}")
    fun getGraphicsCardById(@PathVariable id: Long): ResponseEntity<GraphicsCard> {
        val graphicsCard = graphicsCardService.getGraphicsCardById(id)
        return ResponseEntity.ok(graphicsCard)
    }

    @PostMapping
    fun createGraphicsCard(@Valid @RequestBody graphicsCard: GraphicsCard): ResponseEntity<GraphicsCard> {
        val createdGraphicsCard = graphicsCardService.createGraphicsCard(graphicsCard)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGraphicsCard)
    }

    @PutMapping("/{id}")
    fun updateGraphicsCard(
        @PathVariable id: Long, @Valid @RequestBody graphicsCard: GraphicsCard
    ): ResponseEntity<GraphicsCard> {
        val updatedGraphicsCard = graphicsCardService.updateGraphicsCard(id, graphicsCard)
        return ResponseEntity.ok(updatedGraphicsCard)
    }

    @DeleteMapping("/{id}")
    fun deleteGraphicsCard(@PathVariable id: Long): ResponseEntity<Void> {
        graphicsCardService.deleteGraphicsCard(id)
        return ResponseEntity.noContent().build()
    }
}

@RestController
@RequestMapping("/api/rams")
class RamController(private val ramService: RamService) {

    @GetMapping
    fun getAllRams(): ResponseEntity<List<Ram>> {
        val rams = ramService.getAllRams()
        return ResponseEntity.ok(rams)
    }

    @GetMapping("/{id}")
    fun getRamById(@PathVariable id: Long): ResponseEntity<Ram> {
        val ram = ramService.getRamById(id)
        return ResponseEntity.ok(ram)
    }

    @PostMapping
    fun createRam(@Valid @RequestBody ram: Ram): ResponseEntity<Ram> {
        val createdRam = ramService.createRam(ram)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRam)
    }

    @PutMapping("/{id}")
    fun updateRam(@PathVariable id: Long, @Valid @RequestBody ram: Ram): ResponseEntity<Ram> {
        val updatedRam = ramService.updateRam(id, ram)
        return ResponseEntity.ok(updatedRam)
    }

    @DeleteMapping("/{id}")
    fun deleteRam(@PathVariable id: Long): ResponseEntity<Void> {
        ramService.deleteRam(id)
        return ResponseEntity.noContent().build()
    }
}

@RestController
@RequestMapping("/api/storages")
class StorageController(private val storageService: StorageService) {

    @GetMapping
    fun getAllStorages(): ResponseEntity<List<Storage>> {
        val storages = storageService.getAllStorages()
        return ResponseEntity.ok(storages)
    }

    @GetMapping("/{id}")
    fun getStorageById(@PathVariable id: Long): ResponseEntity<Storage> {
        val storage = storageService.getStorageById(id)
        return ResponseEntity.ok(storage)
    }

    @PostMapping
    fun createStorage(@Valid @RequestBody storage: Storage): ResponseEntity<Storage> {
        val createdStorage = storageService.createStorage(storage)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStorage)
    }

    @PutMapping("/{id}")
    fun updateStorage(@PathVariable id: Long, @Valid @RequestBody storage: Storage): ResponseEntity<Storage> {
        val updatedStorage = storageService.updateStorage(id, storage)
        return ResponseEntity.ok(updatedStorage)
    }

    @DeleteMapping("/{id}")
    fun deleteStorage(@PathVariable id: Long): ResponseEntity<Void> {
        storageService.deleteStorage(id)
        return ResponseEntity.noContent().build()
    }
}

@RestController
@RequestMapping("/api/coolings")
class CoolingController(private val coolingService: CoolingService) {

    @GetMapping
    fun getAllCoolings(): ResponseEntity<List<Cooling>> {
        val coolings = coolingService.getAllCoolings()
        return ResponseEntity.ok(coolings)
    }

    @GetMapping("/{id}")
    fun getCoolingById(@PathVariable id: Long): ResponseEntity<Cooling> {
        val cooling = coolingService.getCoolingById(id)
        return ResponseEntity.ok(cooling)
    }

    @PostMapping
    fun createCooling(@Valid @RequestBody cooling: Cooling): ResponseEntity<Cooling> {
        val createdCooling = coolingService.createCooling(cooling)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCooling)
    }

    @PutMapping("/{id}")
    fun updateCooling(@PathVariable id: Long, @Valid @RequestBody cooling: Cooling): ResponseEntity<Cooling> {
        val updatedCooling = coolingService.updateCooling(id, cooling)
        return ResponseEntity.ok(updatedCooling)
    }

    @DeleteMapping("/{id}")
    fun deleteCooling(@PathVariable id: Long): ResponseEntity<Void> {
        coolingService.deleteCooling(id)
        return ResponseEntity.noContent().build()
    }
}

@RestController
@RequestMapping("/api/powerSupplies")
class PowerSupplyController(private val powerSupplyService: PowerSupplyService) {

    @GetMapping
    fun getAllPowerSupplies(): ResponseEntity<List<PowerSupply>> {
        val powerSupplies = powerSupplyService.getAllPowerSupplies()
        return ResponseEntity.ok(powerSupplies)
    }

    @GetMapping("/{id}")
    fun getPowerSupplyById(@PathVariable id: Long): ResponseEntity<PowerSupply> {
        val powerSupply = powerSupplyService.getPowerSupplyById(id)
        return ResponseEntity.ok(powerSupply)
    }

    @PostMapping
    fun createPowerSupply(@Valid @RequestBody powerSupply: PowerSupply): ResponseEntity<PowerSupply> {
        val createdPowerSupply = powerSupplyService.createPowerSupply(powerSupply)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPowerSupply)
    }

    @PutMapping("/{id}")
    fun updatePowerSupply(
        @PathVariable id: Long, @Valid @RequestBody powerSupply: PowerSupply
    ): ResponseEntity<PowerSupply> {
        val updatedPowerSupply = powerSupplyService.updatePowerSupply(id, powerSupply)
        return ResponseEntity.ok(updatedPowerSupply)
    }

    @DeleteMapping("/{id}")
    fun deletePowerSupply(@PathVariable id: Long): ResponseEntity<Void> {
        powerSupplyService.deletePowerSupply(id)
        return ResponseEntity.noContent().build()
    }
}
