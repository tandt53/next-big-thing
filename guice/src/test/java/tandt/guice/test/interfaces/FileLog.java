package tandt.guice.test.interfaces;

import tandt.guice.scan.annotations.NamedBinder;

@NamedBinder(named = "file", bind = Log.class)
public class FileLog implements Log{
    @Override
    public String print() {
         return "file log";
    }
}
