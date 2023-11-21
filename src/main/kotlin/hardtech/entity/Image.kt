package hardtech.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "image")
data class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val imageId: Long = 0,

    @Column(columnDefinition = "bytea")
    val imageData: ByteArray,


    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "productId")
    @JsonBackReference
    val product: Product
)