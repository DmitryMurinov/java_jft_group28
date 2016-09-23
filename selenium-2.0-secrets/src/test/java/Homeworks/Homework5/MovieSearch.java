package Homeworks.Homework5;

import Workbench.WebElementWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Workbench.Predicates.fixed;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Dmitry Murinov on 23.09.2016.
 *
 * GITHUB LINK TO WHOLE PROJECT WITH THIS ITEM:
 *
 * https://github.com/DmitryMurinov/java_jft_group28/tree/master/selenium-2.0-secrets/src/test/java/Homeworks/Homework5
 */
public class MovieSearch {

    @Test
    public void MovieSearchPositive(){

        WebDriver wd = new FirefoxDriver();

        String URL = "http://barancev.w.pw/php4dvd/";
        String login = "admin";
        String pass = "admin";

        wd.get(URL);

        System.out.println("Wait until page loads");

        WebDriverWait wait = new WebDriverWait(wd, 60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='username']")));

        System.out.println("Login into search engine");

//        wd.findElement(By.xpath(".//*[@id='username']")).click();
        wd.findElement(By.xpath(".//*[@id='username']")).sendKeys(login);

//        wd.findElement(By.xpath(".//*[@id='loginform']/table/tbody/tr[2]/td[2]/input")).click();
        wd.findElement(By.xpath(".//*[@id='loginform']/table/tbody/tr[2]/td[2]/input")).sendKeys(pass);

        wd.findElement(By.xpath(".//*[@id='loginform']/table/tbody/tr[3]/td[2]/input")).click();

        System.out.println("Login ok");

        System.out.println("Wait page to load");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='results']")));

        System.out.println("Find current results");

        WebElement moviesRoot = wd.findElement(By.xpath(".//*[@id='results']"));

        List<WebElement> moviesOpenPage = moviesRoot.findElements(By.tagName("a"));

        List<String> movieNames = new ArrayList<String>();

        for(WebElement e: moviesOpenPage){
            String movieName = e.findElement(By.tagName("div")).
                    findElement(By.className("title")).
                    getText();

            System.out.println("movieName: " + movieName);

            movieNames.add(movieName);
        }

        Random randomGenerator = new Random();

        int index = randomGenerator.nextInt(movieNames.size());

        String searchName = movieNames.get(index);

        System.out.println("Search for: " + searchName);

        wd.findElement(By.xpath(".//*[@id='q']")).sendKeys(searchName + Keys.ENTER);

        System.out.println("Wait for the search to complete");

        new WebElementWait(moviesRoot, 60).until(fixed());

        System.out.println("Get new list");

        List<WebElement> moviesAfterSearch = moviesRoot.findElements(By.tagName("a"));

        for(WebElement e: moviesAfterSearch){
            String movieName = e.findElement(By.tagName("div")).
                    findElement(By.className("title")).
                    getText();

            assertThat(movieName, is(searchName));
        }

        wd.close();
    }
}
