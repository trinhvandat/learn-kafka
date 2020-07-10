package com.spring.kafka.consumer;

import com.spring.kafka.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConfigKafkaConsumer {


    @Bean
    public ConsumerFactory<String,User> consumerFactory(){

        Map<String,Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"${kafka.bootstrap.server}");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"${kafka.groupId}");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),
                new JsonDeserializer<>(User.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,User> kafkaListener(){

        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }


//-------------------------------config kafka for consume message with type's value: Integer--------------------------//

    @Bean
    public ConsumerFactory<String,Integer> consumerFactoryInteger(){

        Map<String,Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"${kafka.bootstrap.server}");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"${kafka.groupId}");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IntegerSerializer.class);

        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(), new IntegerDeserializer());
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Integer> integerKafkaListener(){

        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactoryInteger());

        return factory;
    }


}
