package pl.lodz.eksoc.userlistmanagement.interfaces;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

public interface UserSerializerInterface {

    public <T> void serialize(List<T> list, String fileName) throws IOException;

    public <T> List<T> deserialize( String fileName) throws IOException, ClassNotFoundException, CsvValidationException;
}
