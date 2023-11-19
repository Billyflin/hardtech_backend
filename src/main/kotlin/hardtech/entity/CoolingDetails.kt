package hardtech.entity

//
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "category")
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String = "",

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val products: List<Product> = mutableListOf()
)

@Entity
@Table(name = "product")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val price: Double = 0.0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    val category: Category? = null,

    val brand: String = "",

    @Column(nullable = false, unique = true)
    val name: String = "",

    val productType: String = ""
)

@Entity
@Table(name = "CoolingDetails")
data class CoolingDetails(
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val blockRGB: Boolean = false,

    @Column(nullable = false)
    val fanRPM: Int = 0,

    @Column(nullable = false)
    val fanSize: Int = 0,

    @Column(nullable = false)
    val fans: Int = 0,

    @Column(nullable = false)
    val noiseLevel: Int = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    val product: Product? = null
)

@Entity
@Table(name = "MotherboardDetails")
data class MotherboardDetails(
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val chipset: String = "",

    @Column(nullable = false)
    val formFactor: String = "",

    @Column(nullable = false)
    val memorySlots: Int = 0,

    @Column(nullable = false)
    val memoryType: String = "",

    @Column(nullable = false)
    val m2Slots: Int = 0,

    @Column(nullable = false)
    val pciEx16Slots: Int = 0,

    @Column(nullable = false)
    val pciEx1Slots: Int = 0,

    @Column(nullable = false)
    val sata3Ports: Int = 0,

    @Column(nullable = false)
    val socket: String = "",

    @Column(nullable = false)
    val usb2Ports: Int = 0,

    @Column(nullable = false)
    val usb3Ports: Int = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    val product: Product? = null
)

@Entity
@Table(name = "PowerSupplyDetails")
data class PowerSupplyDetails(
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val efficiency: String = "",

    @Column(nullable = false)
    val fanless: Boolean = false,

    @Column(nullable = false)
    val formFactor: String = "",

    @Column(nullable = false)
    val modular: Boolean = false,

    @Column(nullable = false)
    val power: Double = 0.0,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    val product: Product? = null
)

@Entity
@Table(name = "ProcessorDetails")
data class ProcessorDetails(
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val cores: Int = 0,

    @Column(nullable = false)
    val frequency: Double = 0.0,

    @Column(nullable = false)
    val l3Cache: Int = 0,

    @Column(nullable = false)
    val socket: String = "",

    @Column(nullable = false)
    val tdp: Int = 0,

    @Column(nullable = false)
    val threads: Int = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    val product: Product? = null
)

@Entity
@Table(name = "RAMDetails")
data class RAMDetails(
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val casLatency: Int = 0,

    @Column(nullable = false)
    val color: String = "",

    @Column(nullable = false)
    val frequency: Double = 0.0,

    @Column(nullable = false)
    val heatSpreader: Boolean = false,

    @Column(nullable = false)
    val memoryType: String = "",

    @Column(nullable = false)
    val modules: Int = 0,

    @Column(nullable = false)
    val size: Int = 0,

    @Column(nullable = false)
    val voltage: Double = 0.0,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    val product: Product? = null
)

@Entity
@Table(name = "GraphicsCardDetails")
data class GraphicsCardDetails(
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val displayPorts: Int = 0,

    @Column(nullable = false)
    val fans: Int = 0,

    @Column(nullable = false)
    val hdmi: Int = 0,

    @Column(nullable = false)
    val length: Int = 0,

    @Column(nullable = false)
    val memory: Int = 0,

    @Column(nullable = false)
    val power: Int = 0,

    @Column(nullable = false)
    val speed: Double = 0.0,

    @Column(nullable = false)
    val tdp: Int = 0,

    @Column(nullable = false)
    val vga: Int = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    val product: Product? = null
)

@Entity
@Table(name = "ShoppingCart")
data class ShoppingCart(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product? = null,

    @Column(nullable = false)
    val quantity: Int = 0
)

@Entity
@Table(name = "SalesHistory")
data class SalesHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product? = null,

    @Column(nullable = false)
    val quantity: Int = 0,

    @Column(nullable = false)
    val saleDate: LocalDateTime = LocalDateTime.now()
)

@Entity
@Table(name = "Orders")
data class Orders(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User? = null,

    @Column(nullable = false)
    val orderDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val orderStatus: String = "",

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orderDetails: List<OrderDetails> = mutableListOf()
)

@Entity
@Table(name = "OrderDetails")
data class OrderDetails(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Orders? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product? = null,

    @Column(nullable = false)
    val quantity: Int = 0
)
