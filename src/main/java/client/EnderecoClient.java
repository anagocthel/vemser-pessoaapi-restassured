package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.EnderecoModel;
import utils.Autenticacao;

import static io.restassured.RestAssured.given;

public class EnderecoClient {

    public Response listarEndereco(String idEndereco){
        Response response =
            given()
                .header("Authorization", Autenticacao.tokenAdmin())
                .contentType(ContentType.JSON)
                .pathParams("idEndereco", idEndereco)
            .when()
                .get("/endereco/{idEndereco}");

        return response;

    }
    public Response listarEnderecos(String pagina, String tamanhoDasPaginas){
        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .contentType(ContentType.JSON)
                        .queryParam("pagina", pagina)
                        .queryParam("tamanhoDasPaginas", tamanhoDasPaginas)
                .when()
                        .get("/endereco");

        return response;

    }

    public Response cadastrarEnderecoQueryEPath(String idPessoa, EnderecoModel endereco){
        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .contentType(ContentType.JSON)
                        .queryParam("idPessoa", idPessoa)
                        .pathParams("idPessoa", idPessoa)
                        .body(endereco)
                .when()
                        .post("/endereco/{idPessoa}");

        return response;

    }
    public Response cadastrarEndereco(String idPessoa, EnderecoModel endereco){
        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .contentType(ContentType.JSON)
                        .queryParam("idPessoa", idPessoa)
                        .body(endereco)
                .when()
                        .post("/endereco");

        return response;

    }

    public Response editarEndereco(String idEndereco, EnderecoModel endereco){
        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .contentType(ContentType.JSON)
                        .pathParam("idEndereco", idEndereco)
                        .body(endereco)
                .when()
                        .put("/endereco/{idEndereco}");

        return response;
    }

    public Response deletarEndereco(String idEndereco){
        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .contentType(ContentType.JSON)
                        .pathParam("idEndereco", idEndereco)
                .when()
                        .delete("/endereco/{idEndereco}");

        return response;
    }

    public Response listarEnderecoPorIdPessoa(String idPessoa){
        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .contentType(ContentType.JSON)
                        .queryParam("idPessoa", idPessoa)
                .when()
                        .get("/endereco/retorna-por-id-pessoa");

        return response;
    }

    public Response listarEnderecosPorPais(String pais){
        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .contentType(ContentType.JSON)
                        .queryParam("País", pais)
                .when()
                        .get("/endereco/retorna-por-pais");

        return response;
    }
}
