package com.kafkakotlin.kafkakotlin

import com.kafkakotlin.kafkakotlin.model.Course
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.core.KafkaTemplate

@SpringBootApplication
class KafkakotlinApplication


	fun main(args: Array<String>) {

		runApplication<KafkakotlinApplication>(*args)
	}
