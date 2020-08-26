package com.Eragoo.Tournament.participant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Set<Participant> findAllByIdIn(Set<Long> ids);
}
