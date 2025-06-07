package Tasks.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class JsonStorage {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static void saveUser(User user) {
        String filename = user.getUsername() + "_tasks.json";
        try(Writer writer = new FileWriter(filename)) {
            gson.toJson(user, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static User loadUser(String username) {
        String filename = username + "_tasks.json";
        try (Reader reader = Files.newBufferedReader(Paths.get(filename))) {
            return gson.fromJson(reader, User.class);
        } catch (IOException e) {

            return new User(username);
        }
    }
}
