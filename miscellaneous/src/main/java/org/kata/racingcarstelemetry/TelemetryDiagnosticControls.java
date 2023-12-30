package org.kata.racingcarstelemetry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class TelemetryDiagnosticControls {
    private final String DIAGNOSTIC_CHANNEL_CONNECTION_STRING = "*111#";

    private final TelemetryClient telemetryClient;

    @Getter
    @Setter
    private String diagnosticInfo = "";

    @Getter
    @Setter
    private int retries = 3;

    public TelemetryDiagnosticControls() {
        telemetryClient = new TelemetryClient();
    }

    public void checkTransmission() {
        resetConnection();
        establishConnectionWithRetry(retries);
        receiveDiagnosticInfo();
    }

    public void receiveDiagnosticInfo() {
        telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
        diagnosticInfo = telemetryClient.receive();
    }

    public void establishConnectionWithRetry(int retries) {
        while (!telemetryClient.getOnlineStatus() && retries > 0) {
            telemetryClient.connect(DIAGNOSTIC_CHANNEL_CONNECTION_STRING);
            retries -= 1;
        }

        if (!telemetryClient.getOnlineStatus()) {
            throw new TelemetryClientConnectionException("Unable to connect.");
        }
    }

    public void resetConnection() {
        diagnosticInfo = "";
        telemetryClient.disconnect();
    }
}
