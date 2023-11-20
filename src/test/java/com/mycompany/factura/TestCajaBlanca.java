package com.mycompany.factura;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
/**
 * Clase de prueba para las funciones de Factura con pruebas de caja blanca.
 *
 * @author Kevin Martinez
 * @version 1.0
 */
public class TestCajaBlanca {
    /**
     * Prueba la lectura de factura con decisiones de usuario.
     *
     * <p>Esta prueba simula la entrada del usuario para verificar que la función
     * leer_factura de la clase Factura maneje adecuadamente las decisiones del usuario.
     * Se ingresan dos artículos con cantidades y precios, y se simula la decisión
     * de no ingresar más artículos.
     * </p>
     *
     *
     * <p>Se utiliza un flujo de entrada simulado para proporcionar la entrada del usuario,
     * y luego se restaura el flujo de entrada original.</p>
     *
     */
    @Test
    public void testLeerFacturaConDecisionDeUsuario() {
        // Simulación de entrada de usuario
        String input = "Carro\n2\n10.0\n5\n0\n"; // Decidir ingresar otro artículo
        input += "Moto\n3\n8.0\n2\n1\n"; // Decidir no ingresar más artículos

        // Cambiar System.in para que use nuestro flujo de entrada simulado
        InputStream systemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Llamada a la función leer_factura de nuestra clase Factura.java
        Factura.leer_factura(new String[100], new int[100], new double[100], new double[100], new double[100], 0, "Kevin Martinez");

        // Restaurar System.in
        System.setIn(systemIn);
    }

    /**
     * Prueba la lectura de factura con varios artículos.
     *
     * <p>Esta prueba simula la entrada del usuario para verificar que la función
     * leer_factura de la clase Factura maneje adecuadamente la entrada de varios artículos.
     * Se ingresan tres artículos con cantidades y precios, y se simula la decisión
     * de no ingresar más artículos después del tercer artículo.
     * </p>
     *
     * <p>Se utiliza un flujo de entrada simulado para proporcionar la entrada del usuario,
     * y luego se restaura el flujo de entrada original.</p>
     */
    @Test
    public void testLeerFacturaConArticulos() {

        // Simulación de entrada de usuario
        String input = "Articulo1\n2\n10.0\n5\n0\n"; // Decidir ingresar otro artículo
        input += "Articulo2\n3\n8.0\n2\n0\n"; // Decidir ingresar otro artículo
        input += "Articulo3\n1\n15.0\n10\n1\n"; // Decidir no ingresar más artículos

        // Cambiar System.in para que use nuestro flujo de entrada simulado
        InputStream systemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Llamar a la función leer_factura
        Factura.leer_factura(new String[100], new int[100], new double[100], new double[100], new double[100], 0, "Kevin Martinez");

        // Restaurar System.in
        System.setIn(systemIn);
    }

    /**
     * Prueba el cálculo del IVA con una factura vacía.
     *
     * <p>Esta prueba verifica que la función calcular_iva de la clase Factura
     * maneje correctamente el caso de una factura vacía, donde no hay elementos
     * que cont6ribuyan al cálculo del IVA.
     * </p>
     *
     * <p>Se llama a la función calcular_iva con un array vacío y una cantidad de elementos
     * igual a cero. Se espera que el resultado sea cero y se utiliza un delta para manejar
     * posibles errores de redondeo en comparaciones de números decimales.</p>
     */
    @Test
    public void testCalcularIvaConFacturaVacia() {
        // Llama a la función calcular_iva con una factura vacía
        double iva = Factura.calcular_iva(new double[0], 0);

        // Se asegura de que el resultado sea el esperado (en este caso, el iva debe ser 0)
        assertEquals(0, iva, 0.001); // Usamos un delta para manejar posibles errores de redondeo
    }

    /**
     * Prueba el cálculo del IVA con una factura que tiene artículos.
     *
     * <p>Esta prueba verifica que la función calcular_iva de la clase Factura maneje correctamente
     * el cálculo del IVA cuando se proporciona una factura con artículos y sus respectivos totales.
     * </p>
     *
     * <p>Se crea una factura con algunos artículos (representados por el array total) y se llama
     * a la función calcular_iva con la factura. Luego se calcula manualmente el IVA esperado
     * sumando los totales de los artículos y aplicando la tasa de IVA (0.16). Se utiliza un delta
     * para manejar posibles errores de redondeo en comparaciones de números decimales.</p>
     *
     * <p>Se asegura de que el resultado de la función calcular_iva sea igual al IVA esperado.</p>
     */
    @Test
    public void testCalcularIvaFacturaConArticulos() {
        // Crea una factura con algunos artículos
        double[] total = {200.0, 3000.0, 15000.0};

        // Llamada a la función calcular_iva
        double iva = Factura.calcular_iva(total, total.length);

        // Se calcula el iva manualmente para verificar
        double ivaEsperado = (200.0 + 3000.0 + 15000.0) * 0.16;

        // Nos aseguramos de que el resultado sea el esperado
        assertEquals(ivaEsperado, iva, 0.001);
    }

