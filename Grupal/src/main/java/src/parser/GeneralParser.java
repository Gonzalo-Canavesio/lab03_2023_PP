package src.parser;

public interface GeneralParser<T,R>{ 
    public T parse(R input);
}
