package distributed.order.management.Entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "email", unique = true)
    var email: String = "",

    @Column(name = "password")
    var password: String = ""
) {
    constructor() : this(0, "", "", "")
}
