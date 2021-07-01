package tandt.guice.test.interfaces;

import tandt.guice.scan.annotations.NamedBinder;

@NamedBinder(named = "console", bind = Log.class)
public class ConsoleLog implements Log{
    @Override
    public String print() {
        return "console log";
    }
}
