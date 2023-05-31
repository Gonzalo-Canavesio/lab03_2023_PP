package parser.feedParser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.StringReader;
import org.xml.sax.InputSource;
import httpRequest.RoughFeed;
import feed.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;


public class RssParser extends FeedParser<Element>{

    private Document getDocument(String feed) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(feed));
            Document doc = dBuilder.parse(inputSource);
            return doc;
        } catch (Exception e) {
            System.out.println("Error al parsear el feed rss");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Feed emptyFeed(RoughFeed roughFeed) {
        try {
            Document doc = getDocument(roughFeed.getFeed());
            return new Feed(doc.getElementsByTagName("title").item(0).getTextContent()+ " ("+roughFeed.getUrlParam() + " - " + roughFeed.getUrlType()+")");
        } catch (Exception e) {
            System.out.println("Error al parsear el feed rss");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected List<Element> getRoughArticles(RoughFeed roughFeed) {
        Document doc = getDocument(roughFeed.getFeed());
        NodeList articles = doc.getElementsByTagName("item");
        List<Element> elementsList = new ArrayList<>();
        for (int i = 0; i < articles.getLength(); i++) {
            Element element = (Element) articles.item(i);
            elementsList.add(element);
        }
        return elementsList;
    }

    @Override
    protected Article parseArticle(Element item) {
        try {
            String title = item.getElementsByTagName("title").item(0).getTextContent();
            String link = item.getElementsByTagName("link").item(0).getTextContent();
            String description = item.getElementsByTagName("description").item(0).getTextContent();
            String pubDate = item.getElementsByTagName("pubDate").item(0).getTextContent();
            SimpleDateFormat formato = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Date fecha = formato.parse(pubDate);
            Article articulo = new Article(title, description, fecha, link);
            return articulo;
        } catch (Exception e) {
            System.out.println("Error al parsear el feed rss");
            e.printStackTrace();
            return null;
        }    
    }
}
