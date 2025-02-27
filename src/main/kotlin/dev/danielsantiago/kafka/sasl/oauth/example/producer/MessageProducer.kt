package dev.danielsantiago.kafka.sasl.oauth.example.producer

import org.apache.kafka.clients.producer.KafkaProducer
import java.util.Properties

class MessageProducer {

    private val producer: KafkaProducer<String, String>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = "localhost:9092"
        properties["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        properties["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        properties["security.protocol"] = "SASL_PLAINTEXT"
        properties["sasl.mechanism"] = "OAUTHBEARER"
        properties["sasl.jaas.config"] = "org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule required" +
                " clientId=\"kafka-client\"" +
                " clientSecret=\"FIPgW3IE8SPH96yXTB7r6YVQ62699xDM\";"
        properties["sasl.login.callback.handler.class"] = "org.apache.kafka.common.security.oauthbearer.secured.OAuthBearerLoginCallbackHandler"
        properties["sasl.oauthbearer.token.endpoint.url"] = "http://localhost:8082/realms/kafka-example/protocol/openid-connect/token"
        producer = KafkaProducer<String, String>(properties)
    }

    fun produce() {
        val record = producer.send(org.apache.kafka.clients.producer.ProducerRecord("test-topic", "key", "value")).get()
        println("Record sent to partition ${record.partition()} with offset ${record.offset()}")
    }


}