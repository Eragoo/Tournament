package com.Eragoo.Tournament.participant;

import com.Eragoo.Tournament.tournament.Tournament;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id &&
                name.equals(that.name);
    }

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
