package com.kafkakotlin.kafkakotlin.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.kafkakotlin.kafkakotlin.model.Course
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory

import kotlin.text.Charsets.UTF_8

class CourseDeserializer: Deserializer<Course> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun deserialize(topic: String?, data: ByteArray?): Course? {
        log.info("Deserializing...")
        return objectMapper.readValue(
            String(
                data ?: throw SerializationException("Error when deserializing byte[] to Product"), UTF_8
            ), Course::class.java
        )
    }

    override fun close() {}
}