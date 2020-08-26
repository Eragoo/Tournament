package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.error.exception.ConflictException;
import com.Eragoo.Tournament.participant.Participant;
import com.Eragoo.Tournament.tournament.match.Match;
import com.Eragoo.Tournament.tournament.match.MatchDto;
import com.Eragoo.Tournament.tournament.match.MatchMapper;
import com.Eragoo.Tournament.tournament.match.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class TournamentMatchesLayerGenerator {
    private MatchMapper matchMapper;
    private MatchRepository matchRepository;

    public Set<MatchDto> createLayerWithRandomParticipants(Set<Participant> participants) {
        int matchesNumber = (participants.size() + 1) / 2;
        Set<MatchDto> matches = new HashSet<>();
        for (int i = 0; i < matchesNumber; i++) {
            Match matchWithRandomParticipants = createMatchWithRandomParticipants(participants);
            matchRepository.save(matchWithRandomParticipants);
            MatchDto matchDtoWithRandomParticipants = matchMapper.entityToDto(matchWithRandomParticipants);
            matches.add(matchDtoWithRandomParticipants);
        }
        return matches;
    }

    private Match createMatchWithRandomParticipants(@NotEmpty Set<Participant> participants) {
        if (participants.size() == 1) {
            return getSingleParticipantMatch(participants);
        }

        Match match = new Match();

        Participant blueRandomParticipant = getRandomParticipant(participants);
        Participant redRandomParticipant = getRandomParticipant(participants);

        match.setBlueParticipant(blueRandomParticipant);
        match.setRedParticipant(redRandomParticipant);

        return match;
    }

    private Match getSingleParticipantMatch(@NotEmpty Set<Participant> participants) {
        Match match = new Match();
        Participant blueRandomParticipant = getRandomParticipant(participants);
        match.setBlueParticipant(blueRandomParticipant);
        return match;
    }

    private Participant getRandomParticipant(Set<Participant> participants) {
        Participant randomElementFromCollection = getRandomElementFromCollection(participants);
        participants.remove(randomElementFromCollection);
        return randomElementFromCollection;
    }

    private <T> T getRandomElementFromCollection(@NotEmpty Collection<T> collection) {
        int randomInt = getRandomInt(0, collection.size() - 1);
        Object[] ids = collection.toArray(Object[]::new);
        return (T) ids[randomInt];
    }

    private <T> int getRandomInt(int origin, int bound) {
        return bound > origin ?
                ThreadLocalRandom.current().nextInt(origin, bound)
                : origin;
    }
}
