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
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Interfaz extends javax.swing.JFrame{
    public static File archivo;

    public Interfaz() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        Label1 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        BotonSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Txa1 = new javax.swing.JTextArea();
        BotonEvaluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Label1.setText("Lectura de un archivo");

        Label2.setText("Selecciona el archivo que quieres leer");

        BotonSeleccionar.setText("Seleccionar");
        BotonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSeleccionarActionPerformed(evt);
            }
        });

        Txa1.setColumns(20);
        Txa1.setRows(5);
        jScrollPane1.setViewportView(Txa1);

        BotonEvaluar.setText("Evaluar");
        BotonEvaluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEvaluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Label1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Label2)
                                .addGap(18, 18, 18)
                                .addComponent(BotonSeleccionar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BotonEvaluar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label2)
                    .addComponent(BotonSeleccionar)
                    .addComponent(BotonEvaluar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void BotonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        archivo = fc.getSelectedFile();
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String texto = "";
            String linea;
            while (((linea = br.readLine()) != null)) {
                texto += linea + "\n";
            }
            Txa1.setText(texto);
            JOptionPane.showMessageDialog(null, "Archivo leido exitosamente");
        } catch (Exception e) {

        }
    }                                                

    private void BotonEvaluarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Pila metodo = new Pila();
        metodo.clasificacion(archivo);
        
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

    public static void main(String args[]) {
        String rutaArchivo = "C://Users//rogel//OneDrive//Escritorio//Resultado.txt";
        try {
            borrarContenidoArchivo(rutaArchivo);
            System.out.println("Contenido del archivo borrado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al borrar el contenido del archivo: " + e.getMessage());
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
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
    
    public static void borrarContenidoArchivo(String rutaArchivo) throws IOException {
        // Abre el archivo en modo de escritura, esto borrará el contenido existente
        BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo));
        
        // Cierra el escritor para aplicar los cambios
        writer.close();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton BotonEvaluar;
    private javax.swing.JButton BotonSeleccionar;
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JTextArea Txa1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}