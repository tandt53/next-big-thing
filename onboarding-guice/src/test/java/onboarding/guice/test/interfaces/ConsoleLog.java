package onboarding.guice.test.interfaces;

import onboarding.guice.scan.annotations.NamedBinder;

@NamedBinder(name = "console", bind = Log.class)
public class ConsoleLog implements Log{
    @Override
    public String print() {
        return "console log";
    }
}
