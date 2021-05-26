package clienthttpexemplo;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttpExemplo {

    public static void main(String[] args) throws IOException, InterruptedException{
        connectAndPringURLJavaOracle();
        //printarNomeCompleto("Joao", "silva");
        //printarSoma(2,2,4);
    }

    public static void connectAndPringURLJavaOracle() throws IOException, InterruptedException {
        //var url = new URL("https://docs.oracle.com/javase/10/language/");

        HttpRequest request = HttpRequest.newBuilder()
                .GET().uri(URI.create("https://docs.oracle.com/javase/10/language/"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code :: "+ response.statusCode());
        System.out.println("Headers :: "+ response.headers());
        System.out.println(response.body());


        /*Http2 temos a multiplexação (varias requisições em paralelo sem o problema de fila de requisições e gargalo)*/

    }
}
