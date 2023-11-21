package hardtech.entity.products

import com.fasterxml.jackson.annotation.JsonManagedReference
import hardtech.entity.Product
import jakarta.persistence.*

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