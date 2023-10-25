package pages.weare;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    public void anonymous_browsePublicPostsByCategory() {
        WebElement dropdownMenu = driver.findElement(By.xpath("//select[@id='name']"));
        Select dropdown = new Select(dropdownMenu);
        actions.waitForElementClickable("postsPage.chooseProfession.dropdown");
        dropdown.selectByVisibleText("Baker");
        actions.waitForElementClickable("postsPage.browsePublicPostsByCategoryAsAnonymous.button");
        actions.clickElement("postsPage.browsePublicPostsByCategoryAsAnonymous.button");
    }
    public void registered_browsePublicPostsByCategory() {
        WebElement dropdownMenu = driver.findElement(By.xpath("//select[@id='name']"));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
        Select dropdown = new Select(dropdownMenu);
        actions.waitForElementClickable("postsPage.chooseProfession.dropdown");
        dropdown.selectByVisibleText("Baker");
        actions.waitForElementClickable("postsPage.browsePostsByCategory.button");
        actions.clickElement("postsPage.browsePostsByCategory.button");
    }
    public void registered_browseAllPublicPosts() {
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        actions.waitForElementClickable("postsPage.browseAllPublicPosts.button");
        actions.clickElement("postsPage.browseAllPublicPosts.button");
    }
    public void registered_exploreAllPostsFromSameAuthor() {
        actions.waitForElementClickable("postPage.allPostsOfThisUser.button");
        actions.clickElement("postPage.allPostsOfThisUser.button");
    }
}
