package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        List<Car> cars = Arrays.asList(
                new Car("BMW", 3),
                new Car("Audi", 6),
                new Car("Mercedes-Benz", 300),
                new Car("Lada", 12)
        );
        List<User> users = Arrays.asList(
                new User("Sam", "Golden", "Golden@gmail.com"),
                new User("Jon", "Silver", "Silver@gmail.com"),
                new User("Bob", "Diamond", "Diamond@gmail.com")
        );

        userService.addUsersAndCars(users,cars);

        List<User> saveUsers = userService.listUsers();
        List<Car> saveCars = userService.listCars();

        for (User user : saveUsers) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
            System.out.println();
            System.out.println(userService.getUserByCar("BMW", 3));
        }
        System.out.println("List of cars :");
        for (Car car : saveCars) {
            System.out.println("Car Model = " + car.getModel() + ", Series = " + car.getSeries());
        }
        System.out.println(userService.getUserByCar("Audi",6));


        context.close();
    }
}
