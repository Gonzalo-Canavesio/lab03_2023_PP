package src.parser.feedParser;
import src.feed.*;
import src.httpRequest.RoughFeed;
import src.parser.GeneralParser;
import java.util.List;

public abstract class FeedParser<T> implements GeneralParser<Feed, RoughFeed> {

    public Feed parse(RoughFeed roughFeed){
        Feed feed = emptyFeed(roughFeed);
        List<T> roughArticles = getRoughArticles(roughFeed);
            for (T roughArticle : roughArticles) {
                Article articulo = parseArticle(roughArticle);
                feed.addArticle(articulo);
            }
        return feed;
    }

    protected abstract Feed emptyFeed(RoughFeed roughFeed);

    protected abstract List<T> getRoughArticles(RoughFeed roughFeed);

    protected abstract Article parseArticle(T roughArticle);


}
