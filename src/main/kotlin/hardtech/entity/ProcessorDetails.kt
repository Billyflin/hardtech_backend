package hardtech.entity

import jakarta.persistence.*

@Entity
@Table(name = "processor_details")
data class ProcessorDetails(
    @Id val id: Long = 0,

    @Column(nullable = false) val cores: Int = 0,

    @Column(nullable = false) val frequency: Double = 0.0,

    @Column(nullable = false) val l3Cache: Int = 0,

    @Column(nullable = false) val socket: String = "",

    @Column(nullable = false) val tdp: Int = 0,

    @Column(nullable = false) val threads: Int = 0,
    @OneToOne @MapsId @JoinColumn(name = "id") var product: Product? = null
)