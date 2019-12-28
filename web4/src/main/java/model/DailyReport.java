package model;

import DAO.DailyReportDao;
import org.hibernate.Session;
import service.CarService;
import service.DailyReportService;
import util.DBHelper;

import javax.persistence.*;

@Entity
@Table(name = "daily_reports")
public class DailyReport {

//    private static DailyReport dailyReport;
//
//    public static DailyReport getInstance() {
//        if (dailyReport == null) {
//            dailyReport = new DailyReport(0L, 0L);
//            DailyReportService.getInstance().addDailyReport(dailyReport);
//        }
//        return dailyReport;
//    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "earnings")
    private Long earnings;

    @Column(name = "soldCars")
    private Long soldCars;

    public DailyReport() {

    }

    public DailyReport(Long earnings, Long soldCars) {
        this.earnings = earnings;
        this.soldCars = soldCars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }

    public Long getSoldCars() {
        return soldCars;
    }

    public void setSoldCars(Long soldCars) {
        this.soldCars = soldCars;
    }
}
