package crudservice;

import hibernate.HibernateUtil;
import objectid.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.NoSuchElementException;

public class PlanetCrudService {
    private Session connection;
    private Transaction transaction;
    private List<Planet> resultList;
    private HibernateUtil util = HibernateUtil.getInstance();

    public void readAll(){
        try {
            connection = util.getSessionFactory().openSession();
            transaction = connection.beginTransaction();

            resultList = connection.createQuery("FROM Planet", Planet.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        for (Planet planet: resultList) {
            System.out.println(planet.getId() + " - " + planet.getName());
        }
    }

    public Planet getById(String id){
        connection = util.getSessionFactory().openSession();
        connection.beginTransaction();

        Query<Planet> query = connection.createQuery("FROM Planet WHERE id=:id", Planet.class);
        query.setParameter("id", id);
        resultList = query.list();

        transaction.commit();
        connection.close();

        if (resultList.isEmpty()) {
            throw new NoSuchElementException("Planet not found with ID: " + id);
        }

        Planet planet = resultList.get(0);
        System.out.println(planet.getId() + " - " + planet.getName());
        return planet;
    }

    public void create(String id, String name){
            Planet planet = Planet.builder().id(id).name(name).build();

            Session connection = util.getSessionFactory().openSession();
            Transaction transaction = null;

            try {
                transaction = connection.beginTransaction();
                connection.persist(planet);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
    }

    public void delete(String id){
        try {
            connection = util.getSessionFactory().openSession();
            transaction = connection.beginTransaction();
            Query<?> query = connection.createNativeQuery("DELETE FROM Planet WHERE id=:id", Planet.class);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
