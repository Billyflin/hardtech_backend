package hardtech.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Sales_History")
data class SalesHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id") val product: Product? = null,

    @Column(nullable = false) val quantity: Int = 0,

    @Column(nullable = false) val saleDate: LocalDateTime = LocalDateTime.now()
)