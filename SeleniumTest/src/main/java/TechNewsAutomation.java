import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class TechNewsAutomation {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Browser (1=Chrome, 2=Edge): ");
        int choice = sc.nextInt();
        WebDriver driver = (choice == 2) ? new EdgeDriver() : new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        
        System.out.println("\n=== BROWSER: " + (choice == 2 ? "Edge" : "Chrome") + " ===");
        try {
            driver.get("http://localhost:8080/tech_news_article/index.jsp");
            log("-BASICS-", "Open URL: PASS | URL: " + driver.getCurrentUrl() + " | Title: " + driver.getTitle() + " | Src Len: " + driver.getPageSource().length());
            
            WebElement h2 = driver.findElement(By.tagName("h2"));
            log("-WEB ELEMENTS & LOCATORS-", "tagName & getText: PASS | Text: " + h2.getText());
            
            WebElement email = driver.findElement(By.name("email"));
            email.sendKeys("test");
            log("", "name & sendKeys: PASS | Typed 'test'");
            
            email.clear();
            email.sendKeys("sanjay@gmail.com");
            log("", "clear & getAttribute: PASS | Placeholder: " + email.getAttribute("placeholder"));
            
            driver.findElement(By.cssSelector("input[type='password']")).sendKeys("root");
            log("", "cssSelector: PASS | Typed password");
            
            WebElement btn = driver.findElement(By.className("btn"));
            log("", "className: PASS | Disp:" + btn.isDisplayed() + " En:" + btn.isEnabled() + " Sel:" + btn.isSelected());
            
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            log("", "xpath & click: PASS | Clicked Login");
            
            driver.navigate().to("http://localhost:8080/tech_news_article/add.jsp");
            log("-NAVIGATION & ID LOCATOR-", "navigate().to(): PASS");
            
            try { 
                driver.findElement(By.id("title")).sendKeys("Auto Title"); 
                log("", "id: PASS | Found and typed in title");
            } catch(Exception e) {}
            
            driver.navigate().back();
            log("", "navigate().back(): PASS");
            driver.navigate().forward();
            log("", "navigate().forward(): PASS");
            driver.navigate().refresh();
            log("", "navigate().refresh(): PASS");
            
            driver.navigate().to("http://localhost:8080/tech_news_article/index.jsp");
            
            WebElement link = driver.findElement(By.linkText("View public articles"));
            log("-LINK TEXT & FINDELEMENTS-", "linkText: PASS | Text: " + link.getText());
            link.click();
            log("", "click (on link): PASS");
            
            List<WebElement> links = driver.findElements(By.tagName("a"));
            System.out.print(" > findElements: PASS | Count: " + links.size() + " | Texts: ");
            for(WebElement l : links) System.out.print("[" + l.getText() + "] ");
            System.out.println();
            Thread.sleep(5000);
            
            ((JavascriptExecutor)driver).executeScript("alert('Dummy Alert');");
            log("-SWITCHTO-", "alert: PASS | Extracted: " + driver.switchTo().alert().getText());
            driver.switchTo().alert().accept();
            
        } finally {
            driver.quit();
            sc.close();
        }
    }
    
    private static void log(String header, String detail) throws Exception {
        if (!header.isEmpty()) System.out.println("\n" + header);
        System.out.println(" > " + detail);
        Thread.sleep(5000);
    }
}
