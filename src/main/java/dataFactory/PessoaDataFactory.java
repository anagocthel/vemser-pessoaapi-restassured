package dataFactory;

import client.PessoaClient;
import model.PaginacaoPessoaModel;
import model.PessoaModel;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

public class PessoaDataFactory {

    private static Faker faker = new Faker();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static PessoaClient client = new PessoaClient();

    private PessoaDataFactory() {}

    private static PessoaModel pessoaNova() {
        PessoaModel pessoa = new PessoaModel();
        pessoa.setNome(faker.name().nameWithMiddle());
        pessoa.setDataNascimento(dateFormat.format(faker.date().birthday()));
        pessoa.setCpf(faker.cpf().valid(false));
        pessoa.setEmail(faker.internet().emailAddress());

        return pessoa;
    }
    public static PessoaModel pessoaValida() {
        return pessoaNova();
    }

    public static PessoaModel pessoaSemCPF() {
        PessoaModel pessoaSemCPF= pessoaNova();
        pessoaSemCPF.setCpf(StringUtils.EMPTY);
        return pessoaSemCPF;
    }
    public static PessoaModel pessoaCPFNumeroInvalido() {
        PessoaModel pessoaCPFInvalido= pessoaNova();
        pessoaCPFInvalido.setCpf("123@");
        return pessoaCPFInvalido;
    }

    public static PessoaModel listarUmaPessoa(){
        PaginacaoPessoaModel paginacaoPessoaModel = client.listarPessoas()
                .then()
                .extract()
                .as(PaginacaoPessoaModel.class)
                ;

        return paginacaoPessoaModel.getContent().get(0);
    }

    public static Integer listarPessoasEExtrairTotalElementos(){
        PaginacaoPessoaModel paginacaoPessoaModel = client.listarPessoas()
                .then()
                .extract()
                .as(PaginacaoPessoaModel.class)
                ;

        return paginacaoPessoaModel.getTotalElements();
    }

    static public Integer listarPessoasEExtrairIdDeUma(){
        PaginacaoPessoaModel paginacaoPessoaModel = client.listarPessoas()
                .then()
                .extract()
                .as(PaginacaoPessoaModel.class)
                ;

        return paginacaoPessoaModel.getContent().get(0).getIdPessoa();
    }

    static public PessoaModel pessoaNovaCriada(){
        PessoaModel pessoaValida = pessoaValida();
        PessoaModel pessoaCriada = client.cadastroPessoas(pessoaValida).then().extract().as(PessoaModel.class);

        return pessoaCriada;
    }

}

