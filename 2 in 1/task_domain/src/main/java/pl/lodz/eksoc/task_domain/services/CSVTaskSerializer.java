//package pl.lodz.eksoc.task_domain.sercives;
//
//import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;
//import pl.lodz.eksoc.task_domain.interfaces.TaskSerializerInterface;
//import pl.lodz.eksoc.task_domain.models.Task;
//import com.opencsv.exceptions.CsvValidationException;
//import pl.lodz.eksoc.task_domain.models.TaskStatus;
//import pl.lodz.eksoc.task_domain.models.TaskType;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class CSVTaskSerializer implements TaskSerializerInterface {
//
//    private static final Logger LOGGER = Logger.getLogger(CSVTaskSerializer.class.getName());
//
//    @Override
//    public <T> void serialize(List<T> list, String fileName) throws IOException {
//        File file = new File(fileName);
//
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//
//        if (file.canWrite()) {
//            try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
//                for (T item : list) {
//                    if (item instanceof Task) {
//                        Task task = (Task) item;
//                        String[] taskData = {
//                                String.valueOf(task.id()),
//                                task.name(),
//                                String.valueOf(task.type()),
//                                String.valueOf(task.priority()),
//                                String.valueOf(task.creationDate()),
//                                String.valueOf(task.status()),
//                        };
//                        writer.writeNext(taskData);
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public <T> List<T> deserialize(String fileName) throws IOException, ClassNotFoundException, CsvValidationException {
//        File file = new File(fileName);
//        List<T> tasks = new ArrayList<>();
//
//        if (file.canRead()) {
//            LOGGER.info("Reading file: " + fileName);
//            try (CSVReader reader = new CSVReader(new FileReader(file))) {
//                String[] nextLine;
//                while ((nextLine = reader.readNext()) != null) {
//                    try {
//                        Task task = new Task(
//                                Integer.parseInt(nextLine[0]),
//                                nextLine[1],
//                                TaskType.fromString(nextLine[2]),
//                                Integer.parseInt(nextLine[3]),
//                                LocalDate.parse(nextLine[4]),
//                                TaskStatus.niewykonane
//                        );
//                        tasks.add((T) task);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        LOGGER.severe("Error processing line: " + String.join(",", nextLine));
//                        LOGGER.log(Level.SEVERE, "Exception: ", e);
//                    }
//                }
//            } catch (IOException | CsvValidationException e) {
//                e.printStackTrace();
//                LOGGER.log(Level.SEVERE, "Exception: ", e);
//                throw e; // rethrow the exception after logging
//            }
//        } else {
//            LOGGER.severe("Cannot read file: " + fileName);
//        }
//
//        return tasks;
//    }
//}


package pl.lodz.eksoc.task_domain.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import pl.lodz.eksoc.task_domain.interfaces.TaskSerializerInterface;
import pl.lodz.eksoc.task_domain.models.Task;
import com.opencsv.exceptions.CsvValidationException;
import pl.lodz.eksoc.task_domain.models.TaskStatus;
import pl.lodz.eksoc.task_domain.models.TaskType;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVTaskSerializer implements TaskSerializerInterface {

    @Override
    public <T> void serialize(List<T> list, String fileName) throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        if (file.canWrite()) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
                for (T item : list) {
                    if (item instanceof Task) {
                        Task task = (Task) item;
                        String[] taskData = {
                                String.valueOf(task.getId()),
                                task.getName(),
                                String.valueOf(task.getType()),
                                String.valueOf(task.getPriority()),
                                String.valueOf(task.getCreationDate()),
                                String.valueOf(task.getStatus()),
                        };
                        writer.writeNext(taskData);
                    }
                }
            }
        }
    }

    @Override
    public <T> List<T> deserialize(String fileName) throws IOException, ClassNotFoundException, CsvValidationException {
        File file = new File(fileName);
        List<T> tasks = new ArrayList<>();

        if (file.canRead()) {
            try (CSVReader reader = new CSVReader(new FileReader(file))) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    Task task = new Task(
                            Integer.parseInt(nextLine[0]),
                            nextLine[1],
                            TaskType.valueOf(nextLine[2]),
                            Integer.parseInt(nextLine[3]),
                            LocalDate.parse(nextLine[4]),
                            TaskStatus.niewykonane
                    );
                    tasks.add((T) task);
                }
            }
        }

        return tasks;
    }
}

