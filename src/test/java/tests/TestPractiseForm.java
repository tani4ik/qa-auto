package tests;

import com.codeborne.selenide.Configuration;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;

public class TestPractiseForm {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    void successFillTest() {
        String firstName = "John";
        String lastName = "Galt";
        String email = "johngalt@gmail.com";
        String gender = "Male";
        String phoneNumber = "1231231230";
        String day = "20";
        String month = "January";
        String year = "1999";
        String subject = "Maths";
        String hobby_1 = "Sports";
        String hobby_2 = "Music";
        String fileName = "penguin.jpeg";
        String address = "Main ave, apt 1";
        String state = "Haryana";
        String city = "Karnal";

        step("Open registration page", () -> {
            registrationPage.openPage();
        });

        step("Fill in the form", () -> {
            registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobileNumber(phoneNumber)
                .setBirthDate(day, month, year)
                .selectSubject(subject)
                .selectHobbies(hobby_1, hobby_2)
                .uploadImage(fileName)
                .setCurrentAddress(address)
                .selectState(state)
                .selectCity(city)
                .submitForm();
        });

        step("Verify form", () -> {
            registrationPage.verifyForm("Student Name", firstName + " " + lastName)
                    .verifyForm("Student Email", email)
                    .verifyForm("Gender", gender)
                    .verifyForm("Mobile", phoneNumber)
                    .verifyForm("Date of Birth", day + " " + month + "," + year)
                    .verifyForm("Subjects", subject)
                    .verifyForm("Hobbies", hobby_1 + ", " + hobby_2)
                    .verifyForm("Picture", fileName)
                    .verifyForm("Address", address)
                    .verifyForm("State and City", state + " " + city);
        });
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}