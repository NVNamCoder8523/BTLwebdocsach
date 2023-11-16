package ptit.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ptit.edu.vn.entity.CustomUserDetails;
import ptit.edu.vn.entity.User;
import ptit.edu.vn.repository.UserRepository;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    User user;
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
}
