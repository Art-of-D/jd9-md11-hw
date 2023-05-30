package crudservice;

import hibernate.HibernateUtil;
import objectid.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.NoSuchElementException;

public class ClientCrudService {
    private Session connection;
    private Transaction transaction;
    private List<Client> resultList;
    private HibernateUtil util = HibernateUtil.getInstance();

    public void readAll(){
        try {
            connection = util.getSessionFactory().openSession();
            transaction = connection.beginTransaction();

            resultList = connection.createQuery("FROM Client", Client.class).list();

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

        for (Client client: resultList) {
            System.out.println("client - " + client.getId() + " - " + client.getName());
        }
    }

    public Client getById(int number){
        connection = util.getSessionFactory().openSession();
        connection.beginTransaction();

        Query<Client> query = connection.createQuery("FROM Client WHERE id=:id", Client.class);
        query.setParameter("id", number);
        resultList = query.list();

        transaction.commit();
        connection.close();

        if (resultList.isEmpty()) {
            throw new NoSuchElementException("Client not found with ID: " + number);
        }

        Client client = resultList.get(0);
        System.out.println("client - " + client.getId() + " - " + client.getName());
        return client;
    }

    public void create(String name){
        Client client = Client.builder().name(name).build();

        Session connection = util.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = connection.beginTransaction();
            connection.persist(client);
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

    public void delete(String name, int number){
        try {
            connection = util.getSessionFactory().openSession();
            transaction = connection.beginTransaction();
            Query<?> query = connection.createNativeQuery("DELETE FROM Client WHERE name=:name AND id=:id", Client.class);
            query.setParameter("name", name).setParameter( "id", number);
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
