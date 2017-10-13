package net.continuumsecurity.examples.ropeytasks;

import net.continuumsecurity.Config;
import net.continuumsecurity.Credentials;
import net.continuumsecurity.UserPassCredentials;
import net.continuumsecurity.behaviour.ILogin;
import net.continuumsecurity.behaviour.ILogout;
import net.continuumsecurity.behaviour.INavigable;
import net.continuumsecurity.web.WebApplication;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RopeyTasksApplication extends WebApplication implements ILogin,
        ILogout,INavigable {

    public RopeyTasksApplication() {
        super();

    }

    @Override
    public void openLoginPage() {
        driver.get(Config.getInstance().getBaseUrl());
        findAndWaitForElement(By.name("lastname"));
    }

    @Override
    public void login(Credentials credentials) {
        UserPassCredentials creds = new UserPassCredentials(credentials);
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(creds.getUsername());
        driver.findElement(By.name("action")).click();
    }

    // Convenience method
    public void login(String username, String password) {
        login(new UserPassCredentials(username, password));
    }

    @Override
    public boolean isLoggedIn() {
        driver.get(Config.getInstance().getBaseUrl()+"task/list");
        if (driver.getPageSource().contains("Tasks")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logout() {
        return; //driver.findElement(By.linkText("Logout")).click();
    }

    public void search(String query) {
        if (query != null) {
            driver.findElement(By.name("lastname")).clear();
            driver.findElement(By.name("lastname")).sendKeys(query);
            driver.findElement(By.name("action")).click();
        }
    }

    public void viewAllUsers() {
        driver.get(Config.getInstance().getBaseUrl() + "admin/list");
    }

    public void navigate() {
        openLoginPage();
        search(Config.getInstance().getDefaultUser().toString());
    }

}

