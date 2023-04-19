package com.knf.dev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbootKotlinCrudApplication

fun main(args: Array<String>) {
	runApplication<SpringbootKotlinCrudApplication>(*args){
		setDefaultProperties(mapOf("server.address" to "0.0.0.0"));
		app.use("/", express.static(path.join(__dirname, "angularfront")));
	}
}
