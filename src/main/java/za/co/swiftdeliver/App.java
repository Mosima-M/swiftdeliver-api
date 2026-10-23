package za.co.swiftdeliver;

import io.javalin.Javalin;
import za.co.swiftdeliver.exception.HubNotFoundException;
import za.co.swiftdeliver.repository.HubRepository;
import za.co.swiftdeliver.repository.InMemoryHubRepository;
import za.co.swiftdeliver.service.HubService;

public class App {

    public static void main(String[] args) {

        HubRepository repository = new InMemoryHubRepository();
        HubService service = new HubService(repository);

        Javalin app = Javalin.create();
        app.get("/hubs", ctx -> {
            ctx.json(service.getAllHubs());
        });

        app.get("/hubs/{hubId}", ctx -> {
            ctx.json(service.getHubById(ctx.pathParam("hubId")));
        });

        app.exception(HubNotFoundException.class, (exception, ctx) -> {
            ctx.status(404);
            ctx.result(exception.getMessage());
        });


        app.start(7070);

    }
}
