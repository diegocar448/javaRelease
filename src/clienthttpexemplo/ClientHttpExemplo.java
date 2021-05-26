package clienthttpexemplo;


import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class ClientHttpExemplo {

    static ExecutorService executor = Executors.newFixedThreadPool(6, new ThreadFactory(){
       @Override
       public Thread newThread(Runnable runnable){
           Thread thread = new Thread(runnable);
           System.out.println("Nova Thread criada "+ (thread.isDaemon() ? "daemon" : "") + " Thread Group :: "+ thread.getThreadGroup());

           return thread;
       }
    });



    public static void main(String[] args) throws IOException, InterruptedException{
        //connectAndPringURLJavaOracle();
        connectAkanaiHttpClient();
        //printarNomeCompleto("Joao", "silva");
        //printarSoma(2,2,4);
    }

    public static void connectAkanaiHttpClient() {
        System.out.println("Runnning HTTP/1.1 example ...");

        try{
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
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
                          }catch(IOException e){
                              e.printStackTrace();
                          }catch(InterruptedException e){
                              e.printStackTrace();
                          }
                        });
                        future.add(imgFuture);
                        System.out.println("Submetido um futuro para a imagem "+image);
                    });


                    future.forEach(f-> {
                        try{
                            f.get();
                        }catch(IOException e){
                            e.printStackTrace();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    });
                    long end = System.currentTimeMillis();
                    System.out.println("Tempo de carregamento total :: " + (end - start) + " ms");


        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
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


        /*Http2 temos a multiplexação (varias requisições em paralelo sem o problema de fila de requisições e gargalo)*/

    }
}
