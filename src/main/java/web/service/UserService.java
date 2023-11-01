package web.service;

import java.util.List;
import web.model.User;

public interface UserService {
    List<User> findAll();
    void saveUser(User user);
    void deleteById(Long id);
    User findById(Long id);
    void editUser(User user);
}
