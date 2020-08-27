package com.Eragoo.Tournament.tournament;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TournamentCommand {
    @NotEmpty(message = "Participants cannot be empty")
    @Size(min = 7, message = "Tournament must have at least 7 participants")
    private Set<Long> participantsIds;
    private int matchesNumber;
}
