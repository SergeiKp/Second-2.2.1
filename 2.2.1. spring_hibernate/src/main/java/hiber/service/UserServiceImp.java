package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)

    public User getUserByCar(String model, int series) {
        return userDao.getUserByCar(model, series);
    }

    @Transactional
    public void addUsersAndCars(List<User> users, List<Car> cars) {
        for (Car car : cars) {
            for (int i = 0; i < users.size(); i++) {
                users.get(i).setCar(cars.get(i));
                userDao.add(users.get(i));
            }
        }
    }
}


