package pl.lodz.eksoc.userlistmanagement.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.lodz.eksoc.userlistmanagement.interfaces.UserSerializerInterface;
import pl.lodz.eksoc.userlistmanagement.domain.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer implements UserSerializerInterface {
    @Override
    public <T> void serialize(List<T> list, String fileName) throws IOException
    {
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
    public <T> List<T> deserialize(String fileName) throws IOException, ClassNotFoundException
    {
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
}
