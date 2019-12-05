package src.com.jdk.test.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void test_vector_construct() {
        Vector<Object> vector = new Vector<>();
        assertEquals(10, vector.capacity());
        Vector<Object> initializeVector = new Vector<>(1);
        assertEquals(1, initializeVector.capacity());
        initializeVector.addAll(Collections.unmodifiableList(Arrays.asList(1, 2)));
        assertEquals(2, initializeVector.capacity());
        Vector<Object> incrementVector = new Vector<>(1, 10);
        assertEquals(1, incrementVector.capacity());
        incrementVector.addAll(Collections.unmodifiableList(Arrays.asList(1, 2)));
        assertEquals(2, incrementVector.size());
        assertEquals(11, incrementVector.capacity());
    }

    @Test
    void name() {

    }
}
