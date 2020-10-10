package ru.averveyko.todosimplesecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Тоже саое можно было сделать обычным @Controller
@Configuration
public class ToDoWebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // соответствие эндпоинта "/login" и представлению login (templates/login.mustache)
        registry.addViewController("/login").setViewName("login");
    }
}
