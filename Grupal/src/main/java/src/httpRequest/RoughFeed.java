package src.httpRequest;

import java.io.Serializable;

public class RoughFeed implements Serializable {

  private String urlType;
  private String urlParam;
  private String feed;

  public RoughFeed(String urlType, String urlParam, String feed) {
    this.urlParam = urlParam;
    this.feed = feed;
    this.urlType = urlType;
  }

  public String getUrlType() {
    return urlType;
  }

  public String getUrlParam() {
    return urlParam;
  }

  public String getFeed() {
    return feed;
  }

  public void setUrlType(String urlType) {
    this.urlType = urlType;
  }

  public void setUrlParam(String urlParam) {
    this.urlParam = urlParam;
  }

  public void setFeed(String feed) {
    this.feed = feed;
  }

  @Override
  public String toString() {
    return "RoughFeeds{"
        + "urlType='"
        + urlType
        + '\''
        + ", urlParam='"
        + urlParam
        + '\''
        + ", feed='"
        + feed
        + '\''
        + '}';
  }

  public void prettyPrint() {
    System.out.println(this.toString());
  }
}
