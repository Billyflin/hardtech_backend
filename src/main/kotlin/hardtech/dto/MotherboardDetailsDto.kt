package hardtech.dto

/**
 * DTO for {@link hardtech.entity.MotherboardDetails}
 */
data class MotherboardDetailsDto(
    val chipset: String,
    val formFactor: String,
    val memorySlots: Int,
    val memoryType: String,
    val m2Slots: Int,
    val pciEx16Slots: Int,
    val pciEx1Slots: Int,
    val sata3Ports: Int,
    val socket: String,
    val usb2Ports: Int,
    val usb3Ports: Int,
    val product: ProductDto,
    val category: CategoryDto
)

data class CategoryDto(
    val category_id: Long,
    val name: String
)

data class ProductDto(
    val price: Double,
    val brand: String,
    val name: String
)
