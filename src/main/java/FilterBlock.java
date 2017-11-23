import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by zsmirnova on 11/23/17.
 */
public class FilterBlock {

    @FindBy(xpath = "//button[contains(@class, 'fl-group__btn-wrap')]")
    private ElementsCollection topFilters;

    @FindBy(xpath = "//h4[@class='summary']")
    private ElementsCollection extraFilterCategories;

    @FindBy(xpath = "//span[@class ='label']")
    private ElementsCollection extraFilterLabels;

    @Step("Set top filter {0}")
    public TrivagoResultPage setTopFilter(String nameFilter){
        topFilters.filterBy(text(nameFilter)).first().click();
        return page(TrivagoResultPage.class);
    }

    @Step("Set extra filer {1} in category {0}")
    public TrivagoResultPage setExtraFilter(String category, String filterName){
        extraFilterCategories.filter(text(category)).first().click();
        extraFilterLabels.filter(text(filterName)).first().waitUntil(visible, 1000).click();
        return page(TrivagoResultPage.class);
    }
}
