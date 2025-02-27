package edu.cmu.cs.cs214.rec02;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArrayIntQueueTest {
    private IntQueue queue;

    @Before
    public void setUp() {
        queue = new ArrayIntQueue();
    }

    @Test
    public void testEnqueueAndDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals("Dequeue should return 1", Integer.valueOf(1), queue.dequeue());
        assertEquals("Dequeue should return 2", Integer.valueOf(2), queue.dequeue());
        assertEquals("Dequeue should return 3", Integer.valueOf(3), queue.dequeue());
        assertEquals("Dequeue should return null", null, queue.dequeue());
    }

    @Test
    public void testPeek() {
        assertNull("Peek should return null on empty queue", queue.peek());
        queue.enqueue(5);
        assertEquals("Peek should return 5", Integer.valueOf(5), queue.peek());
        queue.enqueue(10);
        assertEquals("Peek should still return 5", Integer.valueOf(5), queue.peek()); // Peek should not remove element
    }

    @Test
    public void testIsEmpty() {
        assertTrue("Queue should be empty", queue.isEmpty());
        queue.enqueue(7);
        assertFalse("Queue should not be empty", queue.isEmpty());
        // assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue("Queue should be empty after removing all elements",
                queue.isEmpty());
    }

    @Test
    public void testClear() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.clear();
        assertTrue("Queue should be empty after clear", queue.isEmpty());
        assertNull("Peek should return null after clear", queue.peek());
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 15; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 15; i++) {
            assertEquals("Dequeue should return " + i, Integer.valueOf(i), queue.dequeue());
        }
    }

    @Test
    public void testCircularBehavior() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue(); // Remove 1
        queue.dequeue(); // Remove 2
        assertEquals("Size should return 1", 1, queue.size());
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals("Dequeue should return 3", Integer.valueOf(3), queue.dequeue());
        assertEquals("Dequeue should return 4", Integer.valueOf(4), queue.dequeue());
        assertEquals("Dequeue should return 5", Integer.valueOf(5), queue.dequeue());
        assertTrue("Queue should be empty at the end", queue.isEmpty());
    }

    @Test
    public void testEnsureCapacityInCircularBehavior() {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i + 1);
        }
        queue.dequeue(); // Remove 1
        queue.dequeue(); // Remove 2
        queue.enqueue(100);
        queue.enqueue(101);
        queue.enqueue(102);
        assertEquals(101, queue.check(9));
    }

    @Test
    public void testLength() {
        assertEquals("Initial length should be 10", 10, queue.length());
        for (int i = 0; i < 15; i++) {
            queue.enqueue(i);
        }
        assertTrue("Length should increase after exceeding capacity", queue.length() > 10);
    }

    @Test
    public void testCheck() {
        queue.enqueue(5);
        queue.enqueue(10);
        assertEquals("Check should return the correct element", 5, queue.check(0));
        assertEquals("Check should return the correct element", 10, queue.check(1));
    }
}
