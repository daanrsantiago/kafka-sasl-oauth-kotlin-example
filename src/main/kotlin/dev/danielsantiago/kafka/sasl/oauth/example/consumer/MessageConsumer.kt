package dev.danielsantiago.kafka.sasl.oauth.example.consumer

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties

class MessageConsumer {

    val consumer: KafkaConsumer<String, String>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = "localhost:9092"
        properties["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        properties["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        properties["security.protocol"] = "SASL_PLAINTEXT"
        properties["sasl.mechanism"] = "OAUTHBEARER"
        properties["group.id"] = "test"
        properties["sasl.jaas.config"] = "org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule required" +
                " clientId=\"kafka-client\"" +
                " clientSecret=\"FIPgW3IE8SPH96yXTB7r6YVQ62699xDM\";"
        properties["sasl.login.callback.handler.class"] = "org.apache.kafka.common.security.oauthbearer.secured.OAuthBearerLoginCallbackHandler"
        properties["sasl.oauthbearer.token.endpoint.url"] = "http://localhost:8082/realms/kafka-example/protocol/openid-connect/token"

        consumer = KafkaConsumer<String, String>(properties)
    }

    fun consume() {
        consumer.subscribe(listOf("test-topic"))
        while (true) {
            val records = consumer.poll(100)
            for (record in records) {
                println("offset = ${record.offset()}, key = ${record.key()}, value = ${record.value()}")
            }
        }
    }

}