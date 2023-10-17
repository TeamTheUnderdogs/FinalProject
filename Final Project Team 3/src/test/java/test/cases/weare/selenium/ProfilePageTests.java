package test.cases.weare.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.weare.ProfilePage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;

public class ProfilePageTests extends BaseTest {
    ProfilePage profilePage = new ProfilePage(actions.getDriver());

    @Test

    public void adminSuccessfullyUpdateUserPersonalProfile_when_LogedIn() {
        profilePage.adminEditUserPersonalProfile();
        Assertions.assertEquals(getConfigPropertyByKey("soacial.app.editPersonalProfile.page"), getWebDriver().getCurrentUrl() ,
                "Page not successfully navigated");
    }

   @Test
   public void adminSuccessfullyUpdateUserProfessionalProfile_when_LoggedIn(){
        profilePage.adminEditUserProfessionalProfile();
        actions.assertElementPresent("profile.page.assertProfessionChanged.element");
    }
}
