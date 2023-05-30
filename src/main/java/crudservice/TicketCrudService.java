package crudservice;

import hibernate.HibernateUtil;
import objectid.Client;
import objectid.Planet;
import objectid.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.ZonedDateTime;
import java.util.List;

public class TicketCrudService {
    private Session connection;
    private Transaction transaction;
    private List<Ticket> resultList;
    private HibernateUtil util = new HibernateUtil().getInstance();

    public void readAll(){
        try {
            connection = util.getSessionFactory().openSession();
            transaction = connection.beginTransaction();

            resultList = connection.createQuery("FROM Ticket", Ticket.class).list();

            connection.getTransaction().commit();
        } catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        } finally {
            connection.close();
        }

        for (Ticket ticket: resultList) {
            System.out.println("id -" + ticket.getId() + ", created at -" + ticket.getCreated_at() + ", client id - " +
                    ticket.getClient().getId() + ", from planet - " + ticket.getPlanet().getId()+ ", to planet - "
                    + ticket.getPlanet2().getId());
        }
    }

    public void getById(long number){
        connection = util.getSessionFactory().openSession();
        connection.beginTransaction();

        Query<Ticket> query = connection.createQuery("FROM Ticket WHERE id=:id", Ticket.class);
        query.setParameter("id", number);
        resultList = query.list();

        connection.getTransaction().commit();
        connection.close();

        for (Ticket ticket: resultList) {
            System.out.println("id -" + ticket.getId() + ", created at -" + ticket.getCreated_at() + ", client id - " +
                    ticket.getClient().getId() + ", from planet - " + ticket.getPlanet().getId()+ ", to planet - "
                    + ticket.getPlanet2().getId());
        }
    }

    public void getByClientId(int number){
        connection = util.getSessionFactory().openSession();
        connection.beginTransaction();

        Query<Ticket> query = connection.createQuery("FROM Ticket " +
                                                                "JOIN client " +
                                                                "JOIN planet " +
                                                                "JOIN planet2 " +
                                                                "WHERE client.id=:number", Ticket.class);
        query.setParameter("number", number);
        resultList = query.list();

        connection.getTransaction().commit();
        connection.close();

        for (Ticket ticket: resultList) {
            System.out.println("id -" + ticket.getId() + ", created at -" + ticket.getCreated_at() + ", client id - " +
                    ticket.getClient().getId() + ", from planet - " + ticket.getPlanet().getId()+ ", to planet - "
                    + ticket.getPlanet2().getId());
        }
    }

    public void create(Client client, Planet from_planet, Planet to_planet){
            Ticket ticket = Ticket.builder().created_at(ZonedDateTime.now())
                    .client(client)
                    .planet(from_planet)
                    .planet2(to_planet)
                    .build();

            Session connection2 = util.getSessionFactory().openSession();
            Transaction transaction2 = null;

            try {
                transaction2 = connection2.beginTransaction();
                connection2.persist(ticket);
                transaction2.commit();
            } catch (Exception e) {
                if (transaction2 != null) {
                    transaction2.rollback();
                }
            } finally {
                connection2.close();
            }
    }

    public void delete(long number){
        try {
            connection = util.getSessionFactory().openSession();
            transaction = connection.beginTransaction();
            Query<?> query = connection.createNativeQuery("DELETE FROM Ticket WHERE id=:number", Ticket.class);
            query.setParameter("number", number);
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
