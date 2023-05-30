package crudservice;

import hibernate.HibernateUtil;
import objectid.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlanetCrudService {
    private Session connection;
    private Transaction transaction;
    private List<Planet> resultList;
    private HibernateUtil util = new HibernateUtil().getInstance();

    public void readAll(){
        try {
            connection = util.getSessionFactory().openSession();
            transaction = connection.beginTransaction();

            resultList = connection.createQuery("FROM Planet", Planet.class).list();

            connection.getTransaction().commit();
        } catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        } finally {
            connection.close();
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

        connection.getTransaction().commit();
        connection.close();

        for (Planet planet: resultList) {
            System.out.println(planet.getId() + " - " +planet.getName());
            return planet;
        }
        return null;
    }

    public void create(String id, String name){
        String lastIdentifier = null;
        try {
            connection = util.getSessionFactory().openSession();
            transaction = connection.beginTransaction();
            Query<String> query = connection.createQuery("SELECT id FROM Planet WHERE id = :id", String.class);
            query.setParameter("id", id);
            lastIdentifier = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            connection.close();
        }

        if (lastIdentifier == null) {
            Planet planet = Planet.builder().id(id).name(name).build();

            Session connection2 = util.getSessionFactory().openSession();
            Transaction transaction2 = null;

            try {
                transaction2 = connection2.beginTransaction();
                connection2.save(planet);
                transaction2.commit();
            } catch (Exception e) {
                if (transaction2 != null) {
                    transaction2.rollback();
                }
            } finally {
                connection2.close();
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
            connection.close();
        }
    }
}
