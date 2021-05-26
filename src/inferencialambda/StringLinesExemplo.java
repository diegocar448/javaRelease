package inferencialambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StringLinesExemplo {
    /*public static void main(String[] args) {
        String html = "<html> <head> \n</head> \n<body> \n</body> \n</html>";
        System.out.println(html.lines().map(s -> "[TAG] :: "+s).collect(Collectors.joining()));
    }*/


    /*DESAFIO 1*/
    /*public static void main(String[] args) throws IOException {

        String entrada = "13";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        entrada = br.readLine();
        for (int i = 2; i <= Integer.parseInt(entrada); i++) {

            if (i % 2 == 0) {

                System.out.println(i);

            }

        }
    }*/


    /*DESAFIO 2*/
    /*public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        //https://www.devmedia.com.br/como-funciona-a-classe-scanner-do-java/28448
        Scanner sc = new Scanner(System.in);



        String[] cpf = new String[4];
        cpf = sc.nextLine().split("-");



        for(int i= 0; i < cpf.length; i++) {
            //Para cada vez que encontrarmos na string um " . " então quebramos a linha
            System.out.println(cpf[i].replace(".","\n"));
        }
        //Encerramos o algoritmo
        sc.close();
    }*/


    /*DESAFIO 3*/
    public static void main(String[] args) throws IOException {
        Scanner leitor = new Scanner(System.in);
        int DDD = leitor.nextInt();

        switch (DDD) {
            case 61: System.out.println("Brasilia"); break;
            case 71: System.out.println("Salvador"); break;
            case 11: System.out.println("Sao Paulo"); break;
            case 21: System.out.println("Rio de Janeiro"); break;
            case 32: System.out.println("Juiz de Fora"); break;
            case 19: System.out.println("Campinas"); break;
            case 27: System.out.println("Vitoria"); break;
            case 31: System.out.println("Belo Horizonte"); break;

            default: System.out.println("DDD nao cadastrado");
        }
    }
}
