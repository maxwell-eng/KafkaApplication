package com.kafkakotlin.kafkakotlin.config

import com.kafkakotlin.kafkakotlin.model.Course
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TestProducer (
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String,String>
){
    @PostConstruct
    fun test(){
        val courses = mutableListOf<Course>()
        courses.add(Course("science",78))
        courses.add(Course("mathematics", 69))
        courses.add(Course("english", 85))
        val json = """
          [
            {
              "course":"science",
              "marks":78
              
            },
            {
              "course":"mathematics",
              "marks":69
            },
            {
               "course":"mathematics",
               "marks":69
            
            }
          ]"""
        kafkaTemplate.send("topicTwo", courses.toString(),json)
        kafkaTemplate.flush()

    }
}
//using the producer to send data to the specific topic
//kafka template and send method are used to send data
//flush method ensures the sending of the messages
//the model with a data class is used to structure the data we are sending, we are using
// add method to add to the bottom of the mutable list of the 'entity'
//@autowired helps with the dependency injection of kafka template from another
// @postconstruct is used to ensure that dependency injection occurs first.
//@Postconstructor annotation ensures that the particular function always runs
