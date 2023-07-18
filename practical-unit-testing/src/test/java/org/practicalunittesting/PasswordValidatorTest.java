package org.practicalunittesting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"qwertyuiop12", "11234567890", "asdfghjkl;"})
    void lengthValidatorShouldValidatePasswordsWithAtLeastTenSymbols(String validPassword) {
        boolean isPasswordValid = PasswordValidator.validateLength(validPassword);
        assertThat(isPasswordValid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "ab", "abc", "abcd", "qwert", "asdfgh", "qwerty12", "qazxswed", "123qweasd"})
    void lengthValidatorShouldNotValidatePasswordsWithLessThanTenSymbols(String invalidPassword) {
        boolean isPasswordValid = PasswordValidator.validateLength(invalidPassword);
        assertThat(isPasswordValid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"rty123y", "edfrt321", "jhmnb987", "123", "32111", "12333aaa"})
    void digitsValidatorShouldValidatePasswordsContainingThreeOrMoreDigits(String validPassword) {
        boolean isPasswordValid = PasswordValidator.validateContainingDigits(validPassword);
        assertThat(isPasswordValid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"rtyy", "edfrt1", "jhmnb98", "13", "3", "aaa"})
    void digitsValidatorShouldNotValidatePasswordsContainingLessThanThreeDigits(String invalidPassword) {
        boolean isPasswordValid = PasswordValidator.validateContainingDigits(invalidPassword);
        assertThat(isPasswordValid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1_", "_321ds", "fsdfs_mkfls", "_____", "_as_fd_"})
    void specialSymbolValidatorShouldValidateIfPasswordContainsUnderscore(String validPassword) {
        boolean isPasswordValid = PasswordValidator.validateSpecialSymbols(validPassword);
        assertThat(isPasswordValid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "321ds", "fsdfsmkfls", "", "asfd"})
    void specialSymbolValidatorShouldNotValidateIfPasswordDoesNotContainUnderscore(String invalidPassword) {
        boolean isPasswordValid = PasswordValidator.validateSpecialSymbols(invalidPassword);
        assertThat(isPasswordValid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"qweasd123_", "_qweras1234", "___123fffa"})
    void validatorShouldValidatePasswordsThatComplyWithAllRules(String validPasswords) {
        boolean isPasswordValid = PasswordValidator.validate(validPasswords);
        assertThat(isPasswordValid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "fds", "qweasdzxcq", "123", "_fd", "qweasd123r", "12_34", "rewqasdfc_"})
    void validatorShouldNotValidatePasswordsThatFailAtLeastOneOfRules(String invalidPassword) {
        boolean isPasswordValid = PasswordValidator.validate(invalidPassword);
        assertThat(isPasswordValid).isFalse();
    }

}
