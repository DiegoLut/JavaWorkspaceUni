package pl.lodz.eksoc.userlistmanagement.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import pl.lodz.eksoc.userlistmanagement.domain.User;
import pl.lodz.eksoc.userlistmanagement.interfaces.SerializersInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserSerializers implements SerializersInterface {
    @Override
    public <T> void serializeToCSV(List<T> list, String fileName) throws IOException {
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
    public <T> List<T> deserializeFromCSV(String fileName) throws IOException, ClassNotFoundException, CsvValidationException {
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

    @Override
    public <T> void serializeToXML(List<T> list, String fileName) throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        if (file.canWrite()) {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(file, list);
        }
    }

    @Override
    public <T> List<T> deserializeFromXML(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);

        if (file.canRead()) {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        }

        return new ArrayList<>();
    }

    @Override
    public <T> void serializeToJSON(List<T> list, String fileName) throws IOException {
        File file = new File(fileName);

        if(!file.exists())
        {
            file.createNewFile();
        }

        if(file.canWrite())
        {
            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(file, list);
        }
    }

    @Override
    public <T> List<T> deserializeFromJSON(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);

        if (file.canRead())
        {
            var mapper = new ObjectMapper();

            return mapper.readValue(
                    file,
                    mapper.getTypeFactory().constructCollectionType(List.class, User.class));

        }

        return new ArrayList();
    }

    @Override
    public <T> void serializeToBinary(List<T> list, String fileName) throws IOException {
        File file = new File(fileName);

        if(!file.exists()) {file.createNewFile();}

        if(file.canWrite())
        {
            try(var objectStream = new ObjectOutputStream(new FileOutputStream(file)))
            {
                objectStream.writeObject(list);
            }
        }
    }

    @Override
    public <T> List<T> deserializeFromBinary(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);

        if(file.canRead())
        {
            try(var objectStream = new ObjectInputStream(new FileInputStream(file)))
            {
                Object o = objectStream.readObject();

                if(o != null && o.getClass() == ArrayList.class)
                {
                    return (ArrayList<T>) o;
                }
            }
        }
        return new ArrayList();
    }
}
