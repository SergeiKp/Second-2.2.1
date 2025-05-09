package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User getUserByCar(String model, int series);

    public void addUsersAndCars(List<User> users, List<Car> cars);
}
