package com.example.tpbdd.messages;

import com.example.tpbdd.log.LogsController;
import com.example.tpbdd.users.Users;
import com.example.tpbdd.users.UsersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    public UsersController usersController;
    @Autowired
    public LogsController logsController;
    @Autowired
    public MessagesRepository messagesRepository;
    @GetMapping("/conversation/{user1}/{user2}")
    public List<String> conversation(@PathVariable String user1, @PathVariable String user2) {
        if (this.usersController.getUserById(user1) != null && this.usersController.getUserById(user2) != null) {
            List<Messages> messages = this.messagesRepository.findAllByFromAndTo(user1, user2);
            messages.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
            return messages.stream().map(m ->  m.getMessage() + " : " + m.getDate()).toList();
        } else {
            return Collections.singletonList("User not found");
        }
    }
    @PostMapping("/setUserInMessageGenerated")
    public String setUserInMessageGenerated() {
        List<Messages> messages = this.messagesRepository.findAll();
        List<Users> users = this.usersController.usersRepository.findAll();
        for (Messages message : messages) {
            //random between 0 and 300
            int random = (int) (Math.random() * 300);
            message.setFrom(this.usersController.getUserById(users.get(random).getId()).getId());
            random = (int) (Math.random() * 300);
            message.setTo(this.usersController.getUserById(users.get(random).getId()).getId());
            this.messagesRepository.save(message);
        }
        return "Messages updated";
    }

    @PostMapping("/addTchat/{fromUser}/{toUser}")
    public String addTchat(@PathVariable String fromUser, @PathVariable String toUser, @RequestBody String message) {
        //On autorise un tchat avec soit même
        if (this.usersController.getUserById(fromUser) != null && this.usersController.getUserById(toUser) != null) {

            this.messagesRepository.save(new Messages(fromUser, toUser, message));

            this.logsController.addLog(fromUser, "Tchat with " + toUser);
            this.logsController.addLog(toUser, "Tchat with " + fromUser);
            return "Tchat added";
        } else {
            return "User not found";
        }
    }




    @PutMapping("/removeAllMessages")
    public String removeAllMessages() {
        this.messagesRepository.deleteAll();
        return "All messages removed";
    }

}
