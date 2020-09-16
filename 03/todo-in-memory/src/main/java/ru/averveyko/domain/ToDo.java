package ru.averveyko.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ToDo {

    @NotNull
    private String id;

    @NotNull
    @NotBlank
    private String descriptions;

    private LocalDateTime created;

    private LocalDateTime modified;

    private Boolean completed;

    public ToDo() {
        LocalDateTime date = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = date;
        this.modified = date;
    }

    public ToDo(@NotNull @NotBlank String descriptions) {
        this();
        this.descriptions = descriptions;
    }
}
