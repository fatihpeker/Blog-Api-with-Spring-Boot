package tr.folksdev.kdgms.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class User @JvmOverloads constructor(

        @Id
        @Column(name = "user_id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id : String? = "",
        val username : String,
        val password : String,
        val name : String,
        val surname : String,

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
        val posts : Set<BlogPost>? = HashSet(),

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
        val remarks : Set<Comment>? = HashSet(),




){
}