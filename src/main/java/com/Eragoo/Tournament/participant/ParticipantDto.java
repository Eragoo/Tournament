package com.Eragoo.Tournament.participant;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class ParticipantDto {
    private long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantDto that = (ParticipantDto) o;
        return id == that.id &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
