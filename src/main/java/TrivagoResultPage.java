import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by zsmirnova on 11/22/17.
 */
public class TrivagoResultPage {

    @FindBy(xpath = "//h3[contains(@class, 'name__copytex')]")
    private ElementsCollection hotelsName;

    @FindBy(xpath = "//button[contains(@class,'horus__btn-detail--calendar')]")
    private ElementsCollection buttonCalendars;

    @FindBy(xpath = "//button[contains(@class,'cal-btn-next')]")
    private SelenideElement buttonNext;

    @FindBy(xpath = "//time[contains(@class,'cal-day')]")
    private ElementsCollection calendarDays;

    @FindBy(xpath = "//button[contains(@class,'overlay__close')]")
    private SelenideElement buttonCloseCalendar;

    @FindBy(xpath = "//th[contains(@class,'cal-heading-month')]")
    private SelenideElement month;

    @FindBy(xpath = "//button[contains(@class, 'tabs__label') and text()='Top Filters']")
    private SelenideElement topFilterTab;

    @FindBy(xpath = "//button[contains(@class, 'tabs__label') and text()='Extra Filters']")
    private SelenideElement extraFilterTab;

    @FindBy(xpath = "//button[contains(@class,'btn-horus--roomtype')]")
    private SelenideElement buttonRoomType;

    @FindBy(xpath = "//span[@class='df_component df_label']")
    private ElementsCollection listOfRoomTypes;

    @Step("Check hotel {0} is in result list")
    public TrivagoResultPage checkHotelDisplayed(String expectedName){
        hotelsName.filter(text(expectedName)).first().should(visible);
        return this;
    }

    @Step("Check hotel {0} is not in result list")
    public TrivagoResultPage checkHotelNotDisplayed(String expectedName){
        hotelsName.filter(text(expectedName)).first().shouldNot(visible);
        return this;
    }

    @Step("Set room type {0}")
    public TrivagoResultPage setRoomType(String type){
        buttonRoomType.waitUntil(visible, 1000).click();
        listOfRoomTypes.filter(text(type)).first().click();
        return this;
    }

    @Step("Open top filters tab")
    public FilterBlock openTopFilters(){
        topFilterTab.waitUntil(visible, 1000).click();
        return page(FilterBlock.class);
    }

    @Step("Open extra filters tab")
    public FilterBlock openExtraFilters(){
        extraFilterTab.waitUntil(visible, 1000).click();
        return page(FilterBlock.class);
    }

    @Step("Open first calendar")
    public TrivagoResultPage openFirstCalendar(){
        buttonCalendars.first().waitUntil(visible, 1000).click();
        return this;
    }

    @Step("Set one day {0} in month {1}")
    public TrivagoResultPage setOneDayAfterThreeMonth(String day, String expectedMonth){
        while(!month.getText().contains(expectedMonth)){
            buttonNext.waitUntil(visible, 1000).click();
        }
        calendarDays.filter(text(day)).first().waitUntil(visible, 1000).click();
        buttonCloseCalendar.waitUntil(visible, 1000).click();
        return this;
    }
}
