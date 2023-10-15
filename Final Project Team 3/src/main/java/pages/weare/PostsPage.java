package pages.weare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PostsPage extends WEareBasePage {
    public PostsPage(WebDriver webDriver) {
        super(webDriver, "social.app.postsPage");
    }

    public void browsePublicPosts() {
       navigateToPage();
       assertPageNavigated();
    }

    public void browsePublicPostsByCategory () {
        browsePublicPosts();

        WebElement dropdownMenu= driver.findElement(By.xpath("//select[@id='name']"));

        Select dropdown = new Select(dropdownMenu);
        actions.waitForElementClickable("postsPage.chooseProfession.dropdown");
        dropdown.selectByVisibleText("Baker");

        actions.waitForElementClickable("postsPage.browsePostsByCategory.button");
        actions.clickElement("postsPage.browsePostsByCategory.button");

    }

    public void explorePublicPost (){
        browsePublicPosts();
        actions.waitForElementClickable("postsPage.exploreThisPost.button");
        actions.clickElement("postsPage.exploreThisPost.button");
    }

    public void likePublicPost() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");

        actions.waitForElementClickable("postsPage.browseAllPublicPosts.button");
        actions.clickElement("postsPage.browseAllPublicPosts.button");

        actions.waitForElementClickable("postsPage.likePost.button");
        actions.clickElement("postsPage.likePost.button");
    }


}
