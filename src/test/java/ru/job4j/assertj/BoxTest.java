package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isNotBlank()
                .startsWith("C")
                .isEqualTo("Cube");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(5, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .endsWith("ect")
                .isEqualTo("Unknown object");
    }

    @Test
    void whenGetNumberOfVertices5ThenMinus1() {
        Box box = new Box(5, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNegative()
                .isEqualTo(-1);
    }

    @Test
    void whenGetNumberOfVertices0Then0() {
        Box box = new Box(0, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isZero()
                .isEqualTo(0);
    }

    @Test
    void whenGetNumberOfVertices4Then4() {
        Box box = new Box(4, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEven()
                .isNotNegative()
                .isEqualTo(4);
    }

    @Test
    void whenIsExistThenTrue() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void whenIsExistThenFalse() {
        Box box = new Box(5, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void whenGetAreaForSphere2Then50Dot24() {
        Box box = new Box(0, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(50.24d, withPrecision(0.04d))
                .isGreaterThan(50.23d);
    }

    @Test
    void whenGetAreaForTetrahedron4Then27Dot71() {
        Box box = new Box(4, 4);
        double area = box.getArea();
        assertThat(area).isCloseTo(27.71d, withPrecision(0.003d));
    }

    @Test
    void whenGetAreaForUnknown4Then0() {
        Box box = new Box(5, 4);
        double area = box.getArea();
        assertThat(area).isCloseTo(0, withPrecision(0.003d))
                .isLessThan(5.2d);
    }

}