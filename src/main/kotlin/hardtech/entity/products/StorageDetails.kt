package hardtech.entity.products

import hardtech.entity.Product
import jakarta.persistence.*

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