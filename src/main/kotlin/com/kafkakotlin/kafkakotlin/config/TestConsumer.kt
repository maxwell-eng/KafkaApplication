package com.kafkakotlin.kafkakotlin.config


import com.kafkakotlin.kafkakotlin.model.Course
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Service

@Service
class TestConsumer {


    @KafkaListener(topics = ["topicTwo"], groupId = "group_id",containerFactory = "kafkaListenerContainerFactory")
    fun listenGroupFoo(course:String) {
       println(course)

    }
}