package org.practicalunittesting;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class OperatingSystemAssert extends AbstractAssert<OperatingSystemAssert, OperatingSystem> {
    protected OperatingSystemAssert(OperatingSystem operatingSystem, Class<?> selfType) {
        super(operatingSystem, selfType);
    }

    public static OperatingSystemAssert assertThat(OperatingSystem actual) {
        return new OperatingSystemAssert(actual, OperatingSystemAssert.class);
    }

    public OperatingSystemAssert is128bit() {
        isNotNull();
        Assertions.assertThat(actual.getNbOfBits()).isEqualTo(128);
        return this;
    }

    public OperatingSystemAssert wasReleasedIn(int year) {
        isNotNull();
        Assertions.assertThat(actual.getReleaseYear()).isEqualTo(year);
        return this;
    }

    public OperatingSystemAssert hasVersion(int version) {
        isNotNull();
        Assertions.assertThat(actual.getVersion()).isEqualTo(String.valueOf(version));
        return this;
    }
}
