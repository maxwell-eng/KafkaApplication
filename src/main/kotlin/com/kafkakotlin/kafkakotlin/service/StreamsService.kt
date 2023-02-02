package com.kafkakotlin.kafkakotlin.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kafkakotlin.kafkakotlin.model.Course
import jakarta.annotation.PostConstruct
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class StreamsService {
    @Autowired
    private val streamsBuilder: StreamsBuilder? = null
    private val STRING_SERDE = Serdes.String()


    @PostConstruct
    fun buildPipeline() {
        val messageStream = streamsBuilder!!.stream("topicTwo", Consumed.with(STRING_SERDE, STRING_SERDE))
        messageStream.mapValues { value: String? ->
            try {
                val objectMapper = jacksonObjectMapper()
                val courses: MutableList<Course>? = value?.let { objectMapper.readValue(it) }
                val integerList = courses?.stream()?.map { v -> v.marks }?.collect(Collectors.toList())


                val sum = integerList?.stream()?.reduce(0) { aa: Int, bb: Int -> aa + bb }
                return@mapValues sum.toString()
            } catch (e: JsonProcessingException) {
                throw RuntimeException(e)
            }
        }.peek { key: String?, value: String -> println("The sum is $value") }.to("topicSum")
    }
}