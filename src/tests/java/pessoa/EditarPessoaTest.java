package pessoa;

import base.BaseTest;
import client.PessoaClient;
import dataFactory.PessoaDataFactory;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditarPessoaTest extends BaseTest {

    private PessoaClient client = new PessoaClient();

    @Test
    public void testEditarPessoaComSucesso(){
        PessoaModel pessoaModel = PessoaDataFactory.pessoaValida();
        Integer id = PessoaDataFactory.listarPessoasEExtrairIdDeUma();
        PessoaModel result = client.editarPessoas(pessoaModel, id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PessoaModel.class);

        Assertions.assertEquals(pessoaModel.getCpf(), result.getCpf());
        Assertions.assertEquals(pessoaModel.getNome(), result.getNome());
    }

    @Test
    public void testVerificarStatusEditarPessoaDeletada(){
        PessoaModel pessoaCriada = PessoaDataFactory.pessoaNovaCriada();
        client.deletarPessoas(pessoaCriada.getIdPessoa());
        client.editarPessoas(pessoaCriada, pessoaCriada.getIdPessoa())
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
