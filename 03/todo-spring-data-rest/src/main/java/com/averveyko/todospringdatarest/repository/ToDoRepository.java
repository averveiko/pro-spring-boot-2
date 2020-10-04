package com.averveyko.todospringdatarest.repository;

import org.springframework.data.repository.CrudRepository;
import com.averveyko.todospringdatarest.domain.ToDo;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// опциональная аннотация - позволяет задать доп параметры @RepositoryRestResource
public interface ToDoRepository extends CrudRepository<ToDo, String> {
}
