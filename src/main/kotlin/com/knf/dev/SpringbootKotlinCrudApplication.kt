package com.knf.dev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.filter.CorsFilter
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.CorsConfiguration


@SpringBootApplication
class SpringbootKotlinCrudApplication

fun main(args: Array<String>) {
    runApplication<SpringbootKotlinCrudApplication>(*args) {
        setDefaultProperties(mapOf("server.address" to "0.0.0.0"))
    }
}

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/public/")
            .setCachePeriod(0)
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.addAllowedOrigin("*")
        config.addAllowedMethod("GET")
        config.addAllowedMethod("POST")
        config.addAllowedMethod("PUT")
        config.addAllowedMethod("DELETE")
        config.addAllowedMethod("OPTIONS")
        config.addAllowedHeader("*")
        config.addExposedHeader("Authorization")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}
