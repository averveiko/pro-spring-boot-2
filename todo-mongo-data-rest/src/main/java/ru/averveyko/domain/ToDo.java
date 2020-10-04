package ru.averveyko.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Data
public class ToDo {

    @NotNull
    @Id
    private String id;
    @NotNull
    @NotBlank
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Boolean completed;

    public ToDo() {
        LocalDateTime dateTime = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = dateTime;
        this.modified = dateTime;
    }

    public ToDo(String description) {
        this();
        this.description = description;
    }

}
