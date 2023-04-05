package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Transactional(rollbackFor = InsertException.class)
    public Boolean upsertTask(List<Task> tasks, String username) throws InsertException {

        String userid;
        Boolean insertsuccess = false;

        // find user and get userid , create user and get new userid if not existing
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if (userOptional.isPresent()) {
            System.out.println("User found... Proceed to Add Task");
            User user = userOptional.get();
            userid = user.getUserId();
        } else {
            System.out.println("User not found... Creating New User");
            User user = new User();
            user.setName(username);
            user.setUsername(username); // be aware that this shall be without space and special characters
            userid = userRepository.insertUser(user);
            
            if (userid == null){
                throw new InsertException("Unable to create New User :" + user);
            }
        }

        for (Task task : tasks) {
            insertsuccess = taskRepository.insertTask(task, userid);
            if (!insertsuccess){
                throw new InsertException("failed to insert" + task);
            }
        }

        return insertsuccess;

    }

}
