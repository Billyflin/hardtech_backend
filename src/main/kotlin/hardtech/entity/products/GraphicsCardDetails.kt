package hardtech.entity.products

import hardtech.entity.Product
import jakarta.persistence.*

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