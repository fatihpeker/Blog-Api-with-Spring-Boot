package tr.folksdev.kdgms.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Comment @JvmOverloads constructor(
        @Id
        @Column(name = "comment_id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id : String? = "",
        val remark : String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")
        val user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "blogpost_id", referencedColumnName = "blogpost_id")
        val blogPost: BlogPost

) {
}