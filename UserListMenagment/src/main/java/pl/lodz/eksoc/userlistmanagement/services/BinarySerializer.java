package pl.lodz.eksoc.userlistmanagement.services;

import com.opencsv.exceptions.CsvValidationException;
import pl.lodz.eksoc.userlistmanagement.interfaces.UserSerializerInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinarySerializer implements UserSerializerInterface {

    @Override
    public <T> void serialize(List<T> list, String fileName) throws IOException
    {
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
    public <T> List<T> deserialize(String fileName) throws IOException, ClassNotFoundException
    {
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
        // ZAWSZE LEPIEJ JEST ZWRACAĆ PUSTA LISTĘ NIŻ NULL
        return new ArrayList();
    }
}
