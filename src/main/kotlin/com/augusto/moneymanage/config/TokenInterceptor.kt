package com.augusto.moneymanage.config

import com.augusto.moneymanage.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

class TokenInterceptor @Autowired constructor(private val userRepository: UserRepository): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("Authorization")
        val user = this.userRepository.findByToken(token).orElse(null)

        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
            return false
        }

        request.setAttribute("id", user.id)
        return true
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {}

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {}
}