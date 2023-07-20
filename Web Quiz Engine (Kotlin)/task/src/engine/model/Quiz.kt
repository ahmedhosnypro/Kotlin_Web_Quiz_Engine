package engine.model

import jakarta.persistence.*


@Entity
class Quiz(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false) var id: Long? = null,
    @Column(name = "title", nullable = false) var title: String? = null,
    @Column(name = "text", nullable = false) var text: String? = null,

    @ElementCollection
    @CollectionTable(name = "options", joinColumns = [JoinColumn(name = "id")])
    @Column(name = "options", nullable = false) var options: List<String>? = null,

    @ElementCollection
    @CollectionTable(name = "answer", joinColumns = [JoinColumn(name = "id")])
    @Column(name = "answer")
    var answer: Set<Int>? = null
)