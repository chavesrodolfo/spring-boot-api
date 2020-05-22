package io.github.chavesrodolfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponse {

    private final String status;
    private final String message;

}