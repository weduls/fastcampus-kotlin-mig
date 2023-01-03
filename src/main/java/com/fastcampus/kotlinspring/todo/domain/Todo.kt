package com.fastcampus.kotlinspring.todo.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
class Todo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    var title: String,
    @Lob
    var description: String,
    var done: Boolean,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
) {

    fun update(title: String, description: String, done: Boolean) {
        // jpa는 값을 바꿔야하기 때문에 val이 아닌 var로 사용해야하는 부분이 아쉬움
        this.title = title;
        this.description = description;
        this.done = done;
        this.updatedAt = LocalDateTime.now()
    }
}
