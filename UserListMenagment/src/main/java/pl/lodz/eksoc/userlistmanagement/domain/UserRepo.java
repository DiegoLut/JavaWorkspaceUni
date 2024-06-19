package pl.lodz.eksoc.userlistmanagement.domain;

import com.opencsv.exceptions.CsvValidationException;
import pl.lodz.eksoc.userlistmanagement.interfaces.SerializersInterface;
import pl.lodz.eksoc.userlistmanagement.services.UserSerializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private List<User> list = new ArrayList<>();
    private final SerializersInterface serializer = new UserSerializers();


    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public void addUser(User user) {
        list.add(user);
    }

    public void removeUserByIndex(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    public void printAllUsers(){
        getList().forEach(user -> System.out.println(user.toString() + "\n"));
    }

    public void saveToCSV(String fileName) throws IOException {
        serializer.serializeToCSV(list, fileName);
    }

    public void getFromCSV(String fileName) throws CsvValidationException, IOException, ClassNotFoundException {
        list = serializer.deserializeFromCSV(fileName);
    }

    public void saveToXML(String fileName) throws IOException {
        serializer.serializeToXML(list, fileName);
    }

    public void getFromXML(String fileName) throws IOException, ClassNotFoundException {
        list = serializer.deserializeFromXML(fileName);
    }

    public void saveToJSON(String fileName) throws IOException {
        serializer.serializeToCSV(list, fileName);
    }

    public void getFromJSON(String fileName) throws IOException, ClassNotFoundException {
        list = serializer.deserializeFromJSON(fileName);
    }

    public void saveToBinary(String fileName) throws IOException {
        serializer.serializeToBinary(list, fileName);
    }

    public void getFromBinary(String fileName) throws IOException, ClassNotFoundException {
        list = serializer.deserializeFromBinary(fileName);
    }
}