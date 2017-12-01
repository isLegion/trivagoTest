import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

/**
 * Created by zsmirnova on 11/22/17.
 */
public class TrivagoTest {

    public TrivagoPage trivagoPage = new TrivagoPage();
    public TrivagoResultPage trivagoResultPage = new TrivagoResultPage();

    @Before
    public void setUp(){
        DateFormat simpleDayFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        String day = simpleDayFormat.format(date);
        System.setProperty("selenide.browser", "Chrome");
        trivagoPage = open("https://www.trivago.com/?", TrivagoPage.class);
        trivagoResultPage =
                trivagoPage.search("Cork")
                        .setOneDayAfterThreeMonth(day, "February")
                        .setRoomType("Double room");
    }

    @Test
    @Title("Check hotel is displayed with free wi-fi filter")
    public void displayHotelWithWiFiTest(){
        trivagoResultPage
                .openTopFilters()
                .setTopFilter("Free WiFi")
                .checkHotelDisplayed("Cork International Hotel");
    }

    @Test
    @Title("Check hotel is not displayed with free wi-fi filter")
    public void notDisplayHotelWithWiFiTest(){
        trivagoResultPage
                .openTopFilters()
                .setTopFilter("Free WiFi")
                .checkHotelNotDisplayed("Jurys Inn Cork");
    }

    @Test
    @Title("Check hotel is displayed with Spa filter")
    public void displayHotelWithSpaTest(){
        trivagoResultPage
                .openExtraFilters()
                .setExtraFilter("Hotel facilities", "Wellness Center / Spa")
                .checkHotelDisplayed("The River Lee");
    }

    @Test
    @Title("Check hotel is not displayed with Spa filter")
    public void notDisplayHotelWithSpaTest(){
        trivagoResultPage
                .openExtraFilters()
                .setExtraFilter("Hotel facilities", "Wellness Center / Spa")
                .checkHotelNotDisplayed("Jurys Inn Cork");
    }

    @After
    public void tearDown() throws IOException {
        screenshot();
        clearBrowserCache();
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return Files.toByteArray(screenshot);
    }

}
