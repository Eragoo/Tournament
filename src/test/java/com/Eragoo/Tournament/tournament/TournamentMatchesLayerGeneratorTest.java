package com.Eragoo.Tournament.tournament;

import com.Eragoo.Tournament.participant.Participant;
import com.Eragoo.Tournament.tournament.match.Match;
import com.Eragoo.Tournament.tournament.match.MatchDto;
import com.Eragoo.Tournament.tournament.match.MatchMapper;
import com.Eragoo.Tournament.tournament.match.MatchRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

public class TournamentMatchesLayerGeneratorTest {
    private MatchRepository matchRepository = Mockito.mock(MatchRepository.class);
    private TournamentMatchesLayerGenerator layerGenerator;
    private MatchMapper matchMapper = (Match m) -> new MatchDto();

    @BeforeEach
    private void init() {
        layerGenerator = new TournamentMatchesLayerGenerator(matchMapper, matchRepository);
    }

    @Test
    public void generatesCorrectNumberOfMatches() {
        int participantsNumber = 8;
        Set<Participant> participants = generateParticipants(participantsNumber);
        Set<MatchDto> layerWithRandomParticipants = layerGenerator.createLayerWithRandomParticipants(participants);
        Assertions.assertEquals((participantsNumber + 1) / 2, layerWithRandomParticipants.size());
    }

    @Test
    public void generatesCorrectNumberOfMatchesWithOddParticipants() {
        int participantsNumber = 7;
        Set<Participant> participants = generateParticipants(participantsNumber);
        Set<MatchDto> layerWithRandomParticipants = layerGenerator.createLayerWithRandomParticipants(participants);
        Assertions.assertEquals((participantsNumber + 1) / 2, layerWithRandomParticipants.size());
    }

    private Set<Participant> generateParticipants(int amount) {
        Set<Participant> participants = new HashSet<>();
        Tournament tournament = new Tournament(0L, null, 0);
        for (int i = 0; i < amount; i++) {
            Participant nParticipant = new Participant(i, "Joe" + i, tournament);
            participants.add(nParticipant);
        }
        return participants;
    }
}
