package ibf2022.paf.assessment.server.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

@RestController
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/find")
    public void findUser() {

        Optional<User> userOptional = userRepository.findUserByUsername("fred6");

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println(user);
        } else {
            System.out.println("user not found");
        }

    }

    @GetMapping("/insert")
    public void insertUser() {

        User user = new User();
        user.setName("Ali baba");
        user.setUsername("Ali ");

        String insertedID = userRepository.insertUser(user);

        if (insertedID != null) {
            System.out.println(insertedID);
        } else {
            System.out.println("Unable to insert");
        }

    }

    @GetMapping("/inserttask")
    public void insertTask() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Task task = new Task();
        task.setDescription("newtask 999");
        task.setPriority(2);
        task.setDueDate(dateFormat.parse("2022-12-13"));

        String userid = "1b80114c"; //sample user id for fred in sql

        taskRepository.insertTask(task, userid);
    }

}
