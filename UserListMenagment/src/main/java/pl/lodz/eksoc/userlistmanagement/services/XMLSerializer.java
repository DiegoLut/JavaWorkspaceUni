package pl.lodz.eksoc.userlistmanagement.services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pl.lodz.eksoc.userlistmanagement.interfaces.UserSerializerInterface;
import pl.lodz.eksoc.userlistmanagement.domain.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLSerializer implements UserSerializerInterface {
    @Override
    public <T> void serialize(List<T> list, String fileName) throws IOException {
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
    public <T> List<T> deserialize(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);

        if (file.canRead()) {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        }

        return new ArrayList<>();
    }
}
