package pl.lodz.eksoc.userlistmanagement.interfaces;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

public interface SerializersInterface {
    public <T> void serializeToCSV(List<T> list, String fileName) throws IOException;
    public <T> List<T> deserializeFromCSV( String fileName) throws IOException, ClassNotFoundException, CsvValidationException;
    public <T> void serializeToXML(List<T> list, String fileName) throws IOException;
    public <T> List<T> deserializeFromXML( String fileName) throws IOException, ClassNotFoundException;
    public <T> void serializeToJSON(List<T> list, String fileName) throws IOException;
    public <T> List<T> deserializeFromJSON( String fileName) throws IOException, ClassNotFoundException;
    public <T> void serializeToBinary(List<T> list, String fileName) throws IOException;
    public <T> List<T> deserializeFromBinary( String fileName) throws IOException, ClassNotFoundException;
}
