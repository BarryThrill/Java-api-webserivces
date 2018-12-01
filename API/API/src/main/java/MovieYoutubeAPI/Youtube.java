package MovieYoutubeAPI;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Youtube {

	FilmAttribut movie = new FilmAttribut();
	public void search(String trailer, FilmAttribut in) {

		HttpResponse<JsonNode> response;
		try {
			
			/**
			 * Få tillkomst att använda sig utav youtubes databas med länk, api och query.
			 */
			response = Unirest.get("https://www.googleapis.com/youtube/v3/search?key=AIzaSyAgu1BSuCKS_-J8AI_QwD3EX7oG_PSCGXQ&part=snippet")
					.queryString("q", trailer + " trailer")
					.asJson();

			JsonNode json = response.getBody();
			JSONObject envelope = json.getObject();
			JSONArray items = envelope.getJSONArray("items");
			
			/**
			 * Lägger till attributer ifrån klassen FilmAttribut.
			 */
			
			in.setTrailer(items.getJSONObject(0).getJSONObject("id").getString("videoId")); //Lägger till värdet som finns i FilmAttribute som trailer

		}
		catch (JSONException e) {
			e.printStackTrace(); 

		} catch (UnirestException e) {
			e.printStackTrace();
		}

	}
}

