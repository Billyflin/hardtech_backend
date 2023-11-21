package hardtech.entity

import jakarta.persistence.*

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