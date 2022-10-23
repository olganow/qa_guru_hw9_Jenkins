package com.demoqa.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.page.RegistrationFormPage;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;


public class RegistretionFormTests {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String firstNameUser = "Firstname",
            lastNameUser = "Secondname",
            email = "useremail@mail.ma",
            gender = "Female",
            phone = "1234567890",
            yearOfBirth = "1998",
            monthOfbirth = "May",
            dayOfbirth = "16",
            address = "Russia, Spb, 21",
            subjectOne = "Economics",
            subjectSecond = "Biology",
            hobbyOne = "Sports",
            hobbySecond = "Music",
            state = "Uttar Pradesh",
            city = "Lucknow",
            file = "testcat.png";


    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    @Test
    void successfulSubmitFormTest() {
        step("Fill form", () -> {
                    registrationFormPage
                            .openPage()
                            .setFirstName(firstNameUser)
                            .setLastName(lastNameUser)
                            .setEmail(email)
                            .setGender(gender)
                            .setPhone(phone)
                            .setDateOfBirth(dayOfbirth, monthOfbirth, yearOfBirth)
                            .setSubject(subjectOne, subjectSecond)
                            .setHobby(hobbyOne, hobbySecond)
                            .uploadPicture()
                            .setAddress(address)
                            .setState(state)
                            .setCity(city)
                            .clickSubmit();
                });

        step("Check Form", () -> {
                registrationFormPage
                .checkResultsTableVisible();
        registrationFormPage.checkResult(firstNameUser, lastNameUser, email, gender,
                phone, dayOfbirth, monthOfbirth, yearOfBirth, subjectOne, subjectSecond, hobbyOne, hobbySecond,
                address, state, city, file);
    });
    }

}