package com.demoqa.page;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.page.components.CalendarComponent;
import com.demoqa.page.components.ResultsTableComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    private CalendarComponent calendarComponent = new CalendarComponent();
    private ResultsTableComponent resultsTableComponent = new ResultsTableComponent();
    private File file = new File("src/test/resources/testcat.png");
    // Element
    private SelenideElement
            firstNameInput = $("#firstName"),
            secondNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            phoneInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbyInput = $("#hobbiesWrapper"),
            pictureUpload = $x("//input[@id='uploadPicture']"),
            addressInput = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            submitButton = $("#submit");


    //Actions
    public RegistrationFormPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixeban').remove()");
        return this;
    }

//    public void setFirstName(String value) {
//        firstNameInput.setValue(value);
//    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage clearFirstName(String value) {
        firstNameInput.clear();
        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        secondNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setPhone(String value) {
        phoneInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationFormPage setSubject(String subjectOne, String subjectSecond) {
        subjectInput.setValue(subjectOne).pressEnter();
        subjectInput.setValue(subjectSecond).scrollTo().pressEnter();

        return this;
    }

    public RegistrationFormPage setHobby(String hobbyOne, String hobbySecond) {
        hobbyInput.$(byText(hobbyOne)).click();
        hobbyInput.$(byText(hobbySecond)).click();

        return this;
    }

    public RegistrationFormPage uploadPicture() {
        pictureUpload.uploadFile(file);

        return this;
    }

    public RegistrationFormPage setAddress(String address) {
        addressInput.scrollIntoView(true).setValue(address);

        return this;
    }

    public RegistrationFormPage setState(String state) {
        stateInput.setValue(state).pressEnter();

        return this;
    }

    public RegistrationFormPage setCity(String city) {
        cityInput.setValue(city).pressEnter();

        return this;
    }

    public RegistrationFormPage clickSubmit() {
        submitButton.scrollIntoView(true).click();

        return this;
    }

    public RegistrationFormPage checkResultsTableVisible() {
        resultsTableComponent.checkVisible();

        return this;
    }

    public RegistrationFormPage checkResult(String firstNameUser, String lastNameUser, String email, String gender,
                                            String phone, String dayOfbirth, String monthOfbirth, String yearOfBirth,
                                            String subjectOne, String subjectSecond, String  hobbyOne, String hobbySecond,
                                            String address, String state, String city, String file) {
        resultsTableComponent.checkResult(firstNameUser, lastNameUser, email, gender,
                phone, dayOfbirth, monthOfbirth, yearOfBirth, subjectOne,  subjectSecond, hobbyOne, hobbySecond,
                address, state, city, file);

        return this;
    }

}
