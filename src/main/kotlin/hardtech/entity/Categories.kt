package hardtech.entity

import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class Categories(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val categoryId: Long = 0,

    @Column(unique = true) val categoryName: String
)