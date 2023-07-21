package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MockitoPracticeTest {

    @Test
    void mockitoCreatesInstancesOfRequestedTypes() {
        Money money = mock(Money.class);
        FootballTeam footballTeam = mock(FootballTeam.class);

        assertThat(money).isInstanceOf(Money.class);
        assertThat(footballTeam).isInstanceOf(FootballTeam.class);
    }

}