package service;

import DAO.CarDao;
import model.Car;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    public CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCar();
    }

    public List<Car> getAllCarOfBrand(String brand) {
        return new CarDao(sessionFactory.openSession()).getAllCarOfBrand(brand);
    }

    public boolean addCar(Car car) {
        if (count(car.getBrand()) <= 10) {
            new CarDao(sessionFactory.openSession()).addCar(car);
            return true;
        }
        return false;
    }

    public Car buyCar(Car car) {
        for (Car carOfBrand : new CarDao(sessionFactory.openSession()).getAllCarOfBrand(car.getBrand())) {
            if (carOfBrand.equals(car)) {
                new CarDao(sessionFactory.openSession()).delCar(carOfBrand);
                DailyReport.getInstance().setEarnings(carOfBrand.getPrice());
                DailyReport.getInstance().setSoldCars();
                return carOfBrand;
            }
        }
        return null;
    }

    public int count(String brand) {
        return new CarDao(sessionFactory.openSession()).count(brand);
    }

    public void delete() {
        new CarDao(sessionFactory.openSession()).delete();
    }
}
