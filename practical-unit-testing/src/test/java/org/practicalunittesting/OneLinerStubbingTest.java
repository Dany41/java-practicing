package org.practicalunittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OneLinerStubbingTest {
    private Collaborator collaborator = when(mock(Collaborator.class).doSth()).thenReturn("abc").getMock();
    private SUT sut;

    @BeforeEach
    void setUp() {
        sut = new SUT();
        sut.setCollaborator(collaborator);
    }

    @Test
    void shouldReturnABC() {
        assertThat(sut.useCollaborator()).isEqualTo("abc");
    }


}
