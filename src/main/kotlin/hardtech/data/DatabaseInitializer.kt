package hardtech.data

import hardtech.entity.Authority
import hardtech.entity.Categories
import hardtech.repository.AuthorityRepository
import hardtech.repository.CategoriesRepository
import jakarta.annotation.PostConstruct

//@Component
class CategoriesInitializer(private val categoriesRepository: CategoriesRepository) {

    @PostConstruct
    fun init() {
        val defaultCategories = listOf(
            Categories(categoryName = "Cooling"),
            Categories(categoryName = "Graphics Card"),
            Categories(categoryName = "Motherboard"),
            Categories(categoryName = "Power Supply"),
            Categories(categoryName = "Processor"),
            Categories(categoryName = "RAM"),
            Categories(categoryName = "Storage"),
//            Categories(categoryId = 8, categoryName = "Categoría de prueba")
        )

        categoriesRepository.saveAll(defaultCategories)
    }
}
//@Component
class AuthorityInitializer(private val authorityRepository: AuthorityRepository) {

    @PostConstruct
    fun init() {
        val defaultAuthorities = listOf(
            Authority(authorityName = "ROLE_ADMIN"),
            Authority(authorityName = "ROLE_USER")
        )

        authorityRepository.saveAll(defaultAuthorities)
    }
}