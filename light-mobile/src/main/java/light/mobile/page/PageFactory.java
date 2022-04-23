package light.mobile.page;

/**
 * Provides a method for creating instance of page.
 */
public interface PageFactory {

    <TPage extends BasePage> TPage create(Class<TPage> page);
}
