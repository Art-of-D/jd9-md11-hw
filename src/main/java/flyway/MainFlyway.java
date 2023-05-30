package flyway;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MainFlyway {
    private static final MainFlyway INSTANCE;

    static {
        INSTANCE = new MainFlyway();
    }

    public MainFlyway(){
        migration();
    }

    public MainFlyway getInst(){
        return INSTANCE;
    }

    private void migration(){
        Flyway flyway = Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource("jdbc:h2:file:./db",
                        String.valueOf(settings().get("account")),
                        String.valueOf(settings().get("password")))
                .table("flyway_schema_history")
                .locations("/migration")
                .load();
        flyway.migrate();
    }

    private Map settings(){
        String FILENAME = "src/main/resources/connection_settings.json";
        Map<String, String> map = new HashMap<>();
        try {
            String string = Files.readAllLines(Paths.get(FILENAME).toAbsolutePath()).stream().collect(Collectors.joining("\n"));
            TypeToken<?> ttoken = TypeToken.getParameterized(Map.class, String.class, String.class);
            map = new Gson().fromJson(string, ttoken.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
