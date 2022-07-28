package com.aston.customarraylist.service.impl;

import com.aston.customarraylist.service.CustomArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.aston.customarraylist.constants.CustomArrayListConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class customArrayListImplTest {

    @Test
    void shouldThrowExceptionWhenCreatWithNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> new CustomArrayListImpl<>(MINUS_ONE));
    }

    @Test
    void shouldReturnItemWhenAddItem() {
        CustomArrayList<String> out = new CustomArrayListImpl<>();
        assertEquals(TEST_STRING, out.add(TEST_STRING));
    }

    @Test
    void shouldReturnItemWhenAddingItemsMoreThanInitVolume() {
        CustomArrayList<String> out = new CustomArrayListImpl<>(NUMBER_FOR_VOLUME);
        for (int i = ZERO; i < NUMBER_FOR_VOLUME + NUMBER_FOR_VOLUME; i++) {
            assertEquals(TEST_STRING, out.add(TEST_STRING));
        }
    }

    @Test
    void shouldReturnItemWhenAddNull() {
        CustomArrayList<String> out = new CustomArrayListImpl<>();
        assertNull(out.add(null));
    }

    @Test
    void shouldThrowExceptionWhenAddNotValidIndex() {
        CustomArrayList<String> out = new CustomArrayListImpl<>();
        assertThrows(IndexOutOfBoundsException.class, () -> out.add(MINUS_ONE, TEST_STRING));
    }

    @Test
    void shouldReturnCorrectTextWhenAddWithIndex() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        TEST_STRING.chars().forEach(c -> out.add((char) c));
        out.add(FIVE, TEST_CHAR_F);
        for (int i = 0; i < TEST_STRING_WITH_F.length() - 1; i++) {
            assertEquals(TEST_STRING_WITH_F.charAt(i), out.get(i));
        }
    }

    @Test
    void shouldReturnTrueWhenAddedCollectionToList() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        List<Character> actualList = new ArrayList<>();
        TEST_STRING.chars().forEach(c -> actualList.add((char) c));
        TEST_STRING.chars().forEach(c -> out.add((char) c));
        assertTrue(out.addAll(actualList));
        actualList.addAll(actualList);
        for (int i = 0; i < out.size(); i++) {
            assertEquals(actualList.get(i), out.get(i));
        }
    }

    @Test
    void shouldClearListWhenCallClear() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        TEST_STRING.chars().forEach(c -> out.add((char) c));
        out.clear();
        for (int i = 0; i < out.size(); i++) {
            assertNull(out.get(ZERO));
        }
    }

    @Test
    void shouldReturnIndexWhenFindObject() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        TEST_STRING_WITH_F.chars().forEach(c -> out.add((char) c));
        assertEquals(FIVE, out.indexOf(TEST_CHAR_F));
    }

    @Test
    void shouldReturnTrueWhenCheckIsEmpty() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        assertTrue(out.isEmpty());
    }

    @Test
    void shouldReturnCorrectStringWhenRemoveCharByIndex() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        TEST_STRING_WITH_F.chars().forEach(c -> out.add((char) c));
        out.remove(FIVE);
        for (int i = 0; i < out.size(); i++) {
            assertEquals(TEST_STRING.charAt(i), out.get(i));
        }
    }

    @Test
    void shouldReturnCorrectStringWhenRemoveCharByValue() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        TEST_STRING_WITH_F.chars().forEach(c -> out.add((char) c));
        assertTrue(out.remove(TEST_CHAR_F));
        for (int i = 0; i < out.size(); i++) {
            assertEquals(TEST_STRING.charAt(i), out.get(i));
        }
    }

    @Test
    void shouldReturnFalseWhenRemoveNonExistentChar() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        TEST_STRING.chars().forEach(c -> out.add((char) c));
        assertFalse(out.remove(TEST_CHAR_F));
    }

    @Test
    void shouldReturnReplaceCharsWhenCallReplace() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        TEST_STRING.chars().forEach(c -> out.add((char) c));
        assertEquals(THREE, out.replace(TEST_CHAR_T, TEST_CHAR_F));
        for (int i = 0; i < out.size(); i++) {
            assertEquals(TEST_STRING_AFTER_REPLACE.charAt(i), out.get(i));
        }
    }

    @Test
    void shouldReturnAddedCharWhenCallSet() {
        CustomArrayList<Character> out = new CustomArrayListImpl<>();
        TEST_STRING.chars().forEach(c -> out.add((char) c));
        out.set(FIVE, TEST_CHAR_F);
        assertEquals(TEST_CHAR_F, out.get(FIVE));
    }

    @Test
    void shouldReturnTrueWhenCheckEquals() {
        CustomArrayList<Character> outExpect = new CustomArrayListImpl<>();
        CustomArrayList<Character> outActual = new CustomArrayListImpl<>();
        TEST_STRING_WITH_F.chars().forEach(c -> outExpect.add((char) c));
        TEST_STRING_WITH_F.chars().forEach(c -> outActual.add((char) c));
        assertTrue(outExpect.equals(outActual));
    }

}
