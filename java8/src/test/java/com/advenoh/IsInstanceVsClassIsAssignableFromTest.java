package com.advenoh;

import com.advenoh.model.Animal;
import com.advenoh.model.Feline;
import com.advenoh.model.Lion;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class IsInstanceVsClassIsAssignableFromTest {
    @Test
    public void isInstance() {
        Animal animal = new Feline();
        Feline feline = new Feline();
        Lion lion = new Lion();
        Feline lion2 = new Lion();
        Animal noAnimal = null;

        //Animal 인터페이스
        assertThat(Animal.class.isInstance(animal)).isTrue();
        assertThat(Animal.class.isInstance(feline)).isTrue();
        assertThat(Animal.class.isInstance(lion)).isTrue();
        assertThat(Animal.class.isInstance(lion2)).isTrue();

        assertThat(Animal.class.isInstance(noAnimal)).isFalse();

        //Feline 클래스
        assertThat(Feline.class.isInstance(animal)).isTrue();
        assertThat(Feline.class.isInstance(feline)).isTrue();
        assertThat(Feline.class.isInstance(lion)).isTrue();
        assertThat(Feline.class.isInstance(lion2)).isTrue();

        //Lion 클래스
        assertThat(Lion.class.isInstance(lion)).isTrue();
        assertThat(Lion.class.isInstance(lion2)).isTrue();
        assertThat(Lion.class.isInstance(animal)).isFalse();
        assertThat(Lion.class.isInstance(feline)).isFalse();
    }

    @Test
    public void instanceOf() {
        Animal animal = new Feline();
        Feline feline = new Feline();
        Lion lion = new Lion();
        Feline lion2 = new Lion();
        Animal noAnimal = null;

        assertThat(animal instanceof Animal).isTrue();
        assertThat(feline instanceof Animal).isTrue();
        assertThat(lion instanceof Animal).isTrue();
        assertThat(lion2 instanceof Animal).isTrue();
        assertThat(noAnimal instanceof Animal).isFalse();

        assertThat(animal instanceof Feline).isTrue();
        assertThat(feline instanceof Feline).isTrue();
        assertThat(lion instanceof Feline).isTrue();
        assertThat(lion2 instanceof Feline).isTrue();

        assertThat(lion instanceof Lion).isTrue();
        assertThat(lion2 instanceof Lion).isTrue();
        assertThat(animal instanceof Lion).isFalse();
        assertThat(feline instanceof Lion).isFalse();

    }

//    @Test
//    public void whenUsingIsAssignableFromProperly_desiredResultsHappen() {
//        Shape shape = new Triangle();
//        Triangle triangle = new Triangle();
//        IsoscelesTriangle isoscelesTriangle = new IsoscelesTriangle();
//        Triangle isoscelesTriangle2 = new IsoscelesTriangle();
//
//        assertFalse(triangle.getClass().isAssignableFrom(Shape.class));
//        assertTrue(triangle.getClass().isAssignableFrom(shape.getClass()));
//        assertTrue(triangle.getClass().isAssignableFrom(triangle.getClass()));
//        assertTrue(triangle.getClass().isAssignableFrom(isoscelesTriangle.getClass()));
//        assertTrue(triangle.getClass().isAssignableFrom(isoscelesTriangle2.getClass()));
//
//        assertFalse(isoscelesTriangle.getClass().isAssignableFrom(Shape.class));
//        assertFalse(isoscelesTriangle.getClass().isAssignableFrom(shape.getClass()));
//        assertFalse(isoscelesTriangle.getClass().isAssignableFrom(triangle.getClass()));
//        assertTrue(isoscelesTriangle.getClass().isAssignableFrom(isoscelesTriangle.getClass()));
//        assertTrue(isoscelesTriangle.getClass().isAssignableFrom(isoscelesTriangle2.getClass()));
//
//        assertFalse(isoscelesTriangle2.getClass().isAssignableFrom(Shape.class));
//        assertFalse(isoscelesTriangle2.getClass().isAssignableFrom(shape.getClass()));
//        assertFalse(isoscelesTriangle2.getClass().isAssignableFrom(triangle.getClass()));
//        assertTrue(isoscelesTriangle2.getClass().isAssignableFrom(isoscelesTriangle.getClass()));
//        assertTrue(isoscelesTriangle2.getClass().isAssignableFrom(isoscelesTriangle2.getClass()));
//    }

    @Test
    public void isAssignableFrom() {
        Animal animal = new Feline();
        Feline feline = new Feline();
        Lion lion = new Lion();
        Feline lion2 = new Lion();
        Animal noAnimal = null;

        assertThat(feline.getClass().isAssignableFrom(Animal.class)).isTrue();
        assertThat(lion.getClass().isAssignableFrom(Animal.class)).isTrue();
        assertThat(lion instanceof Animal).isTrue();
        assertThat(lion2 instanceof Animal).isTrue();
        assertThat(noAnimal instanceof Animal).isFalse();

        assertThat(animal instanceof Feline).isTrue();
        assertThat(feline instanceof Feline).isTrue();
        assertThat(lion instanceof Feline).isTrue();
        assertThat(lion2 instanceof Feline).isTrue();

        assertThat(lion instanceof Lion).isTrue();
        assertThat(lion2 instanceof Lion).isTrue();
        assertThat(animal instanceof Lion).isFalse();
        assertThat(feline instanceof Lion).isFalse();

    }

    @Test
    public void isAssignableFrom_NullPointerException() {

    }

    @Test
    public void isAssignableFrom_primitive() {
        int intValue = 5;

//        assertThat()
    }
}
