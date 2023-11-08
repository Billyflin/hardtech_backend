package hardtech.hardtech_backend.service

import hardtech.hardtech_backend.models.*
import hardtech.hardtech_backend.repository.*
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class ProcessorService(private val processorRepository: ProcessorRepository) {

    fun getAllProcessors(): List<Processor> = processorRepository.findAll()

    fun getProcessorById(id: Long): Processor =
        processorRepository.findById(id).orElseThrow { ResourceNotFoundException("Processor not found") }

    fun createProcessor(processor: Processor): Processor = processorRepository.save(processor)

    fun updateProcessor(id: Long, processor: Processor): Processor {
        val existingProcessor = getProcessorById(id)
        existingProcessor.brand = processor.brand
        existingProcessor.name = processor.name
        // ... actualizar los demás campos ...
        return processorRepository.save(existingProcessor)
    }

    fun deleteProcessor(id: Long) = processorRepository.deleteById(id)
}

@Service
class GraphicsCardService(private val graphicsCardRepository: GraphicsCardRepository) {

    fun getAllGraphicsCards(): List<GraphicsCard> = graphicsCardRepository.findAll()

    fun createGraphicsCard(graphicsCard: GraphicsCard): GraphicsCard = graphicsCardRepository.save(graphicsCard)

    // Agrega aquí más métodos según sea necesario
}

// Haz lo mismo para las demás entidades (RAM, Storage, PowerSupply, Cooling)
