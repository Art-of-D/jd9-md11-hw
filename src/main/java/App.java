import crudservice.ClientCrudService;
import crudservice.PlanetCrudService;
import crudservice.TicketCrudService;
import flyway.MainFlyway;
import objectid.Client;
import objectid.Planet;

public class App {
    public static void main(String[] args) {
        //To create db
        //new MainFlyway().getInst();

        //To check ClientCrudService
        /*new ClientCrudService().create("Mumba");
        new ClientCrudService().getById(11);
        new ClientCrudService().delete("Mumba");
        new ClientCrudService().readAll();*/

        //To check PlanetCrudService
        /*new PlanetCrudService().readAll();
        new PlanetCrudService().create("ROSSY", "The best rose");
        new PlanetCrudService().readAll();
        new PlanetCrudService().delete("ROSSY");
        new PlanetCrudService().readAll();*/


        //To check TicketCrudService
        /*new TicketCrudService().create(new ClientCrudService().getById(3),
                new PlanetCrudService().getById("URANUS"), new PlanetCrudService().getById("MARS"));
        new TicketCrudService().readAll();
        new TicketCrudService().delete(12);
        new TicketCrudService().readAll();*/

    }


}
