package com.knf.dev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@SpringBootApplication
class SpringbootKotlinCrudApplication

fun main(args: Array<String>) {
    runApplication<SpringbootKotlinCrudApplication>(*args) {
        setDefaultProperties(mapOf("server.address" to "0.0.0.0"))
    }
}

/*@RestController
class StaticFileController {
    @GetMapping("/{path:[^\\.]*}")
    fun serveStaticFile(@PathVariable path: String): ResponseEntity<Any> {
        val indexPath = Paths.get("src/angularfront/index.html")
        return if (indexPath.toFile().exists()) {
            ResponseEntity.ok().body(indexPath.toFile().readText())
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found")
        }
    }
}*/

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/public/")
            .setCachePeriod(0)
    }
}

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
