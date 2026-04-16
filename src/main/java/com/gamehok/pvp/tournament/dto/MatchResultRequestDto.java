package com.gamehok.pvp.tournament.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchResultRequestDto {

    @NotNull
    private Long winnerTeamId;
}
