package hardtech.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Orders")
data class Orders(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User? = null,

    @Column(nullable = false) val orderDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false) val orderStatus: String = "",

    @OneToMany(
        mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true
    ) val orderDetails: List<OrderDetails> = mutableListOf()
)