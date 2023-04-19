package com.knf.dev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import java.nio.file.Paths

@SpringBootApplication
class SpringbootKotlinCrudApplication

fun main(args: Array<String>) {
	runApplication<SpringbootKotlinCrudApplication>(*args) {
		setDefaultProperties(mapOf("server.address" to "0.0.0.0"))
	}
	
	// Serve static files from the "dist" folder
	@RestController
	class StaticFileController {
	    @GetMapping("/{path:[^\\.]*}")
	    fun serveStaticFile(): ResponseEntity<Any> {
	        val indexPath = Paths.get("angularfront/index.html")
	        return ResponseEntity.ok().body(indexPath.toFile().readText())
	    }
	}
}
