package com.mycompany.factura;

import static com.mycompany.factura.Factura.leer_factura;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Clase de prueba para las funciones de Factura con pruebas de caja negra.
 *
 * @author Kevin Martinez
 * @version 1.0
 */

public class TestCajaNegra {
    /**
     * Prueba el cálculo del IVA con datos válidos.
     *
     * <p>Esta prueba verifica que la función calcular_iva de la clase Factura maneje correctamente
     * el cálculo del IVA cuando se proporciona un array de totales y la cantidad de elementos.
     * </p>
     *
     * <p>Se crea un array de totales con datos válidos y se llama a la función calcular_iva con
     * el array y la cantidad de elementos. Se espera que el resultado sea igual al IVA calculado
     * manualmente. Se utiliza un delta para manejar posibles errores de redondeo en comparaciones
     * de números decimales.</p>
     *
     * <p>Se asegura de que el resultado de la función calcular_iva sea igual al IVA esperado.</p>
     */
    @Test
    public void testCalcularIVA() {
        double[] total = {120000.0, 20000.0};

        // Se espera que el resultado sea igual al IVA calculado manualmente (120000.0 * 0.16 + 20000.0 * 0.16)
        assertEquals(22400.0, Factura.calcular_iva(total, 2), 0.01);
    }

    /**
     * Se prueba si los campos tienen validaciones.
     *
     * <p>Esta prueba verifica si la función leer_factura de la clase Factura realiza
     * las validaciones adecuadas para los campos requeridos al ingresar una factura
     * con al menos un artículo.</p>
     *
     * <p>Se simula la entrada de usuario con un artículo válido y se llama a la función
     * leer_factura con los arrays de datos correspondientes. No se esperan excepciones
     * en este caso, ya que se están proporcionando datos válidos.</p>
     *
     * <p>Puedes agregar aserciones adicionales según las validaciones específicas que
     * estés realizando en la función leer_factura.</p>
     */
    @Test
    public void testLeerFacturaConCamposRequeridos() {
        // Simula la entrada de usuario con campos requeridos
        String input = "Taladro\n2\n10000\n15\n0\n"; // Ingresar un artículo

        // Cambia System.in para que use nuestro flujo de entrada simulado
        InputStream systemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Llama a la función leer_factura
        leer_factura(new String[100], new int[100], new double[100], new double[100], new double[100], 0, "Kevin Martinez");

        // Restaurar System.in
        System.setIn(systemIn);
    }

    /**
     * Prueba el cálculo del neto con datos válidos.
     *
     * <p>Esta prueba verifica que la función calcular_neto de la clase Factura maneje correctamente
     * el cálculo del neto cuando se proporciona un array de totales, la cantidad de elementos y un
     * valor de IVA específico.</p>
     *
     * <p>Se crea un array de totales con datos válidos y se llama a la función calcular_neto con
     * el array, la cantidad de elementos y un valor de IVA. Se espera que el resultado sea igual al
     * neto calculado manualmente. Se utiliza un delta para manejar posibles errores de redondeo en
     * comparaciones de números decimales.</p>
     *
     * <p>Se asegura de que el resultado de la función calcular_neto sea igual al neto esperado.</p>
     */
    @Test
    public void testCalcularNeto() {
        double[] total = {100.0, 150.0, 200.0};

        // Se espera que el resultado sea igual al neto calculado manualmente (100.0 + 150.0 + 200.0 + (100.0 + 150.0 + 200.0) * 0.464)
        assertEquals(522.0, Factura.calcular_neto(total, 3, 72.0), 0.01);
    }


    /**
     * Prueba la lectura de factura con datos válidos.
     *
     * <p>Esta prueba verifica que la función leer_factura de la clase Factura maneje correctamente
     * la lectura de una factura con datos válidos proporcionados por el usuario.</p>
     *
     * <p>Se simula la entrada de usuario con un artículo válido y se llama a la función leer_factura
     * con los arrays de datos correspondientes. Luego, se realizan aserciones para verificar que los
     * datos almacenados en los arrays sean los esperados.</p>
     *
     * <p>Se asegura de que los datos almacenados en los arrays sean iguales a los datos proporcionados
     * por el usuario.</p>
     */
    @Test
    public void testLeerFactura() {
        // Simulamos la entrada de usuario con datos válidos
        String input = "carro\n2\n500.0\n10.0\n0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Arreglo para almacenar los datos de la factura
        String[] articulo = new String[100];
        int[] cant = new int[100];
        double[] precio = new double[100];
        double[] dcto = new double[100];
        double[] total = new double[100];
        int n = 0;

        // Llama a la función leer_factura
        leer_factura(articulo, cant, precio, dcto, total, n, "Kevin Martinez");

        // Aserciones para verificar que los datos almacenados son los esperados
        assertEquals("carro", articulo[0]);
        assertEquals(2, cant[0]);
        assertEquals(500.0, precio[0], 0.01);
        assertEquals(10.0, dcto[0], 0.01);
        assertEquals(900.0, total[0], 0.01);
    }


    /**
     * Prueba la impresión de factura con datos válidos.
     *
     * <p>Esta prueba verifica que la función imprimir_factura de la clase Factura genere la salida
     * esperada cuando se le proporcionan datos válidos para la impresión de una factura.</p>
     *
     * <p>Se redirige la salida estándar a un ByteArrayOutputStream para capturar la salida del
     * programa. Luego, se simulan datos válidos de una factura y se llama a la función
     * imprimir_factura. Se compara la salida generada con la salida esperada.</p>
     *
     * <p>Se asegura de que la salida generada sea igual a la salida esperada.</p>
     */
    @Test
    public void testImprimirFacturaConDatosValidos() {
        // Guarda la salida estándar actual
        PrintStream originalOut = System.out;

        try {
            // Redirige la salida estándar a un ByteArrayOutputStream
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Datos de prueba
            String[] articulo = {"Producto1", "Producto2"};
            int[] cant = {2, 3};
            double[] precio = {50.0, 30.0};
            double[] dcto = {10.0, 5.0};
            double[] total = {90.0, 85.5};
            int n = 2;
            double iva = 17.55;
            double neto = 193.05;

            // Llama al método imprimir_factura
            Factura.imprimir_factura(articulo, cant, precio, dcto, total, n, iva, neto);

            // Obtiene la salida del programa
            String expectedOutput = "nombre\t#\tprecio\tdcto\ttotal\n" +
                    "--------------------------------------\n" +
                    "Producto1\t2\t50.0\t10.0%\t90.0\n" +
                    "Producto2\t3\t30.0\t5.0%\t85.5\n" +
                    "--------------------------------------\n" +
                    "Total Bruto...............$175.5\n" +
                    "Total Iva.................$17.55\n" +
                    "Total Neto a pagar........$193.05\n";

            assertEquals(expectedOutput, outContent.toString());
        } finally {
            // Restaura la salida estándar original
            System.setOut(originalOut);
        }
    }
}
