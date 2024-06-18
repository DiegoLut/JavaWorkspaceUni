package pl.lodz.eksoc.userlistmanagement.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import pl.lodz.eksoc.userlistmanagement.interfaces.UserSerializerInterface;
import pl.lodz.eksoc.userlistmanagement.domain.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVSerializer implements UserSerializerInterface {
    @Override
    public <T> void serialize(List<T> list, String fileName) throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        if (file.canWrite()) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
                for (T item : list) {
                    if (item instanceof User) {
                        User user = (User) item;
                        String[] userData = {
                                user.getFirstName(),
                                user.getLastName(),
                                String.valueOf(user.getAge()),
                                user.getEmail()
                        };
                        writer.writeNext(userData);
                    }
                }
            }
        }
    }

    @Override
    public <T> List<T> deserialize(String fileName) throws IOException, ClassNotFoundException, CsvValidationException {
        File file = new File(fileName);
        List<T> users = new ArrayList<>();

        if (file.canRead()) {
            try (CSVReader reader = new CSVReader(new FileReader(file))) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    User user = new User(
                            nextLine[0],
                            nextLine[1],
                            Integer.parseInt(nextLine[2]),
                            nextLine[3]
                    );
                    users.add((T) user);
                }
            }
        }

        return users;
    }
}
