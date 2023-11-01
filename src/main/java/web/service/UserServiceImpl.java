package web.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user){
        userDao.add(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        userDao.remove(userDao.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id){
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        userDao.editUser(user);
    }
}
