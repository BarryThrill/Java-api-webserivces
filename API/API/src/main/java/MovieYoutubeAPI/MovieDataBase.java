package MovieYoutubeAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MovieDataBase {

	FilmAttribut movie = new FilmAttribut();
	public FilmAttribut searchMovie(String filmtitel) {

		HttpResponse<JsonNode> response;
		try {
			
			/**
			 * Få tillkomst att använda sig utav themoviedb databas med länk, api och query.
			 */
			
			response = Unirest.get("http://api.themoviedb.org/3/search/movie?api_key=8fdc08330620170a06de56dae1c5c692&query=Dope")
					.queryString("api_key", "8fdc08330620170a06de56dae1c5c692")
					.queryString("query", filmtitel)
					.asJson();

			JsonNode json = response.getBody();
			JSONObject envelope = json.getObject();
			JSONArray results = envelope.getJSONArray("results");
			
			/**
			 * Lägger till attributer ifrån klassen FilmAttribut.
			 */
			
			movie.setTitle(results.getJSONObject(0).getString("title"));             //Lägger till värdet som finns i FilmAttribute som title
			movie.setRelease(results.getJSONObject(0).getString("release_date"));	 //Lägger till värdet som finns i FilmAttribute som release
			movie.setVote(results.getJSONObject(0).getInt("vote_average"));			 //Lägger till värdet som finns i FilmAttribute som vote
			movie.setOverview(results.getJSONObject(0).getString("overview")) ;		 //Lägger till värdet som finns i FilmAttribute som overview
			movie.setPoster("http://image.tmdb.org/t/p/w500" + results.getJSONObject(0).getString("poster_path"));  		//Lägger till värdet som finns i FilmAttribute som poster

			return movie;
		}
		catch (JSONException e) {
			e.printStackTrace(); 

		} catch (UnirestException e) {

			e.printStackTrace();
		}
		return null;
	}

	public FilmAttribut searchTV(String TVtitel) {

		HttpResponse<JsonNode> response;
		try {
			
			/**
			 * Få tillkomst att använda sig utav themoviedb databas med länk, api och query.
			 */
			
			response = Unirest.get("http://api.themoviedb.org/3/search/tv")
					.queryString("api_key", "8fdc08330620170a06de56dae1c5c692")
					.queryString("query", TVtitel)
					.asJson();

			JsonNode json = response.getBody();
			JSONObject envelope = json.getObject();
			JSONArray results = envelope.getJSONArray("results");
			
			/**
			 * Lägger till attributer ifrån klassen FilmAttribut.
			 */
			
			movie.setTitle(results.getJSONObject(0).getString("name"));				//Lägger till värdet som finns i FilmAttribute som title
			movie.setRelease(results.getJSONObject(0).getString("first_air_date"));	//Lägger till värdet som finns i FilmAttribute som release
			movie.setVote(results.getJSONObject(0).getInt("vote_average"));			//Lägger till värdet som finns i FilmAttribute som vote
			movie.setOverview(results.getJSONObject(0).getString("overview")) ;		//Lägger till värdet som finns i FilmAttribute som overview	
			movie.setPoster("http://image.tmdb.org/t/p/w500" + results.getJSONObject(0).getString("poster_path"));	//Lägger till värdet som finns i FilmAttribute som poster

			return movie;

		}
		catch (JSONException e) {
		
			e.printStackTrace(); 

		} catch (UnirestException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}