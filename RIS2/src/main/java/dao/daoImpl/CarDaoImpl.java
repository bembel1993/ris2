package dao.daoImpl;

import dao.CarDao;
import entity.Car;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sessionFactory.SessionFactoryImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CarDaoImpl implements CarDao {

    public CarDaoImpl(){
    }
    @Override
    public boolean addCar(Car car) {
        boolean isAdded = false;
        try {
            //сессия создается для физ соединения с БД
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(car);
            tx.commit();
            session.close();
            isAdded = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean updateCar(Car car) {
        boolean isUpdated = false;
        try {
            // Тут нужно обновление
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(car);
            tx.commit();
            session.close();
            isUpdated = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCar(int id) {
        boolean isDeleted = false;
        try {
            // Тут нужно удаление
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Car car = session.load(Car.class, id);
            Transaction tx = session.beginTransaction();
            session.delete(car);
            tx.commit();
            session.close();
            isDeleted = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

   /* @Override
    public Car findCarInList(Company company, String name) {
        Car car = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> cr = cb.createQuery(Car.class);
            Root<Car> root = cr.from(Car.class);
            cr.select(root).where(cb.equal(root.get("carId"), company, name));
            car = session.createQuery(cr).getSingleResult();
            tx.commit();
            session.close();
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return car;
    }*/

    @Override
    public Car findCarByName(String name) {
        Car car = null;
        try {
            // Тут нужен поиск по имени
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> cr = cb.createQuery(Car.class);
            Root<Car> root = cr.from(Car.class);
            cr.select(root).where(cb.equal(root.get("companyName"), name));
            car = session.createQuery(cr).getSingleResult();
            tx.commit();
            session.close();
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return car;
    }

    @Override
    public List<Car> showCars () {
        List<Car> car =
                (List<Car>)SessionFactoryImpl.getSessionFactory().
                        openSession().createQuery("From Car").list();
        return car;
    }

}
