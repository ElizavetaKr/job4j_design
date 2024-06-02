package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Elizaveta=");
    }

    @Test
    void whenPatternNotComplete() {
        String path = "./data/pattern_not_complete.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPatternNotCompleteWithoutKey() {
        String path = "./data/pattern_not_complete_without_key.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPatternNotCompleteWithoutValue() {
        String path = "./data/pattern_not_complete_without_value.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }
}