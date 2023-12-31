package endereco;

import base.BaseTest;
import client.EnderecoClient;
import dataFactory.EnderecoDataFactory;
import dataFactory.PessoaDataFactory;
import io.restassured.http.ContentType;
import model.EnderecoModel;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditarEnderecoTest extends BaseTest {

    public EnderecoClient client = new EnderecoClient();

    //Não rodou novamente pela questão do CEP
    @Test
    public void testValidarEdicaoDeEnderecoPorIdComDadosValidos(){

        EnderecoModel enderecoValido = EnderecoDataFactory.enderecoValido();
        Integer idEndereco = EnderecoDataFactory.listarEndereco().getIdEndereco();
        Integer idPessoaValida = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        enderecoValido.setIdPessoa(idPessoaValida);

        EnderecoModel retorno = client.editarEndereco(String.valueOf(idEndereco), enderecoValido)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .extract()
                        .as(EnderecoModel.class);

        Assertions.assertEquals(idEndereco, retorno.getIdEndereco());
        Assertions.assertEquals(enderecoValido.getCep(), retorno.getCep());
        Assertions.assertEquals(enderecoValido.getIdPessoa(), retorno.getIdPessoa());
        Assertions.assertEquals(enderecoValido.getNumero(), retorno.getNumero());
        Assertions.assertEquals(enderecoValido.getPais(), retorno.getPais());

    }

    @Test
    public void testValidarEdicaoDeEnderecoPorIdComCEPPadrao(){

        EnderecoModel enderecoValido = EnderecoDataFactory.enderecoCEPValido();
        Integer idEndereco = EnderecoDataFactory.listarEndereco().getIdEndereco();
        Integer idPessoaValida = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        enderecoValido.setIdPessoa(idPessoaValida);
        EnderecoModel retorno = client.editarEndereco(String.valueOf(idEndereco), enderecoValido)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .extract()
                        .as(EnderecoModel.class);

        Assertions.assertEquals(idEndereco, retorno.getIdEndereco());
        Assertions.assertEquals(enderecoValido.getCep(), retorno.getCep());
        Assertions.assertEquals(enderecoValido.getIdPessoa(), retorno.getIdPessoa());
        Assertions.assertEquals(enderecoValido.getNumero(), retorno.getNumero());
        Assertions.assertEquals(enderecoValido.getPais(), retorno.getPais());

    }


    @Test
    public void testValidarEdicaoDeEnderecoComIdInvalido(){

        EnderecoModel enderecoValido = EnderecoDataFactory.enderecoCEPValido();
        String idEndereco = "shjksah@";
        Integer idPessoaValida = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        enderecoValido.setIdPessoa(idPessoaValida);
        client.editarEndereco(idEndereco, enderecoValido)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors",Matchers.hasItem(Matchers.equalTo("idEndereco: must not be invalid")));

    }


    //Não contém mensagem de erro
    @Test
    public void testValidarEdicaoDeEnderecoComIdVazio(){

        EnderecoModel enderecoValido = EnderecoDataFactory.enderecoCEPValido();
        String idEndereco = "";
        Integer idPessoaValida = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        enderecoValido.setIdPessoa(idPessoaValida);
        client.editarEndereco(idEndereco, enderecoValido)
                .then()
                    .statusCode(405)
                    .body("errors",Matchers.hasItem(Matchers.equalTo("idEndereco: must not be blank")));
    }


    //Mensagem de erro diferente do padrão das outras, seria interessante padronizar
    @Test
    public void testValidarEdicaoDeEnderecoComOCampoNumeroComCaracterInvalido(){

        EnderecoModel enderecoValido = EnderecoDataFactory.enderecoCEPValido();
        Integer idEndereco = EnderecoDataFactory.listarEndereco().getIdEndereco();
        Integer idPessoaValida = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        enderecoValido.setIdPessoa(idPessoaValida);
        enderecoValido.setNumero(null);
        client.editarEndereco(String.valueOf(idEndereco), enderecoValido)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors",Matchers.hasItem(Matchers.equalTo("numero: Informe um numero")));
    }

    //Deu erro 500 ao invés de 400
    @Test
    public void testValidarEdicaoDeEnderecoComCEPComCaracteresInvalidos(){

        EnderecoModel enderecoValido = EnderecoDataFactory.enderecoValido();
        Integer idEndereco = EnderecoDataFactory.listarEndereco().getIdEndereco();
        Integer idPessoaValida = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        enderecoValido.setIdPessoa(idPessoaValida);
        enderecoValido.setCep("@qksjdç.");
        client.editarEndereco(String.valueOf(idEndereco), enderecoValido)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors",Matchers.hasItem(Matchers.equalTo("cep: must not be invalid")));

    }

    @Test
    public void testValidarEdicaoDeEnderecoComIdPessoaNulo(){

        EnderecoModel enderecoValido = EnderecoDataFactory.enderecoCEPValido();
        Integer idEndereco = EnderecoDataFactory.listarEndereco().getIdEndereco();
        enderecoValido.setIdPessoa(null);
        client.editarEndereco(String.valueOf(idEndereco), enderecoValido)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors", Matchers.hasItem(Matchers.equalTo("idPessoa: must not be null")));

    }





}
