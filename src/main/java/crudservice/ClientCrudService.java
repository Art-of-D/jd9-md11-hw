package crudservice;

import hibernate.HibernateUtil;
import objectid.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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

        connection.getTransaction().commit();
        connection.close();

        for (Client client: resultList) {
            System.out.println("client - " + client.getId() + " - " + client.getName());
            return client;
        }
        return null;
    }

    public void create(String name){
        /*Long lastIdentifier = null;
        try {
            connection = util.getSessionFactory().openSession();
            transaction = connection.beginTransaction();
            Query<Long> query = connection.createQuery("SELECT MAX(id) FROM Client", Long.class);
            lastIdentifier = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }*/
        Client client = Client.builder().name(name).build();

        Session connection2 = util.getSessionFactory().openSession();
        Transaction transaction2 = null;

        try {
            transaction2 = connection2.beginTransaction();
            connection2.save(client);
            transaction2.commit();
        } catch (Exception e) {
            if (transaction2 != null) {
                transaction2.rollback();
            }
        } finally {
            if (connection2 != null) {
                connection2.close();
            }
        }

       /*if (lastIdentifier != null) {
            Long newIdentifier = (lastIdentifier != null) ? lastIdentifier + 1 : 1;
            Client client = Client.builder().id(newIdentifier).name(name).build();

            Session connection2 = util.getSessionFactory().openSession();
            Transaction transaction2 = null;

            try {
                transaction2 = connection2.beginTransaction();
                connection2.save(client);
                transaction2.commit();
            } catch (Exception e) {
                if (transaction2 != null) {
                    transaction2.rollback();
                }
            } finally {
                if (connection2 != null) {
                    connection2.close();
                }
            }
        }*/
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
