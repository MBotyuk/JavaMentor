package DAO;

import model.Car;
import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public DailyReport getLastReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("SELECT MAX(id) WHERE cars").list();
        transaction.commit();
        session.close();
        return dailyReports.get(1);
    }

    public void addDailyReport(DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.save(dailyReport);
        transaction.commit();
        session.close();
    }

    public void refresh() {
        Transaction transaction = session.beginTransaction();
        session.clear();
        transaction.commit();
        session.close();
    }
}
