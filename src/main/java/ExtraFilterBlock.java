import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;

/**
 * Created by zsmirnova on 11/23/17.
 */
public class ExtraFilterBlock {

    @FindBy(xpath = ".//section[@class='details']")
    private ElementsCollection filterCategories;

    @FindBy(xpath = "//span[@class ='label']")
    private ElementsCollection extraFilterLabels;

    public ElementsCollection getFilterCategories() {
        return filterCategories;
    }

    public ElementsCollection getExtraFilterLabels() {
        return extraFilterLabels;
    }
}
