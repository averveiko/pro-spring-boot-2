package ru.averveyko.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.Arrays;

/**
 * Для работы со встроенной монгой (не использовать в проде:)!)
 * нужно описать конфигурацию, т.к. встроенная мога запускается на рандомном порту
 */
@Configuration
public class ToDoConfig {
}
