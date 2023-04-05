package ibf2022.paf.assessment.server.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.UserRepository;

@RestController
public class TestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/find")
    public void findUser(){

        Optional<User> userOptional = userRepository.findUserByUsername("fred6");

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println(user);
        } else {
            System.out.println("user not found");
        }

    }

    @GetMapping("/insert")
    public void insertUser(){

        User user = new User();
        user.setName("Ali baba");
        user.setUsername("Ali ");

        String insertedID = userRepository.insertUser(user);

        if(insertedID != null){
            System.out.println(insertedID);
        }else{
            System.out.println("Unable to insert");
        }
        

    }

    
}
