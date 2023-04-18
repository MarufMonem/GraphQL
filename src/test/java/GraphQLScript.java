import io.restassured.path.json.JsonPath;
import netscape.javascript.JSObject;

import  static io.restassured.RestAssured.*;

public class GraphQLScript {
    public static void main(String[] args) {
        String locationName = "Ontario";
        String response = given().log().all().header("Content-Type", "application/json")
                .body("{\"query\":\"mutation($LocationName:String!, $characterName: String!, $episodeName:String!) {\\n  createLocation(location: {name: $LocationName, type: \\\"South East Asia\\\", dimension: \\\"147570\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name: $characterName, type: \\\"learner\\\", status: \\\"alive\\\", species: \\\"Human\\\", gender: \\\"male\\\", image:\\\"png\\\", originId:2577, locationId:2577}){\\n    id\\n  }\\n  createEpisode(episode: {name: $episodeName, air_date:\\\"1990\\\", episode:\\\"01\\\"}){\\n    id\\n  }\\n  \\n  \\n}\\n\",\"variables\":{\"LocationName\":\"" + locationName + "\",\"characterName\":\"Eleven\",\"episodeName\":\"Stranger Things\"}}")
                .when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();
        System.out.println(response);

        System.out.println("----------------------");

        JsonPath js = new JsonPath(response);
        System.out.println("location ID: " + js.getString("data.createLocation.id"));


    }

}
