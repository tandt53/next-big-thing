package light.web;

/**
 * Provides a method for creating instance of page.
 */
public interface PageFactory {

    <TPage extends BaseWebPage<TPage>> TPage create(Class<? extends TPage> contract);
}
