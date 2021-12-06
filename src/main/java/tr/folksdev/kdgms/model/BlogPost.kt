package tr.folksdev.kdgms.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class BlogPost @JvmOverloads constructor(

        @Id
        @Column(name = "blogpost_id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id : String? = "",
        val posting : String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")
        val user: User,

        @OneToMany(mappedBy = "blogPost", fetch = FetchType.LAZY)
        val remarks : Set<Comment>? = HashSet()
) {
}