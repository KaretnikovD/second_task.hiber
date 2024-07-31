package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users(" + "ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(100), " + "LASTNAME VARCHAR(100), AGE TINYINT, PRIMARY KEY (ID) )";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }


    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int rowsAffected = session.createQuery("DELETE FROM User WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Успешное удаление пользователя с ID: " + id);
            } else {
                System.out.println("Пользователь с ID: " + id + " не найден.");
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            return users;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}