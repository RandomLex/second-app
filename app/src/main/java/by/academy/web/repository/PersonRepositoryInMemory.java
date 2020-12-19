package by.academy.web.repository;

import by.academy.web.model.Person;
import by.academy.web.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PersonRepositoryInMemory implements PersonRepository {
    private static volatile PersonRepositoryInMemory instance;

    private Map<Long, Person> map;

    private PersonRepositoryInMemory() {
        map = new ConcurrentHashMap<>();
        initModel().forEach(teacher -> map.put(teacher.getId(), teacher));
    }

    private List<Teacher> initModel() {
        return List.of(
                new Teacher(1, "Alex", 30, 2000),
                new Teacher(2, "John", 35, 1100),
                new Teacher(3, "Bob", 25, 1200)
        );
    }


    public static PersonRepositoryInMemory getInstance() {
        if (instance == null) {
            synchronized (PersonRepositoryInMemory.class) {
                if (instance == null) {
                    instance = new PersonRepositoryInMemory();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Person> findById(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Person save(Person person) {
        if (person.getId() == null) {
            person.setId(new Random().nextLong());
        }
        map.put(person.getId(), person);
        return person;
    }

    @Override
    public Person remove(Person person) {
        return map.remove(person.getId());
    }


}
