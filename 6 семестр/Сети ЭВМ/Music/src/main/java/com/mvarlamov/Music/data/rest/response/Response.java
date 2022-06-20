package com.mvarlamov.Music.data.rest.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message;
    private boolean status;
    public static Response of(String message, boolean status) {
        return new Response(message, status);
    }
}
