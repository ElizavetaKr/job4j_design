package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        input.add(4);
        input.add(5);
        input.add(6);
        ListUtils.removeIf(input, n -> n % 2 == 0);
        assertThat(input).hasSize(3).containsSequence(1, 3, 5);
    }


    @Test
    void whenReplaceIf() {
        input.add(4);
        input.add(5);
        input.add(6);
        ListUtils.replaceIf(input, n -> n % 2 != 0, 0);
        assertThat(input).hasSize(5).containsSequence(0, 0, 4, 0, 6);
    }

    @Test
    void whenRemoveAll() {
        input.add(4);
        input.add(5);
        input.add(6);
        List<Integer> element = new ArrayList<>(Arrays.asList(5, 1, 0, 1, 10, 2));
        ListUtils.removeAll(input, element);
        assertThat(input).hasSize(3).containsSequence(3, 4, 6);
    }

}