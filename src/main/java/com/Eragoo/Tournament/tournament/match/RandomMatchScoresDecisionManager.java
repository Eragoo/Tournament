package com.Eragoo.Tournament.tournament.match;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomMatchScoresDecisionManager implements MatchScoresDecisionManager {

    @Override
    public void setMatchScores(Match match) {
        long blueScore = getRandomScore();
        long redScore = getRandomScore();
        match.setBlueScore(blueScore);
        match.setRedScore(redScore);
    }

    private long getRandomScore() {
        return ThreadLocalRandom.current().nextLong(MatchScoresBounds.MIN, MatchScoresBounds.MAX);
    }
}
