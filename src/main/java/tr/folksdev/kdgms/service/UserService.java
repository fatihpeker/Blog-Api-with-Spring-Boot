package tr.folksdev.kdgms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.folksdev.kdgms.dto.UpdateUserRequest;
import tr.folksdev.kdgms.dto.UserDto;
import tr.folksdev.kdgms.model.User;
import tr.folksdev.kdgms.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public ResponseEntity<String>updateUser(UpdateUserRequest updateUserRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserByUsername(username);
        userRepository.save(new User(user.getId(),
                updateUserRequest.getUsername() == null ? user.getUsername() : updateUserRequest.getUsername(),
                updateUserRequest.getPassword() == null ? user.getPassword() : updateUserRequest.getPassword(),
                updateUserRequest.getName() == null ? user.getName() : updateUserRequest.getName(),
                updateUserRequest.getSurname() == null ? user.getSurname() : updateUserRequest.getSurname()));
        return ResponseEntity.ok("updated successfully");
    }

    public ResponseEntity<String>deleteUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserByUsername(username);
        userRepository.delete(user);
        return ResponseEntity.ok("user deleted");
    }

    public ResponseEntity<List<UserDto>>getAllUsers(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserByUsername(username);
        List<User> userList = userRepository.findAll();
        userList.remove(user);
        List<UserDto> userDtoList = new ArrayList<>();
        for (User users : userList ) {
            userDtoList.add(new UserDto(users.getId(),users.getUsername(),users.getName(),users.getSurname()));
        }
        return ResponseEntity.ok(userDtoList);
    }

    public ResponseEntity<UserDto>getUser(String username){
        User user = userRepository.findUserByUsername(username);
        return ResponseEntity.ok(new UserDto(user.getId(),user.getUsername(),user.getName(),user.getSurname()));
    }

}
