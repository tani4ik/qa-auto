package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.text;

public class TestPractiseForm {

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
        String imageFile = "penguin.jpeg";
        String address = "Main ave, apt 1";
        String state = "Haryana";
        String city = "Karnal";

        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);

        //calendar
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $(byText(day)).click();

        $("#subjectsInput").sendKeys("a");
        $(byText(subject)).click();

        $(byText(hobby_1)).click();
        $(byText(hobby_2)).click();

        $("#uploadPicture").uploadFromClasspath(imageFile);
        $("#currentAddress").setValue(address);

        $("#state").scrollTo().click();
        $(byText(state)).click();

        $("#city").click();
        $(byText(city)).click();

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $("tr", 1).shouldHave(text("Student Name"), text(firstName + " " + lastName));
        $("tr", 2).shouldHave(text("Student Email"), text(email));
        $("tr", 3).shouldHave(text("Gender"), text(gender));
        $("tr", 4).shouldHave(text("Mobile"), text(phoneNumber));
        $("tr", 5).shouldHave(text("Date of Birth"), text(day + " " + month + "," + year));
        $("tr", 6).shouldHave(text("Subjects"), text(subject));
        $("tr", 7).shouldHave(text("Hobbies"), text(hobby_1 + ", " + hobby_2));
        $("tr", 8).shouldHave(text("Picture"), text(imageFile));
        $("tr", 9).shouldHave(text("Address"), text(address));
        $("tr", 10).shouldHave(text("State and City"), text(state + " " + city));
    }
}

