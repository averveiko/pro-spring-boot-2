package ru.averveyko.todosimplesecurity.repository;


import org.springframework.data.repository.CrudRepository;
import ru.averveyko.todosimplesecurity.domain.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, String> {
}
