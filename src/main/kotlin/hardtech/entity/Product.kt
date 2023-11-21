package hardtech.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import hardtech.entity.products.MotherboardDetails
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "product")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val productId: Long = 0,

    @Column(unique = true) @field:NotBlank(message = "El nombre del producto no puede estar vacío") val productName: String,

    @field:NotBlank(message = "La marca no puede estar vacía") val brand: String,

    @field:Min(value = 0, message = "El precio debe ser un número positivo") val price: Double,

    @field:NotBlank(message = "La descripción no puede estar vacía") val description: String,

    @field:Min(value = 0, message = "La calificacion no puede ser menor a 0") val rating: Int,

    @ManyToOne @JoinColumn(name = "categoryId") @JsonManagedReference @field:NotNull(message = "La categoría no puede ser nula") val category: Categories,

    @OneToOne(
        cascade = [CascadeType.ALL], mappedBy = "product"
    ) @JsonBackReference val motherboardDetails: MotherboardDetails? = null,

    @Column(nullable = false) val stock: Int = 0,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    @JsonManagedReference
    val images: MutableList<Image> = mutableListOf()
)