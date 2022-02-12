package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class TestPractiseForm {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1440x768";
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

        registrationPage.openPage();

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
    }
}

