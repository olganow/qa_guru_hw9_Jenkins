package com.demoqa.test;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxForm {

    String firstNameUser = "Firstname",
            lastNameUser = "Secondname",
            email = "useremail@mail.ma",
            gender = "Female",
            phone = "1234567890",
            yearOfBirth = "1998",
            monthOfbirth = "May",
            dayOfbirth = "16";
    File file = new File("src/test/resources/testcat.png");




    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1600x1200";
        Configuration.holdBrowserOpen = true;
        System.out.println("Zoom In with 70%");

    }

    @Test
    void successfulSubmitFormTest() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("[id=firstName]").setValue(firstNameUser);
        $("[id=lastName]").setValue(lastNameUser);
        $("[id=userEmail]").setValue(email);
        $("[id=genterWrapper]").$(byText(gender)).click();
        $("[id=userNumber]").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfbirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfbirth).click();

        $("#subjectsInput").setValue("Economics").pressEnter();
        $("#subjectsInput").setValue("Biology").scrollTo().pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $x("//input[@id='uploadPicture']").uploadFile(file);
        $("[id=currentAddress]").scrollIntoView(true).setValue("Russia, Spb, 21");
        $("#react-select-3-input").setValue("Uttar Pradesh").pressEnter();
        $("#react-select-4-input").setValue("Lucknow").pressEnter();
        $("#submit").scrollIntoView(true).click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstNameUser + " " + lastNameUser));
        $x("//td[text()='Student Email']").parent().shouldHave(text(email));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(phone));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfbirth + " " + monthOfbirth + "," + yearOfBirth));
        $x("//td[text()='Subjects']").parent().shouldHave(text("Economics, Biology"));
        $x("//td[text()='Hobbies']").parent().shouldHave(text("Sports"));
        $x("//td[text()='Picture']").parent().shouldHave(text("testcat.png"));
        $x("//td[text()='Address']").parent().shouldHave(text("Russia, Spb, 21"));
        $x("//td[text()='State and City']").parent().shouldHave(text("Uttar Pradesh Lucknow"));
    }


}