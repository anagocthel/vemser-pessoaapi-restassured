package endereco;

import base.BaseTest;
import client.EnderecoClient;
import dataFactory.EnderecoDataFactory;
import model.EnderecoModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeletarEnderecoTest extends BaseTest {

    public EnderecoClient client = new EnderecoClient();
    @Test
    public void testValidarDelecaoDeEnderecoComIdValido(){
        EnderecoModel enderecoModel = EnderecoDataFactory.listarEndereco();
        client.deletarEndereco(String.valueOf(enderecoModel.getIdEndereco()))
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testValidarDelecaoDeEnderecoComIdComCaracterInvalido(){
        client.deletarEndereco("@jskjsk")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


    // Deu erro porque retornou 500 ao invés de 400
    @Test
    public void testValidarDelecaoDeEnderecoComIdVazio(){
        client.deletarEndereco(" ")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


}
