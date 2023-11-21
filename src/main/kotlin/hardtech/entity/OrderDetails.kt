package hardtech.entity

import jakarta.persistence.*

@Entity
@Table(name = "Order_Details")
data class OrderDetails(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "order_id") val order: Orders? = null,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_id") val product: Product? = null,

    @Column(nullable = false) val quantity: Int = 0
)