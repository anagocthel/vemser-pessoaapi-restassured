package dataFactory;

import client.EnderecoClient;
import model.EnderecoModel;
import model.PaginacaoEnderecoModel;
import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.List;

public class EnderecoDataFactory {

    private static Faker faker = new Faker();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static EnderecoClient client = new EnderecoClient();

    private static EnderecoModel enderecoNovo(){
        EnderecoModel enderecoModel = new EnderecoModel();

        enderecoModel.setCep(faker.address().zipCode());
        enderecoModel.setComplemento("");
        enderecoModel.setEstado(faker.address().state());
        enderecoModel.setNumero(Integer.valueOf(faker.address().buildingNumber()));
        enderecoModel.setLogradouro(faker.address().streetName());
        enderecoModel.setPais(faker.address().country());
        enderecoModel.setTipo("RESIDENCIAL");
        enderecoModel.setCidade(faker.address().cityName());
        return enderecoModel;
    }

    public static EnderecoModel enderecoValido(){
        return enderecoNovo();
    }

    public static EnderecoModel enderecoCEPValido(){
        EnderecoModel enderecoModel = enderecoNovo();
        enderecoModel.setCep("12345678");
        return enderecoModel;
    }

    public static EnderecoModel listarEndereco(){
       PaginacaoEnderecoModel paginacaoEnderecoModel = client.listarEnderecos("","")
                .then()
                    .extract()
                        .as(PaginacaoEnderecoModel.class);
       return paginacaoEnderecoModel.getContent().get(0);
    }

    public static PaginacaoEnderecoModel paginacaoEnderecoModel(){
        return client.listarEnderecos("","")
                .then()
                    .extract()
                        .as(PaginacaoEnderecoModel.class);
    }


}
