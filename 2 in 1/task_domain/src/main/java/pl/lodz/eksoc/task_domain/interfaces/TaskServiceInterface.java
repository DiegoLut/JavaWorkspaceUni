package pl.lodz.eksoc.task_domain.interfaces;

import pl.lodz.eksoc.task_domain.models.Task;

import java.util.List;
import java.util.stream.Collectors;

public interface TaskServiceInterface {

    void markTasksAsCompleted(List<Integer> taskIds, List<Task> tasks);
    void sortTasksByPriority(List<Task> tasks);
    void sortTasksByCreationDate(List<Task> tasks);
    String displayTasks(List<Task> list);
    String displayTask(Task task);
    String generateReport(List<Task> tasks);
}
