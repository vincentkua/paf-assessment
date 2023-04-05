package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6
@Repository
public class TaskRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERTTASKSQL = """
            insert task (description,priority,due_date,user_id) values (?, ? , ?, ?);
            """;

    public Boolean insertTask(Task task, String userid) {

        try {
            Integer rowsinserted = jdbcTemplate.update(INSERTTASKSQL, task.getDescription(), task.getPriority(),
                    task.getDueDate(), userid);

            if (rowsinserted == 1) {
                System.out.println("Task Inserted");
                return true;
            } else {
                System.out.println("Task Failed to insert");
                return false;
            }

        } catch (Exception e) {
            System.out.println("SQL Error... Task Failed to insert");
            return false;
        }

    }

}
