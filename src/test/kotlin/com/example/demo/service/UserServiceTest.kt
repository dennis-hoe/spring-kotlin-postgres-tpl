package com.example.demo.service

import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun `should get all users`() {
        val users = listOf(
            User(id = 1, email = "test1@example.com", name = "Test User 1"),
            User(id = 2, email = "test2@example.com", name = "Test User 2")
        )
        `when`(userRepository.findAll()).thenReturn(users)

        val result = userService.getAllUsers()

        assertEquals(2, result.size)
        verify(userRepository, times(1)).findAll()
    }

    @Test
    fun `should get user by id`() {
        val user = User(id = 1, email = "test@example.com", name = "Test User")
        `when`(userRepository.findById(1L)).thenReturn(Optional.of(user))

        val result = userService.getUserById(1L)

        assertNotNull(result)
        assertEquals("test@example.com", result?.email)
        verify(userRepository, times(1)).findById(1L)
    }

    @Test
    fun `should create user successfully`() {
        val user = User(email = "test@example.com", name = "Test User")
        `when`(userRepository.existsByEmail("test@example.com")).thenReturn(false)
        `when`(userRepository.save(any(User::class.java))).thenReturn(user.copy(id = 1))

        val result = userService.createUser("test@example.com", "Test User")

        assertNotNull(result)
        assertEquals("test@example.com", result.email)
        verify(userRepository, times(1)).save(any(User::class.java))
    }

    @Test
    fun `should throw exception when creating user with existing email`() {
        `when`(userRepository.existsByEmail("test@example.com")).thenReturn(true)

        assertThrows<IllegalArgumentException> {
            userService.createUser("test@example.com", "Test User")
        }
    }

    @Test
    fun `should delete user successfully`() {
        `when`(userRepository.existsById(1L)).thenReturn(true)
        doNothing().`when`(userRepository).deleteById(1L)

        userService.deleteUser(1L)

        verify(userRepository, times(1)).deleteById(1L)
    }

    @Test
    fun `should throw exception when deleting non-existent user`() {
        `when`(userRepository.existsById(1L)).thenReturn(false)

        assertThrows<IllegalArgumentException> {
            userService.deleteUser(1L)
        }
    }
}
