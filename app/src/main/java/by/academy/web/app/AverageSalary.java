package by.academy.web.app;

import by.academy.web.logic.Accounting;
import by.academy.web.model.Person;
import by.academy.web.model.Teacher;
import by.academy.web.repository.PersonRepository;
import by.academy.web.repository.PersonRepositoryInMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(value = "/average-salary")
public class AverageSalary extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AverageSalary.class);
    private Accounting accounting;
    private PersonRepository personRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        accounting = new Accounting();
        personRepository = PersonRepositoryInMemory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> persons = personRepository.findAll();
        List<Teacher> teachers = filterTeachers(persons);
        log.info("List of teachers = {}", teachers);
        BigDecimal averageSalary = averageSalary(teachers);
        log.info("AverageSalary = {}", averageSalary);
        req.setAttribute("averageSalary", averageSalary);
        req.setAttribute("teachers", teachers);
        req.getRequestDispatcher("/average").forward(req, resp);
    }

    private List<Teacher> filterTeachers(List<Person> people) {
        return people.stream()
                .filter(person -> person instanceof Teacher)
                .map(teacher -> (Teacher)teacher)
                .collect(Collectors.toList());
    }

    private BigDecimal averageSalary(List<Teacher> teachers) {
        List<Integer> salaries = teachers.stream()
                .map(Teacher::getSalary)
                .collect(Collectors.toList());
        return accounting.average(salaries).setScale(2, RoundingMode.HALF_UP);
    }

}
