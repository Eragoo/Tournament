package com.Eragoo.Tournament.tournament.match;

import com.Eragoo.Tournament.participant.Participant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Setter
@Getter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Participant blueParticipant;

    @ManyToOne
    private Participant redParticipant;

    private long blueScore;

    private long redScore;

    private Instant startTime;

    private Instant finishTime;
}
