package httpRequest;

import subscription.SingleSubscription;
import subscription.Subscription;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class httpRequester implements Serializable {
	private Subscription subscription;

	public httpRequester(Subscription subscription){
		this.subscription = subscription;
	}

	public List<RoughFeed> getFeeds(){
		List<RoughFeed> roughFeeds = new ArrayList<RoughFeed>();
		for(SingleSubscription singleSubscription : this.subscription.getSubscriptionsList()){
			String urlType = singleSubscription.getUrlType();
			String feed = null;
			for(int i = 0; i < singleSubscription.getUrlParamsSize(); i++){
				feed = this.getFeed(singleSubscription.getFeedToRequest(i));
				if(feed != null){
					RoughFeed roughFeed = new RoughFeed(urlType, singleSubscription.getUrlParams(i), feed);
					roughFeeds.add(roughFeed);
				}
			}
		}
		return roughFeeds;
	}

	public String getFeed(String urlFeed){
		URL url;
		HttpURLConnection conn;
		// Hago el pedido de feed al servidor de noticias
		try{
			url = (new URI(urlFeed)).toURL();
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		} catch (Exception e){
			System.out.println("Error al hacer el pedido de feed al servidor de noticias");
			e.printStackTrace();
			return null;
		}
		// Chequeo que el pedido haya sido exitoso, y en ese caso devuelvo el contenido del feed
		try{
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				BufferedReader readd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLinee;
				StringBuilder response = new StringBuilder();
				while ((inputLinee = readd.readLine()) != null) {
					response.append(inputLinee);
				}
				readd.close();
				return response.toString();
			}else {
				throw new IOException("HTTP request failed with code " + conn.getResponseCode());
			}
		}catch (Exception e){
			System.out.println("Error al obtener el contenido del feed");
			e.printStackTrace();
			return null;
		}
	}

}
