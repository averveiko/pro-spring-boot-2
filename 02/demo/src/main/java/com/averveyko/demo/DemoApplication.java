package com.averveyko.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner, ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    private String info;

    // Пример чтения проперти из application.properties
    @Value("${demo.property}")
    private String demoProperty;

    public static void main(String[] args) {

        new SpringApplicationBuilder(DemoApplication.class)
                .listeners(applicationEvent ->
						logger.info("#### > " + applicationEvent.getClass().getCanonicalName()))
				.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("## > ApplicationRunner Implementation...");
        logger.info("Accessing the Info bean: " + info);
        logger.info("### demoProperty value: " + demoProperty);
        args.getNonOptionArgs().forEach(file -> logger.info(file));
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("## > CommandLineRunner Implementation...");
        logger.info("Accessing the Info bean: " + info);
        for(String arg:args)
            logger.info(arg);
    }

    @Bean
    String info() {
        return "Just a simple String bean";
    }

    /**
     * Так еще проще запускать код при старте модуля
     */
    @Bean
    CommandLineRunner myMethod(){
        return args -> {
            logger.info("## > CommandLineRunner  Implementation... (@Bean)");
            logger.info("Accessing the Info bean: " + info);
            for(String arg:args)
                logger.info(arg);
        };
    }

    @Component
    class MyComponent {
        @Autowired
        public MyComponent(ApplicationArguments arguments) {
            //$ java -jar spring-boot-simple-0.0.1-SNAPSHOT.jar --enable arg1 arg2
            boolean enable = arguments.containsOption("enable"); // --enable
            if (enable) {
                logger.info("#### > You are enabled!");
            }
            List<String> args = arguments.getNonOptionArgs();
            logger.info("#### > extra args ...");
            args.forEach(logger::info);
        }
    }
}
