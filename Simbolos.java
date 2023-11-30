/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rogel
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simbolos {
    public static List<String[]> recorrerLineas() throws IOException {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader archivo = new BufferedReader(new FileReader("C:\\Users\\rogel\\OneDrive\\Escritorio\\Semestre 7\\Lenguajes y Autómatas 2\\archivo.txt"))) {
            String linea;
            while ((linea = archivo.readLine()) != null) {
                linea = linea.trim().substring(1, linea.length() - 1);
                String[] elementos = linea.split(",");
                datos.add(elementos);
            }
        }
        return datos;
    }

    public static boolean recorrerSimbolos(List<String[]> lista, String simbolo) {
        boolean resultado = false;
        for (String[] elem : lista) {
            if (elem[0].equals(simbolo)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

    public static void generarTablas(int i, List<String[]> lista, List<String[]> simbolos, List<String[]> tokens) {
        String[] l = lista.get(i);
        while (!l[1].equals("-2")) {
            if (recorrerSimbolos(simbolos, l[0])) {
                System.out.println();
            } else {
                // Agregar el identificador y su tipo en la tabla de símbolos
                if (l[1].equals("-51")) {
                    simbolos.add(new String[]{l[0], "-51", "0"});
                } else if (l[1].equals("-52")) {
                    simbolos.add(new String[]{l[0], "-52", "0.0"});
                } else if (l[1].equals("-53")) {
                    simbolos.add(new String[]{l[0], "-53", "null"});
                } else if (l[1].equals("-54")) {
                    simbolos.add(new String[]{l[0], "-54", "true"});
                } else if (l[1].equals("-55")) {
                    simbolos.add(new String[]{l[0], "-55", "desconocido"});
                }
            }

            tokens.add(l);
            l = lista.get(++i);
        }

        tokens.add(l);
    }

    public static void main(String[] args) {
        List<String[]> lineas;
        List<String[]> simbolos = new ArrayList<>();
        List<String[]> tokens = new ArrayList<>();

        try {
            lineas = recorrerLineas();
            int i = 0;
            for (String[] linea : lineas) {
                if (linea[1].equals("-15")) {
                    tokens.add(linea);
                    generarTablas(i, lineas, simbolos, tokens);
                } else {
                    tokens.add(linea);
                    i++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Tabla de Tokens:");
        for (String[] lista : tokens) {
            for (String elemento : lista) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }

        System.out.println("\nTabla de Símbolos:");
        for (String[] lista : simbolos) {
            for (String elemento : lista) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }
}