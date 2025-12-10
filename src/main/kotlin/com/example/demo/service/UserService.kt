package com.example.demo.service

import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {
    
    fun getAllUsers(): List<User> = userRepository.findAll()
    
    fun getUserById(id: Long): User? = userRepository.findById(id).orElse(null)
    
    fun getUserByEmail(email: String): User? = userRepository.findByEmail(email)
    
    fun createUser(email: String, name: String): User {
        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("User with email $email already exists")
        }
        val user = User(email = email, name = name)
        return userRepository.save(user)
    }
    
    fun updateUser(id: Long, email: String?, name: String?): User {
        val user = userRepository.findById(id).orElseThrow {
            IllegalArgumentException("User with id $id not found")
        }
        
        // Check if email is being changed and if the new email already exists
        if (email != null && email != user.email && userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("User with email $email already exists")
        }
        
        val updatedUser = user.copy(
            email = email ?: user.email,
            name = name ?: user.name,
            updatedAt = LocalDateTime.now()
        )
        
        return userRepository.save(updatedUser)
    }
    
    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw IllegalArgumentException("User with id $id not found")
        }
        userRepository.deleteById(id)
    }
}
