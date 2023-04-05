package ibf2022.paf.assessment.server.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.query.ReturnableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.InsertException;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@RestController
public class TasksController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    TodoService todoService;

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
                task.setPriority(Integer.parseInt(priority));
                task.setDueDate(dateFormat.parse(dueDate));

                tasks.add(task);
            }
        }

        // Task 8
        try {
            Boolean insertCompleted = todoService.upsertTask(tasks, username);
            if (insertCompleted) {
                return ResponseEntity.status(200).body("Data Inserted");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data Insert Failed");
            }
        } catch (InsertException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }

}
