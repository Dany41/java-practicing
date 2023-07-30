package org.practicalunittesting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.github.javafaker.Faker;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UserToPersonConverterTest {

    private static final Faker faker = Faker.instance();

    private static Stream<Arguments> getRandomNames() {
        return Stream.generate(() -> Arguments.of(
                faker.name().firstName(),
                faker.name().lastName()
        ))
                .limit(100);
    }


    @ParameterizedTest
    @MethodSource(value = "getRandomNames")
    void shouldConvertUserNamesIntoPersonNick(String name, String surname) {
        User user = new User(name, surname);
        Person person = UserToPersonConverter.convert(user);

        assertThat(person.getNickname()).isEqualTo(name + " " + surname);
    }

}