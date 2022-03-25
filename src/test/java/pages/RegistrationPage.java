package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private CalendarComponent calendarComponent = new CalendarComponent();

    private SelenideElement
            pageHeader = $(".main-header"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            birthDatePicker = $("#dateOfBirthInput"),
            mobileNumberInput = $("#userNumber"),
            subjectsDropdown = $("#subjectsInput"),
            chooseFileButton = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateDropdown = $("#state"),
            cityDropdown = $("#city"),
            submitButton = $("#submit"),
            filledFormModal = $(".modal-content");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        pageHeader.shouldHave(text("Practice Form"));

        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    public RegistrationPage setGender(String gender) {
        $(byText(gender)).click();

        return this;
    }

    public RegistrationPage setMobileNumber(String number) {
        mobileNumberInput.setValue(number);

        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        birthDatePicker.click();
        calendarComponent.setBirthDate(day, month, year);

        return this;
    }

    public RegistrationPage selectSubject(String subject) {
        subjectsDropdown.sendKeys("a");
        $(byText(subject)).click();

        return this;
    }

    public RegistrationPage selectHobbies(String hobbyOne, String hobbyTwo) {
        $(byText(hobbyOne)).click();
        $(byText(hobbyTwo)).click();

        return this;
    }

    public RegistrationPage uploadImage(String fileName) {
        chooseFileButton.uploadFromClasspath(fileName);

        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);

        return this;
    }

    public RegistrationPage selectState(String state) {
        stateDropdown.scrollTo().click();
        $(byText(state)).click();

        return this;
    }

    public RegistrationPage selectCity(String city) {
        cityDropdown.click();
        $(byText(city)).click();

        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.click();

        return this;
    }

    public RegistrationPage verifyForm(String label, String value) {
        filledFormModal.shouldBe(visible);
        filledFormModal.$(byText(label)).parent().shouldHave(text(value));

        return this;
    }
}