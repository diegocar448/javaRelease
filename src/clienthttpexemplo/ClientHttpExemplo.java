package clienthttpexemplo;


import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ClientHttpExemplo {

    static ExecutorService executor = Executors.newFixedThreadPool(6, new ThreadFactory(){
       @Override
       public Thread newThread(Runnable runnable){
           Thread thread = new Thread(runnable);
           System.out.println("Nova Thread criada "+ (thread.isDaemon() ? "daemon" : "") + " Thread Group :: "+ thread.getThreadGroup());

           return thread;
       }
    });



    public static void main(String[] args) throws Exception {
        //connectAndPringURLJavaOracle();
        connectAkanaiHttpClient();
        //printarNomeCompleto("Joao", "silva");
        //printarSoma(2,2,4);
    }

    public static void connectAkanaiHttpClient() throws Exception {
        //System.out.println("Runnning HTTP/1.1 example ...");
        System.out.println("Runnning HTTP/2 example ...");

        try{
            HttpClient httpClient = HttpClient.newBuilder()
                    //.version(HttpClient.Version.HTTP_1_1)
                    .version(HttpClient.Version.HTTP_2)
                    .proxy(ProxySelector.getDefault())
                    .build();
            long start = System.currentTimeMillis();

            HttpRequest mainRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.akamai.com/demo/h2_demo_frame.html"))
                    .build();

            HttpResponse<String> response = httpClient.send(mainRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code ::: "+ response.statusCode());
            System.out.println("Response Headers ::: "+ response.headers());
            System.out.println(response.body());

            List<Future<?>> future = new ArrayList<>();

            String responseBody = response.body();
                    responseBody
                    .lines()
                    .filter(line -> line.trim().startsWith("<img height>"))
                    .map(line -> line.substring(line.indexOf("src='") + 5, line.indexOf("'/>")))
                    .forEach(image -> {
                        Future<?> imgFuture = executor.submit(() -> {
                           HttpRequest imgRequest = HttpRequest.newBuilder()
                                   .uri(URI.create("https://http2.akamai.com"+image))
                                   .build();



                          try{

                              HttpResponse<String> imageResponse = httpClient.send(imgRequest, HttpResponse.BodyHandlers.ofString());

                              System.out.println("Imagem carregada :: " + image + ", status code :: " + imageResponse.statusCode());
                          }catch(IOException | InterruptedException e){
                              System.out.println("Mensagem de erro durante requisi????o para recuperar a imagem "+ image);
                          }
                        });
                        future.add(imgFuture);
                        System.out.println("Submetido um futuro para a imagem "+image);
                    });


                    future.forEach(f-> {
                        try{
                            f.get();
                        }catch(InterruptedException | ExecutionException e){
                            System.out.println("Error ao esperar carregar imagem do futuro");
                        }
                    });
                    long end = System.currentTimeMillis();
                    System.out.println("Tempo de carregamento total :: " + (end - start) + " ms");


        }finally {
            executor.shutdown();
        }

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


        /*Http2 temos a multiplexa????o (varias requisi????es em paralelo sem o problema de fila de requisi????es e gargalo)*/

    }
}
