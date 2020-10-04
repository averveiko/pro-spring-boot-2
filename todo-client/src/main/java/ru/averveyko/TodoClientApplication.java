package ru.averveyko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.averveyko.client.ToDoRestClient;
import ru.averveyko.domain.ToDo;

@SpringBootApplication
public class TodoClientApplication {

	private Logger logger = LoggerFactory.getLogger(TodoClientApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TodoClientApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}


	@Bean
	public CommandLineRunner process(ToDoRestClient client) {
		return args -> {

			Iterable<ToDo> toDos = client.findAll();
			assert toDos != null;
			toDos.forEach(toDo -> logger.info(toDo.toString()));

			ToDo newToDo = client.upsert(new ToDo("Пробежка в парке Авиаторов 5 км в легком темпе"));
			assert newToDo != null;
			logger.info(newToDo.toString());

			ToDo toDo = client.findById(newToDo.getId());
			assert toDo != null;
			logger.info(toDo.toString());

			ToDo completed = client.setCompleted(newToDo.getId());
			assert completed.getCompleted();
			logger.info(completed.toString());

			client.delete(newToDo.getId());
			assert client.findById(newToDo.getId()) == null;
		};
	}
}
