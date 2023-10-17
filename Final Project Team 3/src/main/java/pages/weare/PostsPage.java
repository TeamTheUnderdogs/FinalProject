package pages.weare;

import Models.User;
import factories.UserFactory;
import groovy.util.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static factories.UserFactory.faker;

public class PostsPage extends WEareBasePage {
    public PostsPage(WebDriver webDriver) {
        super(webDriver, "social.app.postsPage");
    }

    public void browsePublicPosts() {
       navigateToPage();
       assertPageNavigated();
    }

    public void anonymous_browsePublicPostsByCategory() {
        browsePublicPosts();

        WebElement dropdownMenu= driver.findElement(By.xpath("//select[@id='name']"));

        Select dropdown = new Select(dropdownMenu);
        actions.waitForElementClickable("postsPage.chooseProfession.dropdown");
        dropdown.selectByVisibleText("Baker");

        actions.waitForElementClickable("postsPage.browsePublicPostsByCategoryAsAnonymous.button");
        actions.clickElement("postsPage.browsePublicPostsByCategoryAsAnonymous.button");

    }

    public void registered_browsePublicPostsByCategory () {
       LoginPage loginPage =new LoginPage(actions.getDriver());
       loginPage.loginUser();
       browsePublicPosts();

        WebElement dropdownMenu= driver.findElement(By.xpath("//select[@id='name']"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownMenu);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "" );


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

    public void dislikePublicPost() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");

        actions.waitForElementClickable("postsPage.browseAllPublicPosts.button");
        actions.clickElement("postsPage.browseAllPublicPosts.button");

        actions.waitForElementClickable("postsPage.dislikePost.button");
        actions.clickElement("postsPage.dislikePost.button");
    }

    public void readCommentsInPost() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        explorePublicPost();
        actions.waitForElementClickable("postPage.showComments.button");
        actions.clickElement("postPage.showComments.button");
    }
    public void exploreAllPostsFromSomeAuthor() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        explorePublicPost();
        actions.waitForElementClickable("postPage.allPostsOfThisUser.button");
        actions.clickElement("postPage.allPostsOfThisUser.button");
    }

    public void exploreProfileOfThePostAuthor() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        explorePublicPost();
        actions.waitForElementClickable("profilePage.seeProfile.button");
        actions.clickElement("profilePage.seeProfile.button");

    }

    public void likeComment() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        readCommentsInPost();
        actions.waitForElementClickable("postPage.likeComment");
        actions.clickElement("postPage.likeComment");
    }

    public void dislikeComment() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        readCommentsInPost();
        actions.waitForElementVisible("postPage.dislikeComment");
        actions.clickElement("postPage.dislikeComment");
    }

    public void createPostWithOnlyText() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        actions.waitForElementVisible("homePage.addNewPost.button");
        actions.clickElement("homePage.addNewPost.button");

        actions.waitForElementClickable("postPage.dropDown.button");
        actions.selectFromDropdownMenu("postPage.dropDown.button","Public post");

        actions.waitForElementVisible("postPage.postMessage.field");
        actions.typeValueInField(faker.lorem().characters(25), "postPage.postMessage.field");

        actions.waitForElementClickable("postPage.savePost.button");
        actions.clickElement("postPage.savePost.button");
    }

    public void createPostWithThousandCharacters() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        actions.waitForElementVisible("homePage.addNewPost.button");
        actions.clickElement("homePage.addNewPost.button");

        actions.waitForElementClickable("postPage.dropDown.button");
        actions.selectFromDropdownMenu("postPage.dropDown.button","Private post");

        actions.waitForElementVisible("postPage.postMessage.field");
        actions.typeValueInField(faker.lorem().characters(1000), "postPage.postMessage.field");

        actions.waitForElementClickable("postPage.savePost.button");
        actions.clickElement("postPage.savePost.button");
    }

    public void createCommentWithThousandCharacters() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        explorePublicPost();

        actions.waitForElementVisible("postPage.commentMessage.field");
        actions.typeValueInField(faker.lorem().characters(1000), "postPage.commentMessage.field");

        actions.waitForElementClickable("postPage.postComment.button");
        actions.clickElement("postPage.postComment.button");
    }

    public void editOwnPost() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(30,1250)");
        actions.waitForElementClickable("userPage.firstOwnPost.item");
        actions.clickElement("userPage.firstOwnPost.item");

        actions.waitForElementClickable("postPage.editPost.button");
        actions.clickElement("postPage.editPost.button");

        actions.waitForElementVisible("postPage.postMessage.field");
        actions.typeValueInField(faker.lorem().characters(25), "postPage.postMessage.field");

        actions.waitForElementClickable("postPage.savePost.button");
        actions.clickElement("postPage.savePost.button");
    }

    public void likeOwnPost() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(30,1250)");
        actions.waitForElementClickable("userPage.firstOwnPost.item");
        actions.clickElement("userPage.firstOwnPost.item");

        actions.waitForElementClickable("postPage.allPostsOfThisUser.button");
        actions.clickElement("postPage.allPostsOfThisUser.button");

        actions.waitForElementClickable("postsPage.likePost.button");
        actions.clickElement("postsPage.likePost.button");
    }

    public void dislikeOwnPost() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(30,1250)");
        actions.waitForElementClickable("userPage.firstOwnPost.item");
        actions.clickElement("userPage.firstOwnPost.item");

        actions.waitForElementClickable("postPage.allPostsOfThisUser.button");
        actions.clickElement("postPage.allPostsOfThisUser.button");

        actions.waitForElementClickable("postsPage.dislikePost.button");
        actions.clickElement("postsPage.dislikePost.button");
    }

    public void commentOwnPost() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser();

        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(30,1250)");
        actions.waitForElementClickable("userPage.firstOwnPost.item");
        actions.clickElement("userPage.firstOwnPost.item");

        actions.waitForElementVisible("postPage.commentMessage.field");
        actions.typeValueInField(faker.lorem().characters(25), "postPage.commentMessage.field");

        actions.waitForElementClickable("postPage.postComment.button");
        actions.clickElement("postPage.postComment.button");

        WebElement searchBox = driver.findElement(By.xpath("//button[@class='show-comments']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBox);

        //js.executeScript("window.scrollBy(0,-600", "");

        //actions.waitForElementClickable("postPage.showComments.button");
    }
}
