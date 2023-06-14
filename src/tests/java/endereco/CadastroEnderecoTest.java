package endereco;

import base.BaseTest;
import client.EnderecoClient;
import dataFactory.EnderecoDataFactory;
import dataFactory.PessoaDataFactory;
import model.EnderecoModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class CadastroEnderecoTest extends BaseTest {

    public EnderecoClient client = new EnderecoClient();

    @Test
    public void testValidarCadastroDeEnderecoComEnderecoValido(){
        Integer idPessoaValida = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        EnderecoModel enderecoValido = EnderecoDataFactory.enderecoValido();
        client.cadastrarEndereco(String.valueOf(idPessoaValida), enderecoValido)
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }

    //Falhou alegando que o CEP era inválido mas o CEP é valido
    @Test
    public void testValidarCadastroDeEnderecoComEnderecoValidoEpathNameEQueryName(){
        Integer idPessoaValida = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        EnderecoModel enderecoValido = EnderecoDataFactory.enderecoValido();
        client.cadastrarEnderecoQueryEPath(String.valueOf(idPessoaValida), enderecoValido)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }


}
