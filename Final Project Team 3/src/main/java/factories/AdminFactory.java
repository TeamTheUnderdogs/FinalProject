package factories;
import Models.Admin;
import com.github.javafaker.Faker;


public class AdminFactory {
    public static final Faker faker = new Faker();
    public static Admin createAdmin() {
        var admin = new Admin();

        admin.setUsername(faker.name().firstName());
        admin.setEmail(faker.internet().safeEmailAddress());
        admin.setPassword(faker.internet().password(8,20,true, true, true ));


        return admin;

    }
}
