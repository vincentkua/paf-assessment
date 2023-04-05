package ibf2022.paf.assessment.server.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.InsertException;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@RestController
public class TasksController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    TodoService todoService;

    @PostMapping(value = "task")
    public ModelAndView addTask(@RequestBody MultiValueMap<String, String> form) throws ParseException {
        // Task 4
        Integer taskcount = 0;
        String username = form.getFirst("username");
        List<Task> tasks = new LinkedList<>();
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
                taskcount = taskcount + 1;
            }
        }

        // Task 8
        try {
            Boolean insertCompleted = todoService.upsertTask(tasks, username);
            if (insertCompleted) {
                ModelAndView mav = new ModelAndView("result", HttpStatus.OK);
                mav.addObject("taskCount", taskcount);
                return mav;
            } else {
                ModelAndView mav = new ModelAndView("error", HttpStatus.INTERNAL_SERVER_ERROR);
                return mav;
            }
        } catch (InsertException ex) {
            ModelAndView mav = new ModelAndView("error", HttpStatus.INTERNAL_SERVER_ERROR);
            return mav;
        }

    }

}
