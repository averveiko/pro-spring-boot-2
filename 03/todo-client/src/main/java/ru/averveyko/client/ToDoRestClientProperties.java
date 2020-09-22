package ru.averveyko.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "todo") // загрузка настроек из application.properties
@Data
public class ToDoRestClientProperties {

    private String url;
    private String basePath;
}
