package pages.weare;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static factories.UserFactory.faker;
public class PostPage extends WEareBasePage {
    public PostPage(WebDriver driver) {
        super(driver, "social.app.explorePostPage");
    }
    public void registered_explorePublicPost() {
        actions.waitForElementClickable("postsPage.exploreThisPost.button");
        actions.clickElement("postsPage.exploreThisPost.button");
    }
    public void exploreProfileOfThePostAuthor() {
        actions.waitForElementClickable("profilePage.seeProfile.button");
        actions.clickElement("profilePage.seeProfile.button");
    }
    public void registered_createPostWithOnlyText() {
        actions.waitForElementVisible("homePage.addNewPost.button");
        actions.clickElement("homePage.addNewPost.button");
        actions.waitForElementClickable("postPage.dropDown.button");
        actions.selectFromDropdownMenu("postPage.dropDown.button", "Public post");
        actions.waitForElementVisible("postPage.postMessage.field");
        actions.typeValueInField(faker.lorem().characters(25), "postPage.postMessage.field");
        actions.waitForElementClickable("postPage.savePost.button");
        actions.clickElement("postPage.savePost.button");
    }
    public void registered_createPublicPostWithTextAndPicture() {
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
    public void registered_createPostWithThousandCharacters() {
        actions.waitForElementVisible("homePage.addNewPost.button");
        actions.clickElement("homePage.addNewPost.button");
        actions.waitForElementClickable("postPage.dropDown.button");
        actions.selectFromDropdownMenu("postPage.dropDown.button", "Private post");
        actions.waitForElementVisible("postPage.postMessage.field");
        actions.typeValueInField(faker.lorem().characters(1000), "postPage.postMessage.field");
        actions.waitForElementClickable("postPage.savePost.button");
        actions.clickElement("postPage.savePost.button");
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
    public void registered_editOwnPost() {
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
    public void registered_likeOwnPost() {
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
    public void registered_dislikeOwnPost() {
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
    public void registered_commentOwnPost() {
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
        WebElement showCommentsButton = driver.findElement(By.xpath("//button[@class='show-comments']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", showCommentsButton);
    }
    public void registered_deleteOwnPost() {
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
    public void registered_readCommentsInPost() {
        WebElement showCommentsButton = driver.findElement(By.xpath("//button[@class='show-comments']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", showCommentsButton);
    }
    public void registered_likeComment() {
        WebElement likeComment = driver.findElement(By.xpath("//input[@value='Like']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", likeComment);
    }
    public void registered_dislikeComment() {
        actions.waitForElementVisible("postPage.dislikeComment.button");
        actions.clickElement("postPage.dislikeComment.button");
        actions.waitForElementClickable("postPage.likeComment.button");
    }
    public void registered_createCommentWithThousandCharacters() {
        actions.waitForElementVisible("postPage.commentMessage.field");
        actions.typeValueInField(faker.lorem().characters(1000), "postPage.commentMessage.field");
        actions.waitForElementClickable("postPage.postComment.button");
        actions.clickElement("postPage.postComment.button");
    }
    public void registered_editOwnComment() {
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(30,1250)");
        actions.waitForElementClickable("userPage.firstOwnPost.item");
        actions.clickElement("userPage.firstOwnPost.item");
        WebElement clickShowCommentsButton = driver.findElement(By.xpath("//button[@class='show-comments']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickShowCommentsButton);
        actions.waitForElementClickable("postPage.editComment.button");
        actions.clickElement("postPage.editComment.button");
        actions.waitForElementPresent("postPage.commentMessage.field");
        String fakeMessage = faker.lorem().characters(25);
        actions.typeValueInField(fakeMessage, "postPage.commentMessage.field");
        WebElement clickEditComment = driver.findElement(By.xpath("//input[@type='submit' and @value='Edit Comment' and @class='btn py-3 px-4 btn-primary']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickEditComment);
        WebElement commentParagraph = driver.findElement(By.xpath("//div[@class='comment-body']/p"));
        String commentText = commentParagraph.getText();
        if (commentText.equals(fakeMessage)) {
            System.out.println("The edited comment matches the entered text.");
        } else {
            System.out.println("The edited comment does not match the entered text.");
        }
    }
    public void registered_deleteOwnComment() {
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
        actions.waitForElementClickable("postPage.dropDown.button");
        WebElement dropdownMenu = driver.findElement(By.xpath("//select[@id='StringListId']"));
        Select dropdown = new Select(dropdownMenu);
        dropdown.selectByVisibleText("Delete comment");
        WebElement clickOnSubmitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickOnSubmitButton);
    }
    public void adminEditPublicPost() {
        actions.waitForElementClickable("postPage.editPost.button");
        actions.clickElement("postPage.editPost.button");
        actions.waitForElementVisible("postPage.postMessage.field");
        actions.typeValueInField("This post has been edite by an administrator", "postPage.postMessage.field");
        actions.waitForElementClickable("postPage.privacy.menu");
        actions.selectFromDropdownMenu("postPage.privacy.menu", "Public post");
        actions.waitForElementClickable("postPage.savePost.button");
        actions.clickElement("postPage.savePost.button");
    }
    public void adminDeletePublicPost() {
        actions.waitForElementClickable("postPage.deletePost.button");
        actions.clickElement("postPage.deletePost.button");
        actions.waitForElementClickable("postPage.delete.dropdown");
        actions.selectFromDropdownMenu("postPage.delete.dropdown", "Delete post");
        actions.waitForElementClickable("postPage.submit.button");
        actions.clickElement("postPage.submit.button");
    }
}
