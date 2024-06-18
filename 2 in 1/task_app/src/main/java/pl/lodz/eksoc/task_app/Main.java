package pl.lodz.eksoc.task_app;

import com.opencsv.exceptions.CsvValidationException;
import pl.lodz.eksoc.task_domain.interfaces.TaskSerializerInterface;
import pl.lodz.eksoc.task_domain.models.Task;
import pl.lodz.eksoc.task_domain.services.CSVTaskSerializer;
import pl.lodz.eksoc.task_domain.services.TaskMethods;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Task> list = new ArrayList<>();

        String inputPathCsv = "/Users/kubalutomski/Java_workspace_eksoc/!/task_app/src/main/java/pl/lodz/eksoc/task_app/files/input/csv";
        String outputPathCsv = "/Users/kubalutomski/Java_workspace_eksoc/!/task_app/src/main/java/pl/lodz/eksoc/task_app/files/output/";


        System.out.println("Wczytywanie zadań z pliku csv\n");

        var CSVTaskSerializer = new CSVTaskSerializer();

        deserialize(CSVTaskSerializer, inputPathCsv).forEach(task -> list.add(task));

        System.out.println("Wczytane zadania z pliku csv:\n");

        var TaskMethods = new TaskMethods();

        System.out.println(TaskMethods.displayTasks(list));

        boolean validOptionSelected = false;

        while (!validOptionSelected) {
            System.out.println("\n\nWybierz sposób w jaki chcesz posortować liste:\n" +
                    "1 --> W kolejności dodania\n" +
                    "2 --> W kolejności priorytetów\n>>");

            String x = scanner.nextLine();

            switch (x) {
                case "1":
                    TaskMethods.sortTasksByCreationDate(list);
                    System.out.println(TaskMethods.displayTasks(list));
                    validOptionSelected = true;
                    break;
                case "2":
                    TaskMethods.sortTasksByPriority(list);
                    System.out.println(TaskMethods.displayTasks(list));
                    validOptionSelected = true;
                    break;
                default:
                    System.out.println("Wybrałeś niepoprawną opcję. Spróbuj ponownie.");
            }
        }

        boolean validInput = false;

        while (!validInput) {
            System.out.println("\nPodaj indeksy zadań, które chcesz oznaczyć jako wykonane (oddzielone spacjami):\n>>");
            String line = scanner.nextLine();
            List<Integer> ids;

            try {
                ids = Stream.of(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                if (ids.stream().allMatch(id -> id >= 1 && id <= list.size())) {
                    TaskMethods.markTasksAsCompleted(ids, list);
                    validInput = true;
                } else {
                    System.out.println("Wprowadzone ID jest poza zakresem. Spróbuj ponownie.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wprowadzono niepoprawne ID. Upewnij się, że wpisujesz liczby oddzielone spacjami. Spróbuj ponownie.");
            }
        }

        System.out.println("Zaktualizowane zadania:\n");
        System.out.println(TaskMethods.displayTasks(list));

        System.out.println(TaskMethods.generateReport(list));

        System.out.println("Podaj nazwe jaka ma miec zapisywany plik:\n\n>>");

        String fileName = scanner.nextLine();

        outputPathCsv += fileName;

        serialize(CSVTaskSerializer, list, outputPathCsv);

        System.out.println("Lista zadan zostala zapisan w lokalizacji: " + outputPathCsv + "\n");

        System.out.println("Program zakonczony");

    }

    public static void serialize(TaskSerializerInterface serializer, List<Task> list, String fileName) {
        try {
            serializer.serialize(list, fileName);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Task> deserialize(TaskSerializerInterface serializer, String fileName) {
        try {
            return serializer.deserialize(fileName);
        } catch (IOException | ClassNotFoundException | CsvValidationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
}
