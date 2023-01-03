package com.fastcampus.kotlinspring.todo.service

import com.fastcampus.kotlinspring.todo.domain.Todo
import com.fastcampus.kotlinspring.todo.domain.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
class TodoServiceTests {

    // 추후에 프레임워크에서 주입을 해주기에 lateinit을 사용함
    @MockkBean
    lateinit var repository: TodoRepository

    lateinit var service: TodoService

    // 사용하는 시점에 초기화
    val stub: Todo by lazy {
        Todo (
            id = 1,
            title = "테스트",
            description = "테스트 상세",
            done = false,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }

    @BeforeEach
    fun setUp() {
        this.service = TodoService(repository)
    }

    @Test
    fun `한개의 TODO를 반환해야한다`() {
        // given
        every { repository.findByIdOrNull(1L) } returns stub

        // when
        val actual = service.findById(1L)

        // then
        assertThat(actual).isNotNull
        assertThat(actual).isEqualTo(stub)
    }

}
