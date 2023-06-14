package pessoa;

import base.BaseTest;
import client.PessoaClient;
import dataFactory.PessoaDataFactory;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;


public class DeletarPessoaTest extends BaseTest {
    private PessoaClient client = new PessoaClient();

    @Test
    public void testDeletarPessoaComSucesso(){
        Integer id = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        client.deletarPessoas(id)
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testDeletarPessoaCriadaComSucesso(){
        PessoaModel pessoaCriada = PessoaDataFactory.pessoaNovaCriada();
        client.deletarPessoas(pessoaCriada.getIdPessoa())
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }


}
