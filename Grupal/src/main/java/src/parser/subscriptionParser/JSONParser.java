package src.parser.subscriptionParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import src.subscription.*;

public class JSONParser extends SubscriptionParser<JSONObject> {

  @Override
  public List<JSONObject> getListObjects(FileReader reader) {
    JSONTokener tokener = new JSONTokener(reader);
    JSONArray jsonArray = new JSONArray(tokener);
    List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
    for (int i = 0; i < jsonArray.length(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      jsonObjects.add(jsonObject);
    }
    return jsonObjects;
  }

  @Override
  public SingleSubscription parseObject(JSONObject jsonObject) {

    String url = jsonObject.getString("url");
    String urlType = jsonObject.getString("urlType");
    // Transformo el JSONArray de los parámetros de la subscripción en un List<String>
    JSONArray urlParams = jsonObject.getJSONArray("urlParams");
    // Creo la suscripción single
    SingleSubscription singleSubscription = new SingleSubscription(url, null, urlType);
    // Agrego los parámetros de la suscripción single
    for (int j = 0; j < urlParams.length(); j++) {
      singleSubscription.setUrlParams(urlParams.getString(j));
    }
    return singleSubscription;
  }
}
