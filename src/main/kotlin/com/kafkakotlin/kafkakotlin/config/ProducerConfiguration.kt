package com.kafkakotlin.kafkakotlin.config


import com.kafkakotlin.kafkakotlin.model.Course
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class ProducerConfiguration {
    @Value("\${kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val configProps = HashMap<String, Any>()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }
}
//creating the producer , giving it properties and using kafka template to specify data transfer
//producerFactory is used to create a producer
//producerFactory works hand in hand with kafkaTemplate, this enables sending of messages. begins an instance
//the value annotation is used to specify a property that has already been specified which
// is in this case the browser specified in .properties file. like assigning a default value like the server.
//@Configuration is used to indicate that beans will be used.
//@Bean is used to specify the methods and their returns
//also, producer properties should be configured with key serialization and value serialization in order
// to convert objects to streams during transmission of data note we deserialize for a consumer.