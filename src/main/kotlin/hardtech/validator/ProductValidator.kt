package hardtech.validator

import hardtech.entity.Product
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class ProductValidator : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return Product::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val product = target as Product
        if (product.price < 0) {
            errors.rejectValue("price", "price.negative", "El precio no puede ser negativo")
        }
        if (product.productName.isBlank()) {
            errors.rejectValue("productName", "productName.blank", "El nombre del producto no puede estar vacío")
        }
        if (product.brand.isBlank()) {
            errors.rejectValue("brand", "brand.blank", "La marca no puede estar vacía")
        }
        if (product.category == null) {
            errors.rejectValue("category", "category.null", "La categoría no puede ser nula")
        }
    }
}
