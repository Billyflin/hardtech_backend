package hardtech.entity

//


import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "categories")
data class Categories(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val categoryId: Long = 0,

    @Column(unique = true)
    val categoryName: String
)

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long = 0,

    @Column(unique = true)
    val productName: String,

    val brand: String,

    val price: Double,

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonManagedReference
    val category: Categories,

    @OneToOne(cascade = [CascadeType.ALL], mappedBy = "product")
    @JsonBackReference
    val motherboardDetails: MotherboardDetails? = null
)

@Entity
@Table(name = "motherboard_details")
data class MotherboardDetails(
    @Id
    val productId: Long = 0,

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

    @OneToOne
    @JoinColumn(name = "productId")
    @JsonManagedReference
    val product: Product? = null
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

    @OneToOne(fetch = FetchType.LAZY) val product: Product? = null
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

    @OneToOne(fetch = FetchType.LAZY) @MapsId val product: Product? = null
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

    @OneToOne(fetch = FetchType.LAZY) @MapsId val product: Product? = null
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

    @OneToOne(fetch = FetchType.LAZY) @MapsId val product: Product? = null
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

    @OneToOne(fetch = FetchType.LAZY) @MapsId val product: Product? = null
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
        mappedBy = "order",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
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
