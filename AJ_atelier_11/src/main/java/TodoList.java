import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<Task> tasks = new ArrayList<>();
    public boolean addTask(Task s) {
        if (containsTask(s)){
            return false;
        }
        return tasks.add(s);
    }

    public boolean containsTask(Task s) {
        return tasks.contains(s);
    }

    public boolean removeTask(Task s) {
        return tasks.remove(s);
    }
}
