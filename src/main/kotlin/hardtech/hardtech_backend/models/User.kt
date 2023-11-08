package hardtech.hardtech_backend.models



import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    var username: String,

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    var email: String,

    @NotBlank
    @Size(max = 120)
    var password: String,

    @NotBlank
    @Size(max = 20)
    var name: String,
)