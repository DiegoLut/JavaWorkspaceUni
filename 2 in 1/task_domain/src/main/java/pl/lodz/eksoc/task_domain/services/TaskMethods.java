package pl.lodz.eksoc.task_domain.services;

import pl.lodz.eksoc.task_domain.interfaces.TaskServiceInterface;
import pl.lodz.eksoc.task_domain.models.Task;
import pl.lodz.eksoc.task_domain.models.TaskStatus;
import pl.lodz.eksoc.task_domain.models.TaskType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TaskMethods implements TaskServiceInterface {
    @Override
    public void markTasksAsCompleted(List<Integer> taskIds, List<Task> tasks) {
        for (Task task : tasks) {
            if (taskIds.contains(task.getId())) {
                task.setStatus(TaskStatus.wykonane);
            }
        }
    }

    @Override
    public void sortTasksByPriority(List<Task> tasks) {
        //SORTUJE ZADANIA OD NAJWYŻSZEGO
        tasks.sort(Comparator.comparingInt(Task::getPriority).reversed());
    }

    @Override
    public void sortTasksByCreationDate(List<Task> tasks) {
        //SORTUJE ZADANIA OD NAJWCZEŚNIEJSZEJ DATY DO NAJPÓŹNIEJSZEJ
        tasks.sort(Comparator.comparing(Task::getCreationDate));
    }

    @Override
    public String displayTasks(List<Task> list) {
        return list.stream()
                .map(task -> String.format("Id: %d, Name: %s, Type: %s, Priority: %d, Creation Date: %s, Status: %s",
                        task.getId(), task.getName(), task.getType(), task.getPriority(), task.getCreationDate(), task.getStatus()))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String displayTask(Task task) {
        return String.format("Id: %d, Name: %s, Type: %s, Priority: %d, Creation Date: %s, Status: %s",
                task.getId(), task.getName(), task.getType(), task.getPriority(), task.getCreationDate(), task.getStatus());
    }

//                 STRING.FORMAT
//            %d: Liczby całkowite (Integer).
//            %f: Liczby zmiennoprzecinkowe (Floating-point).
//            %s: Łańcuchy znaków (String).
//            %c: Pojedyncze znaki (Character).
//            %b: Wartości logiczne (Boolean).
    @Override
    public String generateReport(List<Task> tasks) {
        long completedTasks = tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.wykonane)
                .count();

        Task maxPriorityTask = tasks.stream()
                .filter(task -> task.getStatus() != TaskStatus.wykonane)
                .max(Comparator.comparingInt(Task::getPriority))
                .orElse(null);

        Map<TaskType, Long> uncompletedTasksByType = tasks.stream()
                .filter(task -> task.getStatus() != TaskStatus.wykonane)
                .collect(Collectors.groupingBy(Task::getType, Collectors.counting()));

        StringBuilder sb = new StringBuilder();

        sb.append("\n\n------------> RAPORT <------------\n");
        uncompletedTasksByType.forEach((type, count) -> sb.append(String.format("Typ: %s, Liczba niewykonanych " +
                "zadań: %d\n", type, count)));

        sb.append(String.format("Liczba wykonanych zadań: %d\n", completedTasks));

        var TaskMethods = new TaskMethods();

        sb.append(String.format("Niewykonane zadanie o najwyższym priorytecie: %s \n", TaskMethods.displayTask(maxPriorityTask)));

        return sb.toString();
    }
}
