package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Main {

    

    public static void main(String[] args) throws IOException {
        printarNomeCompleto("Joao", "silva");
        printarSoma(2,2,4);
    }


    public static void connectAndPringURLJavaOracle() throws IOException {
        var url = new URL("https://docs.oracle.com/javase/10/language/");
        var urlConnection = url.openConnection();

        /*try {

        }catch(Exception e){

        }*/


        var bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        System.out.println(bufferedReader.lines().collect(Collectors.joining()).replaceAll(">", ">\n"));
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



    //Não Consegue

    //var não pode ser utilizado em nivel de classe
    //var não pode ser utilizado como parâmetro
    //var nao pode ser utilizada em variaveis locais nao inicializadas


}
