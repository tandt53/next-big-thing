package onboarding.web;

import onboarding.web.page.BasePage;

/**
 * Provides a method for creating instance of page.
 */
public interface PageFactory {

    <TPage extends BasePage<TPage>> TPage create(Class<? extends TPage> contract);
}
