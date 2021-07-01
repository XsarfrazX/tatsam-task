package com.sarfraz.priority.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;

@ConditionalOnProperty(name="database", havingValue = "mongodb")
@Configuration
public class PriorityMongoConfig {

    @Value("${spring.data.mongodb.priority.uri:mongodb://localhost:27017/priority}")
    private String priorityDbUri;

    @Value("${spring.data.mongodb.priority.db:priority}")
    private String dbName;
    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(priorityDbUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), dbName);
    }

}
