package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Main {

    

    public static void main(String[] args){
        //connectAndPringURLJavaOracle();
        //printarNomeCompleto("Joao", "silva");
        //printarSoma(2,2,4);
    }


    public static void connectAndPringURLJavaOracle(){


        try {

            var url = new URL("https://docs.oracle.com/javase/10/language/");
            var urlConnection = url.openConnection();

            /* Não faz parte das boas praticas */
            try(var bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
                System.out.println(bufferedReader.lines().collect(Collectors.joining()).replaceAll(">", ">\n"));
            }


        }catch(Exception e){
            e.printStackTrace();
        }


        //var bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

    }


    public static void printarNomeCompleto(String nome, String sobrenome){
        var nomeCompleto = String.format("%s %s", nome, sobrenome);
        System.out.println(nomeCompleto);
    }

    public static void printarSoma(int... numeros) {
        int soma;
        if(numeros.length > 0){
            soma = 0;
            /*for (var numero : numeros){
                soma+=numero;
            }*/
            for (var numero = 0; numero < numeros.length; numero++){
                soma += numeros[numero];
            }
            System.out.println("valor::: " + soma);
        }
    }

    //Consegue

    //variaveis locais inicializadas
    //variável suporte do enhaced for
    //variável suporte do for iterativo
    //variavel try with resource


    //Não Consegue

    //var não pode ser utilizado em nivel de classe
    //var não pode ser utilizado como parâmetro
    //var nao pode ser utilizada em variaveis locais nao inicializadas

    //https://docs.oracle.com/javase/10/language/

    //docker container run -it -m512M --entrypoint bash openjdk:7-jdk
    //docker container run -it -m512M --entrypoint bash openjdk:10-jdk (Versão 10 JavaJDK)
    //java -XX:+PrintFlagsFinal -version (Mostra as configurações Java)
    //java -XX:+PrintFlagsFinal -version | grep MaxHeapSize

    //docker container run -it --cpus 2 openjdk:10-jdk
    //Runtime.getRuntime().availableProcessors()  (Mostra a qtd de cpus-cores usadas)
    // Para sair do jShell digitamos ( /exit )



}
