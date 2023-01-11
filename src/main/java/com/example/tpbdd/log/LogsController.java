package com.example.tpbdd.log;

import com.example.tpbdd.users.Users;
import com.example.tpbdd.users.UsersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    LogsRepository logsRepository;

    @Autowired
    UsersController usersController;

    public String addLog(Users user, String action) {
        Logs log = new Logs(user.getId(), action);
        this.logsRepository.save(log);
        return "Log added";
    }

    public String addLog(String userId, String action) {
        if (this.usersController.getUserById(userId) != null) {
            Logs log = new Logs(userId, action);
            this.logsRepository.save(log);
            return "Log added";
        } else {
            return "User not found";
        }
    }

    @GetMapping(value = "/getAllLogs")
    public List<Logs> getAllLogs(@RequestParam(defaultValue = "0") Integer page) {
        return this.logsRepository.findAll(PageRequest.of(page, 30)).getContent();
    }

    @GetMapping(value = "/getLogByid/{userId}")
    public List<Logs> getLogByid(@PathVariable String userId) {
        return this.logsRepository.findAllByUserId(userId);
    }

    @PutMapping(value = "/removeAllLogs")
    public String removeAllLogs() {
        this.logsRepository.deleteAll();
        return "All logs removed";
    }

}

