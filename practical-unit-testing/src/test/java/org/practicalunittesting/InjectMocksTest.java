package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InjectMocksTest {

    @Mock
    private Collaborator collaborator;

    @InjectMocks
    private SUT sut = new SUT();

    @Test
    void shouldReturnABC() {
        when(collaborator.doSth()).thenReturn("abc");
        assertThat(sut.useCollaborator()).isEqualTo("abc");
    }

}
