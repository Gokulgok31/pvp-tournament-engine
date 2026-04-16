package com.gamehok.pvp.tournament.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorResponseDto {

    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
