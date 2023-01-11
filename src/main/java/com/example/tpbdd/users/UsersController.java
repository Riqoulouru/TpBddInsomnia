package com.example.tpbdd.users;

import com.example.tpbdd.log.LogsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    public LogsController logsController;
    @Autowired
    public UsersRepository usersRepository;

    @GetMapping(value = "/getUserById/{id}")
    public Users getUserById(@PathVariable String id) {
        return this.usersRepository.findById(id);
    }

    @GetMapping(value = "/getUserByUsername/{login}")
    public Users getUserByUsername(@PathVariable String login) {
        return this.usersRepository.findAllByUsername(login);
    }

    @GetMapping(value = "/getAllUser")
    public List<Users> getAllUser(@RequestParam(defaultValue = "0") Integer page) {
        return this.usersRepository.findAll(PageRequest.of(page, 30)).getContent();
    }

    @GetMapping(value = "/addAllFriends")
    public void addAllFriends() {
        List<Users> users = this.usersRepository.findAll();
        users.forEach(user -> {
            user.setFriends(new ArrayList<>());
            this.usersRepository.save(user);
        });

        for (Users user : users) {
            int nbFriends = (int) (Math.random() * 17) + 3;
            for (int i = user.getFriends().size(); i < nbFriends; i++) {
                int random = (int) (Math.random() * users.size());
                user.addFriend(users.get(random).getId());
                users.get(random).addFriend(user.getId());
                this.usersRepository.save(users.get(random));

            }
            this.usersRepository.save(user);
        }
    }

    @GetMapping(value = "/getFriendsByUsername/{username}")
    public List<String> getFriendsByUsername(@PathVariable String username) {
        List<String> friendList = new ArrayList<>();
        this.usersRepository.findAllByUsername(username).getFriends().forEach(friend ->
                friendList.add(this.usersRepository.findById(friend).getUsername()));
        return friendList;
    }

    @GetMapping(value = "/getUserByDescription")
    public List<String> getUserByDescription(@RequestBody String description) {
        List<String> userList = new ArrayList<>();
        this.usersRepository.findAllByDescriptionContaining(description).forEach(user ->
                userList.add(user.getUsername()));

        return userList;
    }

    @PutMapping(value = "/addFriend/{userUsername}/{friendUsername}")
    public String addFriend(@PathVariable String userUsername, @PathVariable String friendUsername) {
        if (userUsername.equals(friendUsername)) {
            return "Un utilisateur ne peut pas devenir ami avec lui-même.";
        }
        Users user = this.usersRepository.findAllByUsername(userUsername);
        Users friend = this.usersRepository.findAllByUsername(friendUsername);
        if (user.getFriends().contains(friend.getId())) {
            return "Cet utilisateur est déjà votre ami.";
        }
        user.addFriend(friend.getId());
        friend.addFriend(user.getId());
        this.usersRepository.save(user);
        this.usersRepository.save(friend);

        this.logsController.addLog(user, "Ajout de l'ami " + friendUsername);
        this.logsController.addLog(friend, "Ajout de l'ami " + userUsername);

        return "L'utilisateur " + friendUsername + " a été ajouté à votre liste d'amis.";
    }

    @PutMapping(value = "/removeFriend/{userUsername}/{friendUsername}")
    public String removeFriend(@PathVariable String userUsername, @PathVariable String friendUsername) {
        if (userUsername.equals(friendUsername)) {
            return "Un utilisateur ne peut pas devenir ami avec lui-même.";
        }
        Users user = this.usersRepository.findAllByUsername(userUsername);
        Users friend = this.usersRepository.findAllByUsername(friendUsername);
        if (!user.getFriends().contains(friend.getId())) {
            return "Cet utilisateur n'est pas votre ami.";
        }
        user.removeFriend(friend.getId());
        friend.removeFriend(user.getId());

        this.usersRepository.save(user);
        this.usersRepository.save(friend);

        this.logsController.addLog(user,"L'utilisateur " + userUsername + " a supprimé l'utilisateur " + friendUsername + " de sa liste d'amis.");
        this.logsController.addLog(friend,"L'utilisateur " + friendUsername + " a supprimé l'utilisateur " + userUsername + " de sa liste d'amis.");

        return "L'utilisateur " + friendUsername + " a été supprimé de votre liste d'amis.";
    }



}
