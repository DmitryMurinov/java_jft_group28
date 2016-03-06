package jft.murinov.addressbook.appmanager;

import jft.murinov.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Dima on 28.02.2016.
 */
public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreationForm() {
        click(By.name("submit"));
    }

    public void fillGroupCreationForm(GroupData groupData) {
        typeInfoBox(By.name("group_name"), groupData.getGroupName());
        typeInfoBox(By.name("group_header"), groupData.getGroupHeader());
        typeInfoBox(By.name("group_footer"), groupData.getGroupFooter());
    }

    public void clickGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initGroupModify() {
        click(By.name("edit"));


    }

    public void submitGroupModify() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        clickGroupCreation();
        fillGroupCreationForm(group);
        submitGroupCreationForm();
        returnToGroupPage();
    }

    public boolean isThereAnyGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
