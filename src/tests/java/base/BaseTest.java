package base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public abstract class BaseTest {

@BeforeAll
    public static void setUp(){
    RestAssured.baseURI = "http://vemser-dbc.dbccompany.com.br";
    RestAssured.port = 39000;
    RestAssured.basePath = "/vemser/dbc-pessoa-api";

    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

}

}
