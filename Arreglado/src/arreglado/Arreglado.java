/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arreglado;

import java.util.Scanner;

/**
 *
 * @author jrdjh
 */
public class Arreglado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while (true) {
            Scanner leer = new Scanner(System.in);
            String operacion;

            System.out.print("Ingrese su operacion: ");
            operacion = leer.next();

            String[][] matrizOperaciones = new String[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    matrizOperaciones[i][j] = "";
                }
            }
            String[] tokenPor = operacion.split("[+]");
            for (int i = 0; i < tokenPor.length; i++) {
                String[] tokenDiv = tokenPor[i].split("[-]");
                for (int j = 0; j < tokenDiv.length; j++) {
                    matrizOperaciones[i][j] = tokenDiv[j];
                }
            }
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (matrizOperaciones[i][j] != "") {
                        matrizOperaciones[i][j] = evaluarOperacion(matrizOperaciones[i][j]);
                    }
                }
            }

            for (int i = 0; i < 10; i++) {
                int valor = 0;
                if (matrizOperaciones[i][0] != "") {
                    valor = Integer.parseInt(matrizOperaciones[i][0]);
                }
                for (int j = 1; j < 10; j++) {
                    if (matrizOperaciones[i][j] != "") {
                        valor -= Integer.parseInt(matrizOperaciones[i][j]);
                    }
                }
                if (matrizOperaciones[i][0] != "") {
                    matrizOperaciones[i][0] = valor + "";
                }
            }
            int resultado = 0;
            for (int i = 0; i < 10; i++) {
                if (matrizOperaciones[i][0] != "") {
                    resultado += Integer.parseInt(matrizOperaciones[i][0]);
                }
            }
            System.out.println(operacion + " = " + resultado + "\n");
            System.out.print("¿Continuar? [S/N]: ");
            char respuesta = leer.next().charAt(0);
            if (respuesta == 'n' || respuesta == 'N') {
                break;
            }
        }
        
    }

    public static String evaluarOperacion(String operacion) {

        while (true) {
            String numero1 = "";
            String numero2 = "";

            //verdadero para * y falso para /
            boolean operador = true;

            //verdadero para numero1 y falso para numero2
            boolean banderaNumero = true;
            boolean senuelo = true;
            int banderaEvaluar = 0;

            for (int i = 0; i < operacion.length(); i++) {
                if (operacion.charAt(i) == '*' || operacion.charAt(i) == '/') {
                    banderaNumero = false;
                    banderaEvaluar++;
                    if (banderaEvaluar == 2) {
                        banderaNumero = true;
                        int valor;
                        if (operador) {
                            valor = Integer.parseInt(numero1) * Integer.parseInt(numero2);
                        } else {
                            valor = Integer.parseInt(numero1) / Integer.parseInt(numero2);
                        }
                        operacion = operacion.substring(i, operacion.length());
                        operacion = valor + operacion;
                        senuelo = false;
                        break;
                    }
                    if (operacion.charAt(i) == '*') {
                        operador = true;
                    } else {
                        operador = false;
                    }
                } else {
                    if (banderaNumero) {
                        numero1 += operacion.charAt(i);
                    } else {
                        numero2 += operacion.charAt(i);
                    }
                }
            }
            if (numOperadores(operacion) == 1 && senuelo) {
                if (operador) {
                    operacion = (Integer.parseInt(numero1) * Integer.parseInt(numero2)) + "";
                } else {
                    operacion = (Integer.parseInt(numero1) / Integer.parseInt(numero2)) + "";
                }
                break;
            }
            if (numOperadores(operacion) == 0) {
                break;
            }
        }
        return operacion;
    }

    public static int numOperadores(String operacion) {
        int numeOperadores = 0;
        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '*' || operacion.charAt(i) == '/') {
                numeOperadores++;
            }
        }
        return numeOperadores;
    }

}
