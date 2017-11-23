import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

/**
 * Created by zsmirnova on 11/22/17.
 */
public class TrivagoPage {

    @FindBy(xpath = "//input[@type='search']")
    private SelenideElement inputSearh;

    public TrivagoResultPage search(String text){
        inputSearh.val(text).pressEnter();
        return page(TrivagoResultPage.class);
    }
}
