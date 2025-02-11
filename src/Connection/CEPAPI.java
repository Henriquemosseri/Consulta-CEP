package Connection;

import Bean.CEP;
import Bean.CEPvia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CEPAPI {
    private Gson gson= new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private List<CEP> lista= new ArrayList<>();


    public Gson getGson() {
        return gson;
    }
    public CEP pesquisarCEP(String cep){
        String json="";
        try {
            if(cep.equalsIgnoreCase("sair")) return null;
            String endereco = "http://viacep.com.br/ws/"+cep+"/json/";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
            lista.add(converterCEP(json));

        } catch (Exception e){
            System.out.println("erro ao pesquisar: "+ e.getMessage());
        }
        return converterCEP(json);
    }

    private CEP converterCEP(String json){
        CEPvia via= gson.fromJson(json, CEPvia.class);
        CEP cep= new CEP(via);

    return cep;
    }
    public void CriarDocumento(){
        try {
            FileWriter file = new FileWriter("endereco.json");
            file.write(gson.toJson(lista));
            file.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
