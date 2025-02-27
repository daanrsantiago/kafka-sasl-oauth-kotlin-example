package dev.danielsantiago.kafka.sasl.oauth.example

import dev.danielsantiago.kafka.sasl.oauth.example.producer.MessageProducer

fun main() {
    val producer = MessageProducer()


    for (i in 1..10) {
        producer.produce()
    }
}