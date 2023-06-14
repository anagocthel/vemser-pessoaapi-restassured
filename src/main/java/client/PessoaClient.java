package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.PessoaModel;
import utils.Autenticacao;

import static io.restassured.RestAssured.given;

public class PessoaClient {

    public Response listarPessoas() {
        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .contentType(ContentType.JSON)
                .when()
                        .get("/pessoa")
                ;

        return response;
    }

    public Response listarPessoas(Integer page, Integer size) {
        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .queryParam("pagina", page)
                        .queryParam("tamanhoDasPaginas", size)
                        .when()
                        .get("/pessoa")
                ;

        return response;
    }

    public Response listarPessoas(String page, String size) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .queryParam("pagina", page)
                        .queryParam("tamanhoDasPaginas", size)
                        .when()
                        .get("/pessoa")
                ;

        return response;
    }
    public Response listarPessoasCPF(String cpf) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .pathParam("cpf", cpf)
                        .when()
                        .get("/pessoa/{cpf}/cpf")
                ;

        return response;
    }
    public Response listarPessoasNome(String nome) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .queryParam("nome", nome)
                        .when()
                        .get("/pessoa/byname/")
                ;

        return response;
    }

    public Response cadastroPessoas(PessoaModel pessoa){
        return
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .body(pessoa)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/pessoa")
                ;

    }

    public Response editarPessoas(PessoaModel pessoa, Integer idPessoa){
        return
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .pathParams("idPessoa",idPessoa)
                        .body(pessoa)
                        .contentType(ContentType.JSON)
                .when()
                        .put("/pessoa/{idPessoa}")
                ;

    }

    public Response deletarPessoas(Integer idPessoa) {
        return
                given()
                        .header("Authorization", Autenticacao.tokenAdmin())
                        .pathParams("idPessoa",idPessoa)
                        .contentType(ContentType.JSON)
                .when()
                        .delete("/pessoa/{idPessoa}")
                ;
    }
}