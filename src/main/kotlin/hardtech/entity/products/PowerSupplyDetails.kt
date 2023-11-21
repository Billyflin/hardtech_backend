package hardtech.entity.products

import hardtech.entity.Product
import jakarta.persistence.*

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