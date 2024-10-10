package ru.mudan.domain.entity

import jakarta.persistence.*
import lombok.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.mudan.domain.entity.enums.Role
import java.util.List

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = ["id"])
@Entity
@Table(name = "users", indexes = [Index(columnList = "login")])
class ApplicationUser : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private var id: Long? = null

    @Column(nullable = false, unique = true)
    private var login: String? = null

    @Column(nullable = false)
    private var displayName: String? = null

    @Column(nullable = false)
    private var hashedPassword: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private var role: Role? = null

    constructor(login: String?, displayName: String?, hashedPassword: String?, role: Role?) {
        this.login = login
        this.displayName = displayName
        this.hashedPassword = hashedPassword
        this.role = role
    }


    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return List.of(SimpleGrantedAuthority(role!!.name))
    }

    override fun getPassword(): String {
        return hashedPassword!!
    }

    override fun getUsername(): String {
        return login!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}