package MovieYoutubeAPI;
import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	public static void main(String[] args) {
		port(1337);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Youtube yt = new Youtube();
		MovieDataBase mdb = new MovieDataBase();

		/**
		 * Få informationen till localhost:1337/search/: (Endast film)
		 */

		get("/search/:movie", (req, res) ->  {
			res.header("Access-Control-Allow-Origin", "*");  //Så att vi kan använda oss utav informationen I HTML. 
			res.type("application/json");
			String titel = req.params(":movie"); 			 //Användaren skriver in en filmtitel som tilläggs som värdet titel.
			FilmAttribut film = mdb.searchMovie(titel);      //Letar efter en film som användaren har skrivit in. 
			yt.search(titel, film); 						 //Letar efter en trailer som användaren har skrivit in.

			return gson.toJson(film); 						 //Returnerar informationen till JSON format. 

		});

		/**
		 * Få informationen till localhost:1337/search/: (Endast serie)
		 */


		get("/search/tv/:tv", (req, res) ->  {
			res.header("Access-Control-Allow-Origin", "*");  //Så att vi kan använda oss utav informationen I HTML. 
			res.type("application/json");	
			String titel = req.params(":tv");				 //Användaren skriver in en TVtitel som tilläggs som värdet titel.
			FilmAttribut film = mdb.searchTV(titel); 		 //Letar efter en serie som användaren har skrivit in. 
			yt.search(titel, film); 						 //Letar efter en trailer som användaren har skrivit in.


			return gson.toJson(film);						 //Returnerar informationen till JSON format. 

		});




	}
}
