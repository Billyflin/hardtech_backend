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
    fun getAllGraphicsCards(): List<GraphicsCard> = graphicsCardService.getAllGraphicsCards()

    @PostMapping
    fun createGraphicsCard(@RequestBody graphicsCard: GraphicsCard): GraphicsCard =
        graphicsCardService.createGraphicsCard(graphicsCard)

    // Agrega aquí más métodos según sea necesario
}

// Haz lo mismo para las demás entidades (RAM, Storage, PowerSupply, Cooling)
