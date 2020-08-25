package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.participant.Participant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Setter
@Getter
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    @NotEmpty(message = "Participants cannot be empty")
    @Size(min = 7, message = "Tournament must have at least 7 participants")
    private Set<Participant> participants;

    private int matchesNumber = (participants.size() + 1) / 2;
}