    /**
     * Prueba el cálculo del neto con una factura vacía.
     *
     * <p>Esta prueba verifica que la función calcular_neto de la clase Factura maneje
     * correctamente el cálculo del neto cuando se proporciona una factura vacía (sin artículos)
     * y un valor de IVA igual a 0.
     * </p>
     *
     * <p>Se llama a la función calcular_neto con un array vacío de totales, una cantidad de elementos
     * igual a cero y un valor de IVA igual a 0. Se espera que el resultado sea 0 y se utiliza un delta
     * para manejar posibles errores de redondeo en comparaciones de números decimales.</p>
     *
     * <p>Se asegura de que el resultado de la función calcular_neto sea igual a 0.</p>
     */
    @Test
    public void testCalcularNetoConFacturaVacia() {
        // Llamar a la función calcular_neto con una factura vacía y iva 0
        double neto = Factura.calcular_neto(new double[0], 0, 0);

        // Asegurarse de que el resultado sea el esperado (en este caso, el neto debe ser 0)
        assertEquals(0, neto, 0.001); // Usamos un delta para manejar posibles errores de redondeo
    }

    /**
     * Prueba el cálculo del neto con una factura que contiene artículos.
     *
     * <p>Esta prueba verifica que la función calcular_neto de la clase Factura maneje
     * correctamente el cálculo del neto cuando se proporciona una factura con artículos y
     * un valor de IVA específico.
     * </p>
     *
     * <p>Se crea una factura con algunos artículos (representados por el array total) y un valor de IVA.
     * Luego, se llama a la función calcular_neto con la factura. Se calcula manualmente el neto esperado
     * sumando los totales de los artículos y el valor de IVA. Se utiliza un delta para manejar posibles
     * errores de redondeo en comparaciones de números decimales.</p>
     *
     * <p>Se asegura de que el resultado de la función calcular_neto sea igual al neto esperado.</p>
     */
    @Test
    public void testCalcularNetoFacturaConArticulos() {
        // Crea una factura con algunos artículos
        double[] total = {250000.0, 30000.0, 150000.0};
        double iva = 5.0;

        // Llama a la función calcular_neto
        double neto = Factura.calcular_neto(total, total.length, iva);

        // Calcula el neto manualmente para verificar
        double netoEsperado = (250000.0 + 30000.0 + 150000.0) + 5.0;

        // Se asegura de que el resultado sea el esperado
        assertEquals(netoEsperado, neto, 0.001);
    }


    /**
     * Prueba de la impresión de una factura con artículos.
     *
     * <p>Esta prueba verifica que la función imprimir_factura de la clase Factura genere
     * la salida esperada cuando se proporciona una factura con artículos, cantidades,
     * precios, descuentos, totales, IVA y neto.</p>
     *
     * <p>Se crea una factura con algunos artículos y se simula la salida de la función
     * imprimir_factura utilizando un ByteArrayOutputStream para capturar la salida.
     * Luego, se compara la salida generada con la salida esperada y se realiza la
     * verificación mediante assertEquals.</p>
     */
    @Test
    public void testImprimirFacturaConArticulos() {
        // Crea una factura con algunos artículos
        String[] articulo = {"Martillo", "Portatil"};
        int[] cant = {2, 3};
        double[] precio = {1000.0, 800.0};
        double[] dcto = {5.0, 2.0};
        double[] total = {1900.0, 2352.0};
        double iva = 680.32;
        double neto = 4932.32;

        // Simula la salida de la función imprimir_factura con la factura creada
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Llama a la función imprimir_factura con la factura creada
        Factura.imprimir_factura(articulo, cant, precio, dcto, total, total.length, iva, neto);

        // Restaurar System.out
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Asegurarse de que la salida sea la esperada
        String expectedOutput = """
            nombre\t#\tprecio\tdcto\ttotal
            --------------------------------------
            Martillo\t2\t1000.0\t5.0%\t1900.0
            Portatil\t3\t800.0\t2.0%\t2352.0
            --------------------------------------
            Total Bruto...............$4252.0
            Total Iva.................$680.32
            Total Neto a pagar........$4932.32
            """;
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }
}