package pl.lodz.eksoc.userlistmanagement;

import pl.lodz.eksoc.userlistmanagement.domain.UserRepo;
import pl.lodz.eksoc.userlistmanagement.interfaces.UserSerializerInterface;
import pl.lodz.eksoc.userlistmanagement.domain.User;
import pl.lodz.eksoc.userlistmanagement.services.BinarySerializer;
import pl.lodz.eksoc.userlistmanagement.services.JSONSerializer;
import pl.lodz.eksoc.userlistmanagement.services.XMLSerializer;
import pl.lodz.eksoc.userlistmanagement.services.CSVSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pl.lodz.eksoc.userlistmanagement.services.Validations.isAgeValid;
import static pl.lodz.eksoc.userlistmanagement.services.Validations.isEmailValid;

public class Main {

    private static final UserRepo userRepo = new UserRepo();
    private static final UserSerializerInterface CSVSerializer = new CSVSerializer();
    private static final UserSerializerInterface XMLSerializer = new XMLSerializer();
    private static final UserSerializerInterface JSONSerializer = new JSONSerializer();
    private static final UserSerializerInterface BinarySerializer = new BinarySerializer();

    public static void main (String[] args)
    {
        boolean lunch = true;

        Scanner scanner = new Scanner(System.in);

        while(lunch)
        {
            System.out.println("Choose one of the options:\n\n" +
                    "1 -> Show user list\n" +
                    "2 -> Import users from file\n" +
                    "3 -> Add user to the list\n" +
                    "4 -> Delete user from list\n" +
                    "5 -> Save list as CSV file\n" +
                    "6 -> Exit program\n\n>>");

            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    userRepo.getList().forEach(user -> System.out.println(user.toString() + "\n"));
                    break;
                case "2":
                    System.out.println("Input a file path\n\n>>");

                    String path = scanner.nextLine();

                    System.out.println("Choose one of the options:\n\n" +
                            "1 -> Binary\n" +
                            "2 -> JSON\n" +
                            "3 -> XML\n" +
                            "4 -> CSV\n\n>>");

                    String file = scanner.nextLine();

                    switch (file) {
                        case "1":
                            deserialize(BinarySerializer, path).stream().forEach(user -> userRepo.addUser(user));

                            break;
                        case "2":
                            deserialize(JSONSerializer, path).stream().forEach(user -> userRepo.addUser(user));

                            break;
                        case "3":
                            deserialize(XMLSerializer, path).stream().forEach(user -> userRepo.addUser(user));

                            break;
                        case "4":
                            deserialize(CSVSerializer, path).stream().forEach(user -> userRepo.addUser(user));

                            break;
                        default:
                            System.out.println("Choose from options 1, 2, 3");
                            break;
                    }
                    break;
                case "3":
                    System.out.println("Input user first name\n\n>>");
                    String firstName = scanner.nextLine();

                    System.out.println("Input user last name\n\n>>");
                    String lastName = scanner.nextLine();

                    System.out.println("Input user age\n\n>>");
                    int age = Integer.parseInt(scanner.nextLine());

                    System.out.println("Input user email\n\n>>");
                    String email = scanner.nextLine();

                    if (isAgeValid(age))
                    {
                        if (isEmailValid(email))
                        {
                            userRepo.addUser(new User(firstName, lastName, age, email));
                        }
                        else
                        {
                            System.out.println("The email is not valid.");
                        }
                    }
                    else
                    {
                        System.out.println("The age is not valid.");
                    }
                    break;
                case "4":
                    int i = 0;

                    for(User user : userRepo.getList())
                    {
                        System.out.println(i + " --> " + user.toString() + "\n");

                        i++;
                    }

                    System.out.println("Choose index of the user you would like to delete:\n\n>>");

                    int index = Integer.parseInt(scanner.nextLine());

                    if(index > userRepo.getList().size() || index < 0)
                    {
                        System.out.println("You choose wrong index");
                    }
                    else
                    {
                        userRepo.removeUserByIndex(index);
                        System.out.println("User of index " + index + " has been removed");
                    }
                    break;
                case "5":
                    System.out.println("Input the name of the CSV file (without extension):\n\n>>");

                    String fileName = scanner.nextLine();

                    String filePath = "/Users/kubalutomski/Java_workspace_eksoc/UserListMenagment/src/main/java/pl/lodz/eksoc/userlistmanagement/files/output/" + fileName;


                    serialize(CSVSerializer, userRepo.getList(), filePath);

                    System.out.println("User list has been saved to " + filePath);

                    String pathToOthers = "/Users/kubalutomski/Java_workspace_eksoc/UserListMenagment/src/main/java/pl/lodz/eksoc/userlistmanagement/files/output/";

                    serialize(BinarySerializer, userRepo.getList(), pathToOthers + "BinaryUsers");
                    serialize(XMLSerializer, userRepo.getList(), pathToOthers + "XMLUsers");
                    serialize(JSONSerializer, userRepo.getList(), pathToOthers + "JSONUsers");

                    System.out.println("\nUser list has been also saved to Binary , XML , JSON files in same location (After you exit the program)\n");
                    break;
                case "6":
                    lunch = false;
                    break;
                default:
                    System.out.println("Choose from options 1, 2, 3, 4, 5, 6");
                    break;
            }
        }
    }

    public static void serialize(UserSerializerInterface serializer , List<User> list, String fileName)
    {
        try {
            serializer.serialize(list, fileName);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<User> deserialize(UserSerializerInterface serializer, String fileName)
    {
        try{
            return serializer.deserialize(fileName);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ArrayList();
    }
}
