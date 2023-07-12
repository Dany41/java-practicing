package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FootballTeamTest {

    private static final int ANY_NUMBER = 123;

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 3, 10 })
    void constructorShouldSetGamesWon(int nbOfGamesWon) {
        FootballTeam team = new FootballTeam(nbOfGamesWon);

        assertThat(team.getGamesWon())
                .as("number of games won")
                .isEqualTo(nbOfGamesWon);
    }

    @ParameterizedTest
    @ValueSource(ints = { -10, -1 })
    void constructorShouldThrowExceptionForIllegalGamesNb(int illegalNbOfGames) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new FootballTeam(illegalNbOfGames));
    }

    @Test
    void shouldBePossibleToCompareTeams() {
        FootballTeam team = new FootballTeam(ANY_NUMBER);

        assertThat(team).isInstanceOf(Comparable.class);
    }

    @Test
    void teamsWithMoreMatchesWonShouldBeGreater() {
        FootballTeam team_2 = new FootballTeam(2);
        FootballTeam team_3 = new FootballTeam(3);

        assertThat(team_3.compareTo(team_2))
                .as("team with 3 games won should be ranked before team with 2 games won")
                .isGreaterThan(0);
    }

    @Test
    void teamsWithLessMatchesWonShouldBeLesser() {
        FootballTeam team_2 = new FootballTeam(2);
        FootballTeam team_3 = new FootballTeam(3);

        assertThat(team_2.compareTo(team_3))
                .as("team with 2 games won should be ranked after team with 3 games won")
                .isLessThan(0);
    }

    @Test
    void teamsWithSameNumberOfMatchesWonShouldBeEqual() {
        FootballTeam testA = new FootballTeam(2);
        FootballTeam testB = new FootballTeam(2);

        assertThat(testA.compareTo(testB)).isEqualTo(0);
    }

}