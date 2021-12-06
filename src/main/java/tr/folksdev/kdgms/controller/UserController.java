package tr.folksdev.kdgms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.folksdev.kdgms.dto.UpdateUserRequest;
import tr.folksdev.kdgms.dto.UserDto;
import tr.folksdev.kdgms.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/1.0/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "update")
    public ResponseEntity<String>updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest){
        return userService.updateUser(updateUserRequest);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<String>deleteUser(){
        return userService.deleteUser();
    }

    @GetMapping(value = "get_all")
    public ResponseEntity<List<UserDto>>getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "get_user")
    public ResponseEntity<UserDto>getUser(@RequestParam(value = "username") String username){
        return userService.getUser(username);
    }

}
