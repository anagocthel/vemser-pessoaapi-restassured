package utils;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Autenticacao {

    static String url = "http://vemser-dbc.dbccompany.com.br:39000/vemser/dbc-pessoa-api/auth";

    public static String tokenAdmin() {

        String tokenAdmin =
                given()
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                    "login" : "admin",
                                    "senha" : "123"
                                }
                                """)
                        .when()
                        .post(url)
                        .then()
                        .extract().asString()
                ;
        return tokenAdmin;
    }
}