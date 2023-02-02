package com.kafkakotlin.kafkakotlin.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
@EnableKafka
class kafkaTopicConfiguration {
    @Value("\${kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: MutableMap<String,Any?> = HashMap()

        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG]=bootstrapServers
        return KafkaAdmin(configs)

    }

    @Bean
    fun createTopic(): NewTopic {
        return TopicBuilder.name("topicTwo").build()
    }

    @Bean
    fun createTopicSum(): NewTopic {
        return TopicBuilder.name("topicSum").build()
    }

}
// fun createTopic(topicName: String): NewTopic {
//      return TopicBuilder.name(topicName).build()
// }
// createTopic("topicTwo")

//We can create a topic manually in cmd or using code with the adminApi as seen in the bean
//mutableMap<k,v> supports key value pairs which make easy retrieving. then the hashMap stores the key value pairs as an object
//Admin client config contains constants
//the second bean creates the topic and topicBuilder.name creates the topic and names it followed by .build to build the topic