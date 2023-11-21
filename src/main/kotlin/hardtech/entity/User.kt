package hardtech.entity

import jakarta.persistence.*

@Entity
@Table(name = "`user`")
class User(
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long? = null,

    @Column(name = "username", length = 50, unique = true)
    var username: String? = null,

    @Column(name = "password", length = 100)
    var password: String? = null,

    @Column(name = "nickname", length = 50)
    var nickname: String? = null,

    @Column(name = "email", length = 50, unique = true)
    var email: String? = null,

    @Column(name = "phone", length = 50)
    var phone: String? = null,

    @Column(name = "address", length = 50)
    var address: String? = null,

    @Column(name = "city", length = 50)
    var city: String? = null,

    @Column(name = "country", length = 50)
    var country: String? = null,

    @Column(name = "activated")
    var isActivated: Boolean = false,

    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_name", referencedColumnName = "authority_name")]
    )
    val authorities: Set<Authority>? = null
)