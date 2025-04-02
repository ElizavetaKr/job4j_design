package ru.job4j.ood.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FoolTest {
    @Test
    void whenNumberMultipleOf3ThenFizz() {
        assertThat(Fool.compare(3)).isEqualTo("Fizz");
        assertThat(Fool.compare(6)).isEqualTo("Fizz");
        assertThat(Fool.compare(33)).isEqualTo("Fizz");
        assertThat(Fool.compare(21)).isEqualTo("Fizz");
    }

    @Test
    void whenNumberMultipleOf5ThenBuzz() {
        assertThat(Fool.compare(5)).isEqualTo("Buzz");
        assertThat(Fool.compare(50)).isEqualTo("Buzz");
        assertThat(Fool.compare(35)).isEqualTo("Buzz");
        assertThat(Fool.compare(20)).isEqualTo("Buzz");
    }

    @Test
    void whenNumberMultipleOf3And5ThenFizzBuzz() {
        assertThat(Fool.compare(15)).isEqualTo("FizzBuzz");
        assertThat(Fool.compare(45)).isEqualTo("FizzBuzz");
        assertThat(Fool.compare(75)).isEqualTo("FizzBuzz");
    }

    @Test
    void whenNumberNotMultipleOf3And5ThenNumber() {
        assertThat(Fool.compare(1)).isEqualTo("1");
        assertThat(Fool.compare(7)).isEqualTo("7");
        assertThat(Fool.compare(17)).isEqualTo("17");
    }
}