package com.kafkakotlin.kafkakotlin

import com.kafkakotlin.kafkakotlin.model.Course
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate

@SpringBootTest
class KafkakotlinApplicationTests ( @Autowired
									private val kafkaTemplate: KafkaTemplate<String, MutableList<Course>>
){




	@Test
	fun test(){
		val courses = mutableListOf<Course>()
		courses.add(Course("science",78))
		courses.add(Course("mathematics", 69))
		courses.add(Course("english", 85))

		kafkaTemplate.send("topicTwo", courses.toString(),courses)
		kafkaTemplate.flush()

	}


}
