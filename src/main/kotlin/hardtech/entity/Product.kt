package hardtech.entity
//
//import jakarta.persistence.*
//
//@Entity
//@Table(name = "Product")
//data class Product(
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long = 0,
//
//    @Column(nullable = false)
//    val name: String = "",
//
//    val brand: String = "",
//
//    @Column(nullable = false)
//    val price: Double = 0.0,
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    val category: Category? = null
//)
//
