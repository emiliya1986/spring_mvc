package web.service;

import java.util.List;
import web.model.User;

public interface UserService {
    public List<User> findAll();
    public void saveUser(User user);
    public void deleteById(Long id);
    public User findById(Long id);
    void editUser(User user);
}
