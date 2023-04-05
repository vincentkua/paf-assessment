package ibf2022.paf.assessment.server.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.query.ReturnableType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 4, Task 8

@RestController
public class TasksController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping(value = "task", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addTask(@RequestBody MultiValueMap<String, String> form) throws ParseException {

        String username = form.getFirst("username");

        List<Task> tasks = new LinkedList<>();

        // Loop through all the form parameters and add tasks to the tasks list
        for (String key : form.keySet()) {
            if (key.startsWith("description-")) {
                int index = Integer.parseInt(key.split("-")[1]);
                String description = form.getFirst(key);
                String priority = form.getFirst("priority-" + index);
                String dueDate = form.getFirst("dueDate-" + index);

                Task task = new Task();
                task.setDescription(description);
                task.setPriority(Integer.parseInt(priority) );
                task.setDueDate(dateFormat.parse(dueDate));

                tasks.add(task);
            }
        }

        System.out.println(username);
        System.out.println(tasks);


        return null;
    }

}
