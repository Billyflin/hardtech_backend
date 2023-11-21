package hardtech.entity.products

//


import hardtech.entity.Product
import jakarta.persistence.*


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

