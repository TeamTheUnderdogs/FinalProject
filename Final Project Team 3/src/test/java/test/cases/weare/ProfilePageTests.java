package test.cases.weare;

import org.junit.jupiter.api.Test;
import pages.weare.ProfilePage;

public class ProfilePageTests extends BaseTest {
    ProfilePage profilePage = new ProfilePage(actions.getDriver());

    @Test

    public void AdminSuccessfullyUpdateUserPersonalProfile_when_LogedIn() {
        profilePage.editUserPersonalProfile();
        actions.assertElementAttribute("profilePage.email.field", "id", "emailE");
    }
}
