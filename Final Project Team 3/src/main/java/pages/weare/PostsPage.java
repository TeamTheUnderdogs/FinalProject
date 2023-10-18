package pages.weare;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static factories.UserFactory.faker;

public class PostsPage extends WEareBasePage {
    public PostsPage(WebDriver webDriver) {
        super(webDriver, "social.app.postsPage");
    }

    public void browsePublicPosts() {
        navigateToPage();
        assertPageNavigated();
    }

    public void browseAllPublicPosts_registered() {
        actions.waitForElementClickable("postsPage.browseAllPublicPosts.button");
        actions.clickElement("postsPage.browseAllPublicPosts.button");
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
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownMenu);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");

        Select dropdown = new Select(dropdownMenu);
        actions.waitForElementClickable("postsPage.chooseProfession.dropdown");
        dropdown.selectByVisibleText("Baker");

        actions.waitForElementClickable("postsPage.browsePostsByCategory.button");
        actions.clickElement("postsPage.browsePostsByCategory.button");

    }

    public void registered_explorePublicPost() {
        actions.waitForElementClickable("postsPage.exploreThisPost.button");
        actions.clickElement("postsPage.exploreThisPost.button");
    }

    public void registered_likePublicPost() {
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");

        actions.waitForElementClickable("postsPage.browseAllPublicPosts.button");
        actions.clickElement("postsPage.browseAllPublicPosts.button");

        actions.waitForElementClickable("postsPage.likePost.button");
        actions.clickElement("postsPage.likePost.button");
    }

    public void registered_dislikePublicPost() {
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");

        actions.waitForElementClickable("postsPage.browseAllPublicPosts.button");
        actions.clickElement("postsPage.browseAllPublicPosts.button");

        actions.waitForElementClickable("postsPage.dislikePost.button");
        actions.clickElement("postsPage.dislikePost.button");
    }

    public void readCommentsInPost() {
        actions.waitForElementClickable("postPage.showComments.button");
        actions.clickElement("postPage.showComments.button");
    }

    public void exploreAllPostsFromSameAuthor() {
        actions.waitForElementClickable("postPage.allPostsOfThisUser.button");
        actions.clickElement("postPage.allPostsOfThisUser.button");
    }

    public void exploreProfileOfThePostAuthor() {
        actions.waitForElementClickable("profilePage.seeProfile.button");
        actions.clickElement("profilePage.seeProfile.button");
    }

    public void likeComment() {
        actions.waitForElementClickable("postPage.likeComment.button");
        actions.clickElement("postPage.likeComment.button");
    }

    public void dislikeComment() {
        actions.waitForElementVisible("postPage.dislikeComment.button");
        actions.clickElement("postPage.dislikeComment.button");
    }

    public void createPostWithOnlyText() {
        actions.waitForElementVisible("homePage.addNewPost.button");
        actions.clickElement("homePage.addNewPost.button");

        actions.waitForElementClickable("postPage.dropDown.button");
        actions.selectFromDropdownMenu("postPage.dropDown.button", "Public post");

        actions.waitForElementVisible("postPage.postMessage.field");
        actions.typeValueInField(faker.lorem().characters(25), "postPage.postMessage.field");

        actions.waitForElementClickable("postPage.savePost.button");
        actions.clickElement("postPage.savePost.button");
    }

    public void createPostWithThousandCharacters() {
        actions.waitForElementVisible("homePage.addNewPost.button");
        actions.clickElement("homePage.addNewPost.button");

        actions.waitForElementClickable("postPage.dropDown.button");
        actions.selectFromDropdownMenu("postPage.dropDown.button", "Private post");

        actions.waitForElementVisible("postPage.postMessage.field");
        actions.typeValueInField(faker.lorem().characters(1000), "postPage.postMessage.field");

        actions.waitForElementClickable("postPage.savePost.button");
        actions.clickElement("postPage.savePost.button");
    }

    public void createCommentWithThousandCharacters() {
        actions.waitForElementVisible("postPage.commentMessage.field");
        actions.typeValueInField(faker.lorem().characters(1000), "postPage.commentMessage.field");

        actions.waitForElementClickable("postPage.postComment.button");
        actions.clickElement("postPage.postComment.button");
    }

    public void editOwnPost() {
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
    }

    public void deleteOwnPost() {
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(30,1250)");
        actions.waitForElementClickable("userPage.firstOwnPost.item");
        actions.clickElement("userPage.firstOwnPost.item");

        actions.waitForElementClickable("postPage.deletePost.button");
        actions.clickElement("postPage.deletePost.button");

        actions.waitForElementClickable("postPage.dropDown.button");

        WebElement dropdownMenu = driver.findElement(By.xpath("//select[@id='StringListId']"));
        Select dropdown = new Select(dropdownMenu);
        dropdown.selectByVisibleText("Delete post");

        WebElement searchBox = driver.findElement(By.xpath("//input[@value='Submit']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBox);
    }

    public void editOwnComment() {
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(30,1250)");
        actions.waitForElementClickable("userPage.firstOwnPost.item");
        actions.clickElement("userPage.firstOwnPost.item");

        WebElement searchBox = driver.findElement(By.xpath("//button[@class='show-comments']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBox);

        actions.waitForElementClickable("postPage.editComment.button");
        actions.clickElement("postPage.editComment.button");

        String fakeMessage = faker.lorem().characters(25);
        actions.typeValueInField(fakeMessage, "postPage.commentMessage.field");

        actions.waitForElementClickable("postPage.editComment.button");
        actions.clickElement("postPage.editComment.button");

        // After editing, retrieve the edited comment's text from the webpage
        WebElement commentParagraph = driver.findElement(By.xpath("//div[@class='comment-body']/p"));
        String commentText = commentParagraph.getText();

        // Compare the edited comment's text with the fake message
        if (commentText.equals(fakeMessage)) {
            System.out.println("The edited comment matches the entered text.");
        } else {
            System.out.println("The edited comment does not match the entered text.");
        }
    }

    public void deleteOwnComment() {
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(30,1250)");
        actions.waitForElementClickable("userPage.firstOwnPost.item");
        actions.clickElement("userPage.firstOwnPost.item");

        WebElement searchBox = driver.findElement(By.xpath("//button[@class='show-comments']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBox);

        actions.waitForElementClickable("postPage.deleteComment.button");
        actions.clickElement("postPage.deleteComment.button");

        WebElement dropdownMenu = driver.findElement(By.xpath("//select[@id='StringListId']"));
        Select dropdown = new Select(dropdownMenu);
        dropdown.selectByVisibleText("Delete comment");

        WebElement clickOnSubmitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickOnSubmitButton);
    }

    public void createPublicPostWithTextAndPicture() {
        actions.waitForElementVisible("homePage.addNewPost.button");
        actions.clickElement("homePage.addNewPost.button");

        actions.waitForElementClickable("postPage.dropDown.button");
        actions.selectFromDropdownMenu("postPage.dropDown.button", "Public post");

        actions.waitForElementVisible("postPage.postMessage.field");
        actions.typeValueInField(faker.lorem().characters(25), "postPage.postMessage.field");

        By fileInputSelector = By.xpath("//input[@id='imagefile']");
        WebElement fileInput = driver.findElement(fileInputSelector);
        String filePath = new File("src/main/java/resources/testUser.jpg").getAbsolutePath();
        fileInput.sendKeys(filePath);
        actions.waitForElementClickable("postPage.savePost.button");
        actions.clickElement("postPage.savePost.button");
    }
}
