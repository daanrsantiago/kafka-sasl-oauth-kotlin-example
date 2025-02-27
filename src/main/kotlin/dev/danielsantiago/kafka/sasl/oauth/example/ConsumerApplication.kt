package dev.danielsantiago.kafka.sasl.oauth.example

import dev.danielsantiago.kafka.sasl.oauth.example.consumer.MessageConsumer

fun main() {
    val messageConsumer = MessageConsumer()

    messageConsumer.consume()
}