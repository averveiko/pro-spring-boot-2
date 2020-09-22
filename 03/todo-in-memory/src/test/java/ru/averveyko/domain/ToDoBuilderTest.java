package ru.averveyko.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoBuilderTest {

    @Test
    public void builderTest() {
        ToDo toDo = ToDoBuilder.create()
                .withDescription("someDescription")
                .withId("234")
                .build();

        assertEquals("someDescription", toDo.getDescription()  );
        assertEquals("234", toDo.getId()  );
    }

    @Test
    public void toDoWithoutIdTest() {
        ToDo toDo = ToDoBuilder.create()
                .withDescription("someDescription")
                .build();

        assertEquals("someDescription", toDo.getDescription()  );
        assertNotNull(toDo.getId());
    }

}