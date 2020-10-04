package ru.averveyko.repository;

import org.springframework.data.repository.CrudRepository;
import ru.averveyko.domain.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, String> {
}
