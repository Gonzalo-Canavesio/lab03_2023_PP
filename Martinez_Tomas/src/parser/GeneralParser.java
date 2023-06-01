package parser;

import java.io.IOException;

public interface GeneralParser<T,R>{
    public T parse(R input) throws IOException;
}
