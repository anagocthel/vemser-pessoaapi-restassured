package pessoa;

import base.BaseTest;
import client.PessoaClient;
import dataFactory.PessoaDataFactory;
import io.restassured.http.ContentType;
import model.PaginacaoPessoaModel;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ListarPessoasTest extends BaseTest {

    PessoaClient client = new PessoaClient();


    @Test
    public void testDeveListarPessoasComSucesso(){

        client.listarPessoas()
                .then()
                .statusCode(HttpStatus.SC_OK)
        ;
    }

    @Test
    public void testDeveListarPessoasPorCPFComSucesso(){
        PessoaModel pessoaModel = PessoaDataFactory.listarUmaPessoa();
        PessoaModel pessoaRetorno = client.listarPessoasCPF(pessoaModel.getCpf())
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                        .as(PessoaModel.class)
        ;

        Assertions.assertEquals(pessoaModel.getCpf(), pessoaRetorno.getCpf());
        Assertions.assertEquals(pessoaModel.getNome(), pessoaRetorno.getNome());
        Assertions.assertEquals(pessoaModel.getEmail(), pessoaRetorno.getEmail());
        Assertions.assertEquals(pessoaModel.getDataNascimento(), pessoaRetorno.getDataNascimento());
        Assertions.assertNotNull(pessoaRetorno.getIdPessoa());
    }

    @Test
    public void testDeveListarPessoasPorNomeComSucesso(){
        client.listarPessoasNome(PessoaDataFactory.listarUmaPessoa().getNome())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .log().all()
        ;
    }

    @Test
    public void testDeveListarPessoasComPagianacaoComSucesso(){
        Integer page = 1;
        Integer size = 100;

        PaginacaoPessoaModel result = client.listarPessoas(page, size)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PaginacaoPessoaModel.class)
                ;

        Assertions.assertEquals(page, result.getPage());
        Assertions.assertEquals(size, result.getContent().size());
    }

    @Test
    public void testDeveListarPessoasComPagianacaoSemSucesso(){
        String page = "alyson";
        String size = "campos";

        client.listarPessoas(page, size)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }

    //Não tenho certeza se meu teste está correto
    @Test
    public void testValidarPaginacaoComNumerosInvalidos(){
        Integer totalElementos = PessoaDataFactory.listarPessoasEExtrairTotalElementos();
        String page = String.valueOf(totalElementos+1000);
        String size = String.valueOf(totalElementos+1000);

        client.listarPessoas(page, size)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;

    }

}
