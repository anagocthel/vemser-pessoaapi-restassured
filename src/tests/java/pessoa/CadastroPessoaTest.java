package pessoa;

import base.BaseTest;
import client.PessoaClient;
import dataFactory.PessoaDataFactory;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class CadastroPessoaTest extends BaseTest {


    private PessoaClient client = new PessoaClient();

    @Test
    public void testDeveCadastrarPessoaComSucesso(){
        // massa de dados
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        client.cadastroPessoas(pessoaValida)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testDeveValidarMsgErroPessoaSemCPF(){
        // massa de dados
        PessoaModel pessoaValida = PessoaDataFactory.pessoaSemCPF();
        client.cadastroPessoas(pessoaValida)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("mensagem", Matchers.equalTo("Campo CPF inválido"));
    }

    //Retornou 200 ao invés de não permitir
    @Test
    public void testDeveValidarMsgErroPessoaComCPFInvalido(){
        // massa de dados
        PessoaModel pessoaCPFInvalido = PessoaDataFactory.pessoaCPFNumeroInvalido();
        client.cadastroPessoas(pessoaCPFInvalido)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("mensagem", Matchers.equalTo("Campo CPF inválido"));
    }

    @Test
    public void testDeveValidarMsgErroPessoaSemNome(){
        PessoaModel pessoaInvalida = PessoaDataFactory.pessoaValida();
        pessoaInvalida.setNome("");
        client.cadastroPessoas(pessoaInvalida)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("mensagem", Matchers.equalTo("Campo nome não pode ser nulo\""));
    }

    @Test
    public void testDeveValidarMsgErroPessoaSemDataNascimento(){
        PessoaModel pessoaInvalida = PessoaDataFactory.pessoaValida();
        pessoaInvalida.setDataNascimento("");
        client.cadastroPessoas(pessoaInvalida)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("mensagem", Matchers.equalTo("Campo data nascimento não pode ser nulo\""));
    }

    @Test
    public void testDeveValidarMsgErroPessoaSemEmail(){
        PessoaModel pessoaInvalida = PessoaDataFactory.pessoaValida();
        pessoaInvalida.setEmail("");
        client.cadastroPessoas(pessoaInvalida)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("mensagem", Matchers.equalTo("Campo email não pode ser nulo\""));
    }
}
