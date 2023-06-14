package endereco;

import base.BaseTest;
import client.EnderecoClient;
import dataFactory.EnderecoDataFactory;
import io.restassured.http.ContentType;
import model.EnderecoModel;
import model.PaginacaoEnderecoModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListarEnderecosTest extends BaseTest {

    private EnderecoClient client = new EnderecoClient();

    @Test
    public void testeValidarListagemDeEnderecoComIdEndereçoValido(){

        EnderecoModel enderecoModel = EnderecoDataFactory.listarEndereco();

        EnderecoModel retorno = client.listarEndereco(String.valueOf(enderecoModel.getIdEndereco()))
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .extract()
                        .as(EnderecoModel.class);

        Assertions.assertEquals(enderecoModel.getIdEndereco(), retorno.getIdEndereco());
        Assertions.assertEquals(enderecoModel.getCep(), retorno.getCep());
        Assertions.assertEquals(enderecoModel.getIdPessoa(), retorno.getIdPessoa());
        Assertions.assertEquals(enderecoModel.getNumero(), retorno.getNumero());
        Assertions.assertEquals(enderecoModel.getPais(), retorno.getPais());
    }

    @Test
    public void testeValidarListagemDeEnderecoComIdEnderecoCaracteresInvalidos(){

        client.listarEndereco("@jhdbsdhlb-")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    //Deu erro pois retornou 200 e listou vários endereços
    @Test
    public void testeValidarEnderecoComIdEnderecoVazio(){

        client.listarEndereco("")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void testeValidarPaginacaoDoListarEnderecosCadastradosComValoresInvalidos(){
        PaginacaoEnderecoModel paginacaoEnderecoModel = EnderecoDataFactory.paginacaoEnderecoModel();
        client.listarEnderecos(String.valueOf(paginacaoEnderecoModel.getTotalElements()+1000), String.valueOf(paginacaoEnderecoModel.getTotalElements()+1000))
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    //Não deixa trazer todos na mesma página
    @Test
    public void testeValidarListagemDeEnderecosCadastradosComPaginacaoVazia(){
        PaginacaoEnderecoModel retorno = client.listarEnderecos("","")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .extract()
                        .as(PaginacaoEnderecoModel.class);

        Assertions.assertEquals(retorno.getTotalElements(),retorno.getContent().size());
    }

    @Test
    public void testeValidarListagemDeEnderecosPorIdPessoaComIdValido(){

        EnderecoModel enderecoModel = EnderecoDataFactory.listarEndereco();

        client.listarEnderecoPorIdPessoa(String.valueOf(enderecoModel.getIdPessoa()))
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON);
    }

    @Test
    public void testeValidarListagemDeEnderecosPorIdPessoaComIdVazio(){
        client.listarEnderecoPorIdPessoa("")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

//Acredito que seria melhor o parametro não ter caracter especial e nem comecar com letra maiúscula (País)
    @Test
    public void testeValidarListagemDeEnderecosPorPais(){

        EnderecoModel enderecoModel = EnderecoDataFactory.listarEndereco();

        client.listarEnderecosPorPais(enderecoModel.getPais())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON);
    }
//Deveria dar 400 mas deu 200
    @Test
    public void testeValidarListagemDeEnderecosPorPaisComPaisVazio(){
        client.listarEnderecosPorPais("")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
