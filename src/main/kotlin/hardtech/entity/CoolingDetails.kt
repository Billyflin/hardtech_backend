package hardtech.entity

//


import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "categories")
data class Categories(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val categoryId: Long = 0,

    @Column(unique = true) val categoryName: String
)

@Entity
@Table(name = "product")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val productId: Long = 0,

    @Column(unique = true) @field:NotBlank(message = "El nombre del producto no puede estar vacío") val productName: String,

    @field:NotBlank(message = "La marca no puede estar vacía") val brand: String,

    @field:Min(value = 0, message = "El precio debe ser un número positivo") val price: Double,

    @ManyToOne @JoinColumn(name = "categoryId") @JsonManagedReference @field:NotNull(message = "La categoría no puede ser nula") val category: Categories,

    @OneToOne(
        cascade = [CascadeType.ALL], mappedBy = "product"
    ) @JsonBackReference val motherboardDetails: MotherboardDetails? = null
)

@Entity
@Table(name = "motherboard_details")
data class MotherboardDetails(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val productId: Long = 0,

    @Column(nullable = false) val chipset: String,

    @Column(nullable = false) val formFactor: String,

    @Column(nullable = false) val memorySlots: Int,

    @Column(nullable = false) val memoryType: String,

    @Column(nullable = false) val m2Slots: Int,

    @Column(nullable = false) val pciEx16Slots: Int,

    @Column(nullable = false) val pciEx1Slots: Int,

    @Column(nullable = false) val sata3Ports: Int,

    @Column(nullable = false) val socket: String,

    @Column(nullable = false) val usb2Ports: Int,

    @Column(nullable = false) val usb3Ports: Int,

    @OneToOne @MapsId @JoinColumn(name = "productId") @JsonManagedReference var product: Product? = null

)


@Entity
@Table(name = "cooling_details")
data class CoolingDetails(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @Column(nullable = false) val blockRGB: Boolean = false,

    @Column(nullable = false) val fanRPM: Int = 0,

    @Column(nullable = false) val fanSize: Int = 0,

    @Column(nullable = false) val fans: Int = 0,

    @Column(nullable = false) val noiseLevel: Int = 0,

    @OneToOne @MapsId @JoinColumn(name = "id") var product: Product? = null
)

@Entity
@Table(name = "power_supply_details")
data class PowerSupplyDetails(
    @Id val id: Long = 0,

    @Column(nullable = false) val efficiency: String = "",

    @Column(nullable = false) val fanless: Boolean = false,

    @Column(nullable = false) val formFactor: String = "",

    @Column(nullable = false) val modular: Boolean = false,

    @Column(nullable = false) val power: Double = 0.0,
    @OneToOne @MapsId @JoinColumn(name = "id") var product: Product? = null
)

@Entity
@Table(name = "processor_details")
data class ProcessorDetails(
    @Id val id: Long = 0,

    @Column(nullable = false) val cores: Int = 0,

    @Column(nullable = false) val frequency: Double = 0.0,

    @Column(nullable = false) val l3Cache: Int = 0,

    @Column(nullable = false) val socket: String = "",

    @Column(nullable = false) val tdp: Int = 0,

    @Column(nullable = false) val threads: Int = 0,
    @OneToOne @MapsId @JoinColumn(name = "id") var product: Product? = null
)

@Entity
@Table(name = "RAMDetails")
data class RAMDetails(
    @Id val id: Long = 0,

    @Column(nullable = false) val casLatency: Int = 0,

    @Column(nullable = false) val color: String = "",

    @Column(nullable = false) val frequency: Double = 0.0,

    @Column(nullable = false) val heatSpreader: Boolean = false,

    @Column(nullable = false) val memoryType: String = "",

    @Column(nullable = false) val modules: Int = 0,

    @Column(nullable = false) val size: Int = 0,

    @Column(nullable = false) val voltage: Double = 0.0,
    @OneToOne @MapsId @JoinColumn(name = "id") var product: Product? = null
)
@Entity
@Table(name = "Storage_Details")
data class StorageDetails(
    @Id val id: Long = 0,

    @Column(nullable = false) val capacity: Int = 0,

    @Column(nullable = false) val formFactor: String = "",

    @Column(nullable = false) val interfaceType: String = "",

    @Column(nullable = false) val m2: Boolean = false,

    @Column(nullable = false) val nvme: Boolean = false,

    @Column(nullable = false) val rpm: Int = 0,

    @Column(nullable = false) val size: Int = 0,

    @Column(nullable = false) val type: String = "",
    @OneToOne @MapsId @JoinColumn(name = "id") var product: Product? = null
)

@Entity
@Table(name = "Graphics_Card_Details")
data class GraphicsCardDetails(
    @Id val id: Long = 0,

    @Column(nullable = false) val displayPorts: Int = 0,

    @Column(nullable = false) val fans: Int = 0,

    @Column(nullable = false) val hdmi: Int = 0,

    @Column(nullable = false) val length: Int = 0,

    @Column(nullable = false) val memory: Int = 0,

    @Column(nullable = false) val power: Int = 0,

    @Column(nullable = false) val speed: Double = 0.0,

    @Column(nullable = false) val tdp: Int = 0,

    @Column(nullable = false) val vga: Int = 0,

    @OneToOne @MapsId @JoinColumn(name = "id") var product: Product? = null
)

@Entity
@Table(name = "Shopping_Cart")
data class ShoppingCart(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id") val product: Product? = null,

    @Column(nullable = false) val quantity: Int = 0
)

@Entity
@Table(name = "Sales_History")
data class SalesHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id") val product: Product? = null,

    @Column(nullable = false) val quantity: Int = 0,

    @Column(nullable = false) val saleDate: LocalDateTime = LocalDateTime.now()
)

@Entity
@Table(name = "Orders")
data class Orders(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User? = null,

    @Column(nullable = false) val orderDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false) val orderStatus: String = "",

    @OneToMany(
        mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true
    ) val orderDetails: List<OrderDetails> = mutableListOf()
)

@Entity
@Table(name = "Order_Details")
data class OrderDetails(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "order_id") val order: Orders? = null,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id") val product: Product? = null,

    @Column(nullable = false) val quantity: Int = 0
)
