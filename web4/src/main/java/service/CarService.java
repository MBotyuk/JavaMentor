package service;

import DAO.CarDao;
import DAO.DailyReportDao;
import model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {
    private long[] dailyReportArray = new long[2];

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
                dailyReportArray[0] = dailyReportArray[0] + carOfBrand.getPrice();
                dailyReportArray[1] = dailyReportArray[1] + 1;
                return carOfBrand;
            }
        }
        return null;
    }

    public long[] getDailyReportArray() {
        return dailyReportArray;
    }

    public void refreshDailyReportArray() {
        dailyReportArray[0] = 0;
        dailyReportArray[1] = 0;
    }

    public int count(String brand) {
        return new CarDao(sessionFactory.openSession()).count(brand);
    }

    public void refresh() {
        new CarDao(sessionFactory.openSession()).refresh();
    }
}
