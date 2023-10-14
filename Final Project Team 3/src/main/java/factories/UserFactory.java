package factories;
import Models.User;
import com.github.javafaker.Faker;

public class UserFactory {


    public static final Faker faker = new Faker();

    public static User createUser() {
        User user = new User();

user.setUsername(faker.name().firstName());
user.setEmail(faker.internet().safeEmailAddress());
user.setPassword(faker.internet().password(8,20,true, true, true ));
user.setFirstName(faker.name().firstName());
user.setLastName(faker.name().lastName());
user.setBirthday(faker.date().birthday().toString());
user.setIntroduction((faker.lorem().paragraph(2)));
user.setSkill(faker.lorem().paragraph(1));

return user;

    }

    public static User createUserWithTwentyLettersUsername() {
        User user = new User();

        user.setUsername(faker.lorem().characters(20));
        user.setEmail(faker.internet().safeEmailAddress());
        user.setPassword(faker.internet().password(8,20,true, true, true ));


        return user;

    }



}
