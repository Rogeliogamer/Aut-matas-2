import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PseudoCodeInterpreter {

    public static void main(String[] args) {
        // Lee el archivo de seudocódigo
        String fileName = "C:\\Users\\rogel\\OneDrive\\Escritorio\\Ejemplo - Pila de Simbolos.txt"; // Cambia esto al nombre de tu archivo
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                interpretarLinea(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void interpretarLinea(String linea) {
        // Divide la línea en partes
        String[] partes = linea.split("\\s+");
        double b= 0;
        boolean resultadoR = false;
        
        // Realiza operaciones según la estructura del seudocódigo
        switch (partes[0]) {
            case "y":
                // y = 10 * b / 2;
                Scanner scannerY = new Scanner(System.in);
                System.out.print("Ingrese el valor de b: ");
                b = scannerY.nextDouble();
                double y = 10 * b / 2;
                System.out.println("y = " + y);
                break;

            case "s":
                // s = b * x * z * 10 / 30;
                Scanner scannerS = new Scanner(System.in);
                System.out.print("Ingrese el valor de x: ");
                double x = scannerS.nextDouble();
                System.out.print("Ingrese el valor de z: ");
                double z = scannerS.nextDouble();
                double s = b * x * z * (10 / 30);
                System.out.println("s = " + s);
                break;

            case "read":
                // read (y);
                Scanner scannerRead = new Scanner(System.in);
                System.out.print("Ingrese el valor de y: ");
                double valorY = scannerRead.nextDouble();
                break;

            case "Until":
                // Until (y > 500);
                Scanner scannerUntil = new Scanner(System.in);
                System.out.print("Ingrese el valor de y para la condición Until: ");
                double valorYUntil = scannerUntil.nextDouble();
                if (!(valorYUntil > 500)) {
                    // Terminar la ejecución o realizar alguna acción específica
                }
                break;

            case "r":
                // r = not ( (a > var1) or (m == true) ) and (100 >= r);
                Scanner scannerR = new Scanner(System.in);
                System.out.print("Ingrese el valor de a: ");
                double a = scannerR.nextDouble();
                System.out.print("Ingrese el valor de var1: ");
                double var1 = scannerR.nextDouble();
                Scanner scannerM = new Scanner(System.in);
                System.out.print("Ingrese el valor de m (true/false): ");
                boolean m = scannerM.nextBoolean();
                System.out.print("Ingrese el valor de r: ");
                double valorR = scannerR.nextDouble();
                resultadoR = !(a > var1 || m == true) && (100 >= valorR);
                System.out.println("r = " + resultadoR);
                break;

            case "write":
                // write (r);
                System.out.println("El valor de r es: " + resultadoR);
                break;

            default:
                // Otra operación no reconocida
                System.out.println("Operación no reconocida: " + linea);
                break;
        }
    }
}
