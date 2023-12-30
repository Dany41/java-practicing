package org.kata.racingcarstelemetry;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelemetryDiagnosticControlsTest {

    private final String DIAGNOSTIC_CHANNEL_CONNECTION_STRING = "*111#";

    @Mock
    private TelemetryClient mockTelemetryClient;

    @InjectMocks
    private TelemetryDiagnosticControls telemetryDiagnosticControls;
    private final String ANY_DIAGNOSTIC_MESSAGE = "some diagnostic message";

    @Test
    void resetConnectionDisconnectsFromClientAndClearDiagnosticInfo() {
        telemetryDiagnosticControls.resetConnection();

        verify(mockTelemetryClient, atMostOnce()).disconnect();
        assertThat(telemetryDiagnosticControls.getDiagnosticInfo()).isEmpty();
    }

    @Test
    void establishConnectionWithRetryDoExactNumberOfTriesThenFails() {
        when(mockTelemetryClient.getOnlineStatus()).thenReturn(false);

        assertThatExceptionOfType(TelemetryClientConnectionException.class)
                .isThrownBy(() -> telemetryDiagnosticControls.establishConnectionWithRetry(telemetryDiagnosticControls.getRetries()));
        verify(mockTelemetryClient, times(telemetryDiagnosticControls.getRetries())).connect(DIAGNOSTIC_CHANNEL_CONNECTION_STRING);
    }

    @Test
    void establishConnectionWithRetryDoOnlyOneConnectIfSuccessfulConnection() {
        when(mockTelemetryClient.getOnlineStatus()).thenReturn(false, true);

        telemetryDiagnosticControls.establishConnectionWithRetry(telemetryDiagnosticControls.getRetries());
        verify(mockTelemetryClient, times(1)).connect(DIAGNOSTIC_CHANNEL_CONNECTION_STRING);
    }

    @Test
    void receiveDiagnosticInfoSendsMessageToClientAndStoreTheReceivedInfo() {
        when(mockTelemetryClient.receive()).thenReturn(ANY_DIAGNOSTIC_MESSAGE);

        telemetryDiagnosticControls.receiveDiagnosticInfo();

        verify(mockTelemetryClient, atMostOnce()).send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        verify(mockTelemetryClient, atMostOnce()).receive();
        assertThat(telemetryDiagnosticControls.getDiagnosticInfo()).isEqualTo(ANY_DIAGNOSTIC_MESSAGE);
    }

    @Test
    void checkTransmissionResetsConnectionAndAcquireDiagnosticInfo() {
        when(mockTelemetryClient.getOnlineStatus()).thenReturn(false, true);
        when(mockTelemetryClient.receive()).thenReturn(ANY_DIAGNOSTIC_MESSAGE);

        assertThat(telemetryDiagnosticControls.getDiagnosticInfo()).isEmpty();
        telemetryDiagnosticControls.checkTransmission();
        verify(mockTelemetryClient, atMostOnce()).disconnect();
        verify(mockTelemetryClient, atLeastOnce()).connect(DIAGNOSTIC_CHANNEL_CONNECTION_STRING);
        verify(mockTelemetryClient, atMostOnce()).receive();
        assertThat(telemetryDiagnosticControls.getDiagnosticInfo()).isEqualTo(ANY_DIAGNOSTIC_MESSAGE);
    }
}