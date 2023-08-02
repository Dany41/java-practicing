package org.practicalunittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BoilerplateCodeTest {
    private Collaborator collaborator;
    private SUT sut;
    @BeforeEach
    void setUp() {
        sut = new SUT();
        collaborator = Mockito.mock(Collaborator.class);
        sut.setCollaborator(collaborator);
    }
    @Test
    void shouldReturnABC() {
        when(collaborator.doSth()).thenReturn("abc");
        assertThat(sut.useCollaborator()).isEqualTo("abc");
    }

}
