package pl.lodz.eksoc.task_domain.interfaces;

import com.opencsv.exceptions.CsvValidationException;
import pl.lodz.eksoc.task_domain.models.Task;

import java.io.IOException;
import java.util.List;

public interface TaskSerializerInterface {
    public <T> void serialize(List<T> list, String fileName) throws IOException;

    public <T> List<T> deserialize( String fileName) throws IOException, ClassNotFoundException, CsvValidationException;
}
