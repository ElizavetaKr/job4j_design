package ru.job4j.ood.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class GeneratorClassTest {
    @Test
    void whenProduceWithValidArgumentsThenValidString() {
        String template = "I am ${name}, Who are ${subject}? ";
        Map args = Map.of("name", "Liza", "subject", "you");
        GeneratorClass gc = new GeneratorClass();
        assertThat(gc.produce(template, args)).isEqualTo("I am Liza, Who are you? ");
    }

    @Test
    void whenKeysIsMissingInMapThenGetException() {
        String template = "I am ${name}, Who are ${subject}? ";
        Map args = Map.of("subject", "you");
        GeneratorClass gc = new GeneratorClass();
        assertThatThrownBy(() -> gc.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMoreKeysWhenWeNeedThenGetException() {
        String template = "I am ${name}, Who are ${subject}? ";
        Map args = Map.of("name", "Liza", "subject", "you", "key", "test");
        GeneratorClass gc = new GeneratorClass();
        assertThatThrownBy(() -> gc.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

}