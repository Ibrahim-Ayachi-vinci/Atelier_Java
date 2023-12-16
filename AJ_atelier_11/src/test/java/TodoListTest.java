import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {
    TodoList todoList;
    Task task;
    @BeforeEach
    void setUp() {
        todoList = new TodoList();
        task = new Task();
    }

    @Test
    void addTask() {
        assertAll(
                () -> assertTrue(todoList.addTask(task)),
                () -> assertTrue((todoList.containsTask(task)))
        );
    }

    @Test
    void addEmptyTask() {
        assertAll(
                () -> assertFalse(todoList.addTask(task)),
                () -> assertFalse(todoList.containsTask(task)),
                () -> assertFalse(todoList.addTask(null)),
                () -> assertFalse(todoList.containsTask(null))
        );
    }

    @Test
    void addExistingTask() {
        todoList.addTask(task);
        assertFalse(todoList.addTask(task));
    }

    @Test
    void removeTask() {
        todoList.addTask(task);
         assertTrue(todoList.removeTask(task));
    }

    @Test
    void removeInexestingTask() {
        assertFalse(todoList.removeTask(task));
    }
}