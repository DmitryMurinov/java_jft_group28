package jft.murinov.addressbook.appmanager;

import jft.murinov.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

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

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public void modifyGroup(int index, GroupData group) {
        selectGroup(index);
        initGroupModify();
        fillGroupCreationForm(group);
        submitGroupModify();
        returnToGroupPage();
    }

    public boolean isThereAnyGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for(WebElement element: elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
