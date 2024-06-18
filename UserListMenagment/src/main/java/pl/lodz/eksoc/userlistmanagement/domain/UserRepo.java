package pl.lodz.eksoc.userlistmanagement.domain;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private List<User> list = new ArrayList<>();

    public List<User> getList()
    {
        return list;
    }

    public void setList(List<User> list)
    {
        this.list = list;
    }

    public void addUser(User user)
    {
        list.add(user);
    }
    public void removeUserByIndex(int index)
    {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
