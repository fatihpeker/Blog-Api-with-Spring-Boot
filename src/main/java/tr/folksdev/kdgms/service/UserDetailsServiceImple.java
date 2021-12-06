package tr.folksdev.kdgms.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.folksdev.kdgms.dto.MyUserDetails;
import tr.folksdev.kdgms.model.User;


@Service
public class UserDetailsServiceImple implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImple(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.getUserByUsername(username);

        return MyUserDetails.build(user);
    }
}
