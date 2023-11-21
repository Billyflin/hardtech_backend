package hardtech.dto

import hardtech.entity.Categories

data class ProductDTO(
    val productId: Long,
    val productName: String,
    val brand: String,
    val price: Double,
    val category: Categories,
    val images: List<ImageDTO>
)