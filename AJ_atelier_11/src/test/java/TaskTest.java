import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
    }

    @Test
    void createTask() {
        assertAll(
                () -> assertTrue(task.createTask("tache 2","dzdz"))
        );
    }
}