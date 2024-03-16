package org.example.scrapperapp.scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScrapperService {
    private final Logger logger = LoggerFactory.getLogger(ScrapperService.class);
    private final String url = "http://wmii.uwm.edu.pl/kadra";
    private WebDriver driver;

    public ScrapperService() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);
        driver.get(url);
    }

    public List<Map<String, String>> getEmployeesData() {
        logger.info("Scrapping data start.");

        WebElement employeesTable = driver.findElement(By.id("block-views-employees-block"));
        WebElement tableBody = employeesTable.findElement(By.tagName("tbody"));

        List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));

        logger.info("Scrapping data finished");

        return normalizeData(tableRows);
    }

    private List<Map<String, String>> normalizeData(List<WebElement> tableRows) {

        List<Map<String, String>> norm_data = new ArrayList<>();

        for (WebElement tableRow : tableRows) {
            Map<String, String> data = new HashMap<>();

            String degree = tableRow
                    .findElement(By.className("views-field-degree"))
                    .getText();

            String fullName = tableRow
                    .findElement(By.className("views-field-title"))
                    .getText();

            String email = tableRow
                    .findElement(By.className("views-field-field-email"))
                    .getText();

            String phone = tableRow
                    .findElement(By.className("views-field-field-phone"))
                    .getText();

            String webUrl = tableRow
                    .findElement(By.className("views-field-field-url"))
                    .getText();

            String unitAbbr = tableRow
                    .findElement(By.className("views-field-field-unit-abbr"))
                    .getText();

            String room = tableRow
                    .findElement(By.className("views-field-field-room"))
                    .getText();

            String consultation = tableRow
                    .findElement(By.className("views-field-field-consultation"))
                    .getText();

            data.put("degree", degree.isEmpty() ? "null" : degree);
            data.put("fullName", fullName.isEmpty() ? "null" : fullName);
            data.put("email", email.isEmpty() ? "null" : email);
            data.put("phone", phone.isEmpty() ? "null" : phone);
            data.put("webUrl", webUrl.isEmpty() ? "null" : webUrl);
            data.put("unitAbbr", unitAbbr.isEmpty() ? "null" : unitAbbr);
            data.put("room", room.isEmpty() ? "null" : room);
            data.put("consultation", consultation.isEmpty() ? "null" : consultation);

            norm_data.add(data);

        }
        return norm_data;
    }
}
