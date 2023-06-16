package src.parser.feedParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import src.feed.*;
import src.httpRequest.RoughFeed;

public class RedditParser extends FeedParser<JSONObject> {

  @Override
  protected Feed emptyFeed(RoughFeed roughFeed) {
    return new Feed(roughFeed.getUrlParam() + " (" + roughFeed.getUrlType() + ")");
  }

  @Override
  protected List<JSONObject> getRoughArticles(RoughFeed roughFeed) {
    JSONObject feed = new JSONObject(roughFeed.getFeed());
    JSONArray items = feed.getJSONObject("data").getJSONArray("children");
    List<JSONObject> jsonObjectsList = new ArrayList<>();
    for (int i = 0; i < items.length(); i++) {
      JSONObject jsonObject = items.getJSONObject(i);
      jsonObjectsList.add(jsonObject);
    }
    return jsonObjectsList;
  }

  @Override
  protected Article parseArticle(JSONObject item) {
    Article articulo = null;
    try {
      String title = item.getJSONObject("data").getString("title");
      String link = item.getJSONObject("data").getString("url");
      String description = item.getJSONObject("data").getString("selftext");
      Date fecha = new Date(item.getJSONObject("data").getLong("created_utc"));
      articulo = new Article(title, description, fecha, link, "reddit");
    } catch (Exception e) {
      System.out.println("Error al parsear el feed reddit");
      e.printStackTrace();
      return null;
    }
    return articulo;
  }
}
