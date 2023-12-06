package VCI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Rogelio Perez Guevara
 * @version 20/11/23
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Pila {
    public Stack<String> pilaDeSimbolos = new Stack<>();
    public Stack<Integer> pilaDePrioridad = new Stack<>();

    public Stack<String> pilaDeEstados = new Stack<>();
    public Stack<Integer> pilaDeDirecciones = new Stack<>();

    public ArrayList<String> cintaDeValores = new ArrayList<>();

    public void clasificacion(File archivo) {
        try {
            String temporal = "";
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char caracter = line.charAt(i);
                    if (evitar(caracter) == false) {
                        temporal += caracter;
                    } else if (enviar(caracter) == true) {
                        if (temporal.length() > 0) {
                            verificarExistencia(temporal);
                        }
                        temporal = "";
                    }

                    switch (caracter) {
                        case ')':
                            while (!pilaDeSimbolos.lastElement().equals("(")) {
                                cintaDeValores.add(pilaDeSimbolos.pop());
                                pilaDePrioridad.pop();
                            }
                            if (!pilaDeEstados.isEmpty()) {
                                if (pilaDeEstados.lastElement().equals("Until")) {
                                    cintaDeValores.add(pilaDeDirecciones.pop().toString());
                                    pilaDeEstados.pop();
                                    cintaDeValores.add("UNTIL");
                                }
                            }
                            pilaDeSimbolos.pop();
                            pilaDePrioridad.pop();
                            break;
                        case '(':
                            pilaDeSimbolos.push("(");
                            pilaDePrioridad.push(0);
                        break;
                        case ';':
                            while (!pilaDeSimbolos.isEmpty()) {
                                cintaDeValores.add(pilaDeSimbolos.pop());
                                pilaDePrioridad.pop();
                            }
                        break;
                        default:
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        escribir(mostrarResultado());
    }

    public boolean evitar(char temporal) {
        Character[] evitar = new Character[]{';', '(', ')', ' '};
        boolean existe = false;
        for (char elemento : evitar) {
            if (elemento == temporal) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean enviar(char temporal) {
        Character[] evitar = new Character[]{';', ')', ' '};
        boolean existe = false;
        for (char elemento : evitar) {
            if (elemento == temporal) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public void verificarExistencia(String temporal) {
        String[] estructura = new String[]{"Repeat", "Until", "End", "Begin"};
        String[] prioridad_0 = new String[]{"="};
        String[] prioridad_10 = new String[]{"or"};
        String[] prioridad_20 = new String[]{"and"};
        String[] prioridad_30 = new String[]{"not"};
        String[] prioridad_40 = new String[]{"<", ">", "==", ">=", "<="};
        String[] prioridad_50 = new String[]{"+", "-"};
        String[] prioridad_60 = new String[]{"*", "/"};

        boolean encontrado = false;

        for (String elemento : prioridad_0) {
            if (temporal.equals(elemento)) {
                operacionDePilas(temporal, 0);
                encontrado = true;
                break;
            }
        }
        for (String elemento : prioridad_10) {
            if (temporal.equals(elemento)) {
                operacionDePilas(temporal, 10);
                encontrado = true;
                break;
            }
        }
        for (String elemento : prioridad_20) {
            if (temporal.equals(elemento)) {
                operacionDePilas(temporal, 20);
                encontrado = true;
                break;
            }
        }
        for (String elemento : prioridad_30) {
            if (temporal.equals(elemento)) {
                operacionDePilas(temporal, 30);
                encontrado = true;
                break;
            }
        }
        for (String elemento : prioridad_40) {
            if (temporal.equals(elemento)) {
                operacionDePilas(temporal, 40);
                encontrado = true;
                break;
            }
        }
        for (String elemento : prioridad_50) {
            if (temporal.equals(elemento)) {
                operacionDePilas(temporal, 50);
                encontrado = true;
                break;
            }
        }
        for (String elemento : prioridad_60) {
            if (temporal.equals(elemento)) {
                operacionDePilas(temporal, 60);
                encontrado = true;
                break;
            }
        }
        for (String elemento : estructura) {
            if (temporal.equals(elemento)) {
                operacionDeEstructuras(elemento);
                encontrado = true;
                break;
            }
        }

        if (encontrado == false) {
            cintaDeValores.add(temporal);
        }
    }

    public void operacionDePilas(String temporal, int prioridad) {
        if (pilaDeSimbolos.isEmpty()) {
            pilaDeSimbolos.push(temporal);
            pilaDePrioridad.push(prioridad);
        } else {
            if (pilaDePrioridad.lastElement() >= prioridad) {
                pilaDePrioridad.pop();
                String respaldo = pilaDeSimbolos.pop();
                pilaDeSimbolos.push(temporal);
                pilaDePrioridad.push(prioridad);
                cintaDeValores.add(respaldo);
            } else {
                pilaDeSimbolos.push(temporal);
                pilaDePrioridad.push(prioridad);
            }
        }
    }

    public void operacionDeEstructuras(String temporal) {
        switch (temporal) {
            case "Repeat":
                pilaDeEstados.push("Repeat");
                pilaDeDirecciones.push(cintaDeValores.size());
            break;
            case "Until":
                pilaDeEstados.push("Until");
                break;
            case "End":
                pilaDeEstados.pop();
                break;
            case "Begin":

            break;
        }
    }

    public String mostrarResultado() {
        String resultado = "";
        resultado += "\nCinta de valores\n";
        for (String a : cintaDeValores) {
            int espacios = 8 - a.length();
            String espaciado = "";
            for (int i = 0; i < espacios; i++) {
                espaciado += " ";
            }
            resultado += a + espaciado + " | ";
        }
        resultado += "\n";
        for (int apuntador = 0; apuntador < cintaDeValores.size(); apuntador++) {
            int espacios = 8 - Integer.toString(apuntador).length();
            String espaciado = "";
            for (int i = 0; i < espacios; i++) {
                espaciado += " ";
            }
            resultado += apuntador + espaciado + " | ";
        }
        System.out.println(resultado);
        return resultado;
    }

    public static void escribir(String captura) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            File file = new File("C://Users//rogel//OneDrive//Escritorio//Resultado.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(captura + "\n");
            System.out.println("\nSe ha guardo en un archivo txt con el nombre \"Resultado\"");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}