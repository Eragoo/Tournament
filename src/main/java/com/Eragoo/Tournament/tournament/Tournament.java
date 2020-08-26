package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.participant.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "tournament")
    @NotEmpty(message = "Participants cannot be empty")
    @Size(min = 7, message = "Tournament must have at least 7 participants")
    private Set<Participant> participants;

    private int matchesNumber;
}
