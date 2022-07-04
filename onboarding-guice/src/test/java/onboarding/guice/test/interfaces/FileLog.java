package onboarding.guice.test.interfaces;

import onboarding.guice.scan.annotations.NamedBinder;

@NamedBinder(name = "file", bind = Log.class)
public class FileLog implements Log {
    @Override
    public String print() {
        return "file log";
    }
}
