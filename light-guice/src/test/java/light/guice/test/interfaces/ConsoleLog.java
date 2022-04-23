package light.guice.test.interfaces;

import light.guice.scan.annotations.NamedBinder;

@NamedBinder(name = "console", bind = Log.class)
public class ConsoleLog implements Log{
    @Override
    public String print() {
        return "console log";
    }
}
