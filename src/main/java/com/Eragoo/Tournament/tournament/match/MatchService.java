package com.Eragoo.Tournament.tournament.match;

import com.Eragoo.Tournament.error.exception.NotFoundException;
import com.Eragoo.Tournament.participant.Participant;
import com.Eragoo.Tournament.participant.ParticipantDto;
import com.Eragoo.Tournament.participant.ParticipantMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@AllArgsConstructor
@Transactional
public class MatchService {
    private RandomMatchScoresDecisionManager scoresDecisionManager;
    private ParticipantMapper participantMapper;
    private MatchRepository matchRepository;

    public MatchResults getResults(long id) {
        Match match = getMatchIfExist(id);
        play(match);
        Participant winner = getWinner(match);
        ParticipantDto winnerDto = participantMapper.entityToDto(winner);
        long scoreDiff = Math.abs(match.getBlueScore() - match.getRedScore());
        return new MatchResults(winnerDto, scoreDiff);
    }

    private Match getMatchIfExist(long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Match with id " + id + "not found"));
    }

    private Participant getWinner(@NotNull Match match) {
        return match.getBlueScore() > match.getRedScore() ? match.getBlueParticipant() : match.getRedParticipant();
    }

    private void play(@NotNull Match match) {
        scoresDecisionManager.setMatchScores(match);
    }
}
