package hardtech.service

import hardtech.dto.ImageDTO
import hardtech.entity.Image
import hardtech.entity.Product
import hardtech.repository.ImageRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ImageService(private val imageRepository: ImageRepository) {

    fun saveImage(image: MultipartFile, product: Product): Image {
        val bytes = image.bytes
        val imageEntity = Image(imageData = bytes, product = product)
        return imageRepository.save(imageEntity)
    }

    fun findImageById(id: Long): ImageDTO {
        val image = imageRepository.findById(id).orElseThrow { RuntimeException("Image not found") }
        val imageDataBase64 = Base64.getEncoder().encodeToString(image.imageData)
        return ImageDTO(image.imageId, imageDataBase64)
    }
}