/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.lodz.uni.eksoc.cstreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Stream;
import models.User;

/**
 *
 * @author blaszczyk
 */
public class CStreams {

    public static void main(String[] args) {
        
        javaStream();
        
        streamMap();
        
        streamMapUser();
        
        streamFlatMap();
        
        streamFlatMapUser();
        
        streamFilter();
        
        streamReduce();
        
        streamInt();
    }
    
    private static void javaStream()
    {
        Stream stream = Stream.of(
                "A1", "A2", "A3", "A4"
        );
        
        List list = Arrays.asList(
                "A1", "A2", "A3", "A4");
        
        Stream listStream = list.stream();
        
        System.out.println("-----forEach------");
        
        listStream.forEach(value -> System.out.println(value));
        
        list.stream().forEach(System.out::println);      
    }
    
    private static void streamMap()
    {
        
        List<String> list = Arrays.asList(
                "A1", "A2", "A3", "A4");
        
        System.out.println("-----map------");
        
        list.stream()
                .map(value -> value.toLowerCase())
                .forEach(System.out::println);
          
    }
    
    private static void streamMapUser()
    {
        List<User> list = Arrays.asList(
                new User("Adam", "Kowalski", 46),
                new User("Anna", "Kowalska", 44),
                new User("Jan", "Nowak", 40),
                new User("Aleksandra", "Nowak", 36),
                new User("Katarzyna", "Nowak", 16));
        
        System.out.println("-----map lastname------");
        
        List<String> lastnames = 
                list.stream()
                .map(user -> user.lastname())
                .toList();

        lastnames.stream()
                .forEach(System.out::println);
        
        System.out.println("-----map status------");
        
        list.stream()
                .map(user -> getStatus(user))
                .forEach(System.out::println);
      }
    
    private static String getStatus(User user)
    {
        return (user.age() >= 18) ? "Osoba pełnoletnia" : "Osoba niepełnoletnia";
    }
    
    private static void streamFlatMap()
    {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7,8);
        List<Integer> list2 = Arrays.asList(11,12,13,14,15,16,17,18);
        List<Integer> list3 = Arrays.asList(21,22,23,24,25,26,27,28);
        
        List<List<Integer>> list = Arrays.asList(list1, list2, list3);
        
        System.out.println("-----flat map------");
        
        list.stream()
                .flatMap(l -> l.stream())
                .forEach(System.out::println);
        
        
    }
    
    private static void streamFlatMapUser()
    {
        List<User> adults = Arrays.asList(
                new User("Adam", "Kowalski", 46),
                new User("Anna", "Kowalska", 44),
                new User("Jan", "Nowak", 40),
                new User("Aleksandra", "Nowak", 36));
        
        List<User> childrens = Arrays.asList(
                new User("Kasia", "Kowalska", 16),
                new User("Mariola", "Kowalska", 14),
                new User("Janek", "Nowak", 10),
                new User("Zbyszek", "Nowak", 16));
        
        List<List<User>> users = Arrays.asList(adults, childrens);
        
        System.out.println("-----users flat map------");
        
        users.stream()
                .flatMap(user->user.stream())
                .map(user -> user.firstname())
                .forEach(System.out::println);
        
    }
    
    private static void streamFilter()
    {
        List<User> list = Arrays.asList(
                new User("Adam", "Kowalski", 46),
                new User("Anna", "Kowalska", 44),
                new User("Jan", "Nowak", 40),
                new User("Aleksandra", "Nowak", 36),
                new User("Katarzyna", "Nowak", 16));
        
        List<User> olderThen40 = 
                list.stream()
                .filter(user -> user.age() > 40)
                .toList();
        
        System.out.println("-----users older then 40:------");
        
        olderThen40.stream().forEach(user -> System.out.println(user.firstname()));
    }
    
    private static void streamReduce()
    {
            List<User> list = Arrays.asList(
                new User("Adam", "Kowalski", 46),
                new User("Anna", "Kowalska", 44),
                new User("Jan", "Nowak", 40),
                new User("Aleksandra", "Nowak", 96),
                new User("Katarzyna", "Nowak", 16));
            
            System.out.println("-----reduce------");
            
            list.stream()
                    .reduce((u1, u2) -> u1.age() > u2.age() ? u1 : u2)
                    .ifPresent(user -> System.out.println(user.firstname()));
            
            list.stream()
                    .max(new Comparator<User>(){
                    @Override
                    public int compare(User o1, User o2) {
                        return Integer.compare(o1.age(), o2.age());
                    }
                }).ifPresent(user -> System.out.println(user.firstname()));
            
            list.stream()
                    .max((o1, o2) -> Integer.compare(o1.age(), o2.age()))
                    .ifPresent(user -> System.out.println(user.firstname()));
    }
    
    private static void streamInt()
    {
        List<String> list = Arrays.asList("1","2","3","4","5","6","7","8");
        
       IntSummaryStatistics stats = list.stream()
                .mapToInt(Integer::parseInt)
                .summaryStatistics();
       
       System.out.println("-----IntSummaryStatistics------");
       
       System.out.println(String.format("Liczba: %d", stats.getCount()));
       System.out.println(String.format("Suma: %d", stats.getSum()));
       System.out.println(String.format("Średnia: %f", stats.getAverage()));
       System.out.println(String.format("Min: %d", stats.getMin()));
       System.out.println(String.format("Max: %d", stats.getMax()));
    }
}
