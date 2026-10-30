import java.util.Scanner;
public class SistemaVentasNuevo {
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            int opcion;
            int cantidadVentas = 0;
            int totalProductos = 0;
            int ventasEfectivo = 0;
            int ventasTarjeta = 0;
            int ventasTransferencia = 0;
            int ventasConDescuento = 0;
            int ventasMayores500 = 0;
            double totalVendido = 0;
            double totalDescuentos = 0;
            double totalIVA = 0;
            double ventaMayor = 0;
            double ventaMenor = 0;
            do {
                System.out.println("\n===== SISTEMA DE VENTAS =====");
                System.out.println("1. Registrar venta");
                System.out.println("2. Mostrar caja");
                System.out.println("3. Mostrar estadisticas");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opcion: ");
                opcion = entrada.nextInt();
                entrada.nextLine();
                switch (opcion) {
                    case 1 -> {
                        double subtotal = 0;
                        int seguir;
                        do {
                            System.out.print("\nCodigo del producto: ");
                            String codigo = entrada.nextLine();
                            System.out.print("Nombre del producto: ");
                            String nombre = entrada.nextLine();
                            System.out.print("Precio del producto: Q");
                            double precio = entrada.nextDouble();
                            System.out.print("Cantidad: ");
                            int cantidad = entrada.nextInt();
                            entrada.nextLine();
                            double importe = precio * cantidad;
                            subtotal += importe;
                            totalProductos += cantidad;
                            System.out.printf("Producto: %s | Codigo: %s | Importe: Q%.2f%n",
                                    nombre, codigo, importe);
                            System.out.print("¿Desea agregar otro producto? (1=Si, 0=No): ");
                            seguir = entrada.nextInt();
                            entrada.nextLine();
                        } while (seguir == 1);
                        double descuento = 0;
                        if (subtotal < 200) {
                            descuento = 0;
                        } else if (subtotal < 500) {
                            descuento = subtotal * 0.05;
                        } else if (subtotal < 1000) {
                            descuento = subtotal * 0.08;
                        } else {
                            descuento = subtotal * 0.10;
                        }
                        double subtotalConDescuento = subtotal - descuento;
                        double iva = subtotalConDescuento * 0.12;
                        double total = subtotalConDescuento + iva;
                        System.out.printf("%nSubtotal: Q%.2f%n", subtotal);
                        System.out.printf("Descuento: Q%.2f%n", descuento);
                        System.out.printf("IVA (12%%): Q%.2f%n", iva);
                        System.out.printf("TOTAL A PAGAR: Q%.2f%n", total);
                        System.out.println("\nForma de pago:");
                        System.out.println("1. Efectivo");
                        System.out.println("2. Tarjeta");
                        System.out.println("3. Transferencia");
                        System.out.print("Seleccione: ");
                        int formaPago = entrada.nextInt();
                        switch (formaPago) {
                            case 1 -> {
                                ventasEfectivo++;
                                double recibido;
                                do {
                                    System.out.print("Dinero recibido: Q");
                                    recibido = entrada.nextDouble();
                                    if (recibido < total) {
                                        System.out.println("Dinero insuficiente. Intente de nuevo.");
                                    }
                                } while (recibido < total);
                                double cambio = recibido - total;
                                System.out.printf("Cambio: Q%.2f%n", cambio);
                        }
                            case 2 -> {
                                ventasTarjeta++;
                                System.out.println("Pago con tarjeta registrado.");
                        }
                            case 3 -> {
                                ventasTransferencia++;
                                System.out.println("Transferencia registrada.");
                        }
                            default -> System.out.println("Forma de pago no valida.");
                        }
                        entrada.nextLine();
                        cantidadVentas++;
                        totalVendido += total;
                        totalDescuentos += descuento;
                        totalIVA += iva;
                        if (descuento > 0) {
                            ventasConDescuento++;
                        }
                        if (total > 500) {
                            ventasMayores500++;
                        }
                        if (cantidadVentas == 1) {
                            ventaMayor = total;
                            ventaMenor = total;
                        } else {
                            if (total > ventaMayor) {
                                ventaMayor = total;
                            }
                            if (total < ventaMenor) {
                                ventaMenor = total;
                            }
                        }
                        System.out.println("\nVenta registrada correctamente.");
                    }
                    case 2 -> {
                        System.out.printf("%n===== CAJA =====%n");
                        System.out.printf("Total vendido: Q%.2f%n", totalVendido);
                        System.out.println("Cantidad de ventas: " + cantidadVentas);
                        System.out.printf("Total de descuentos: Q%.2f%n", totalDescuentos);
                        System.out.printf("Total de IVA: Q%.2f%n", totalIVA);
                    }
                    case 3 -> {
                        System.out.println("\n===== ESTADISTICAS =====");
                        System.out.println("Cantidad de ventas: " + cantidadVentas);
                        System.out.println("Productos vendidos: " + totalProductos);
                        System.out.printf("Total vendido: Q%.2f%n", totalVendido);
                        System.out.printf("Total descuentos: Q%.2f%n", totalDescuentos);
                        System.out.printf("Total IVA: Q%.2f%n", totalIVA);
                        System.out.println("Ventas en efectivo: " + ventasEfectivo);
                        System.out.println("Ventas con tarjeta: " + ventasTarjeta);
                        System.out.println("Ventas por transferencia: " + ventasTransferencia);
                        System.out.println("Ventas con descuento: " + ventasConDescuento);
                        System.out.println("Ventas mayores de Q500: " + ventasMayores500);
                        if (cantidadVentas > 0) {
                            double promedio = totalVendido / cantidadVentas;
                            System.out.printf("Promedio por venta: Q%.2f%n", promedio);
                            System.out.printf("Venta mayor: Q%.2f%n", ventaMayor);
                            System.out.printf("Venta menor: Q%.2f%n", ventaMenor);
                        } else {
                            System.out.println("Todavia no hay ventas registradas.");
                        }
                    }
                    case 4 -> System.out.println("Gracias por utilizar el sistema.");
                    default -> System.out.println("Opcion no valida.");
                }
            } while (opcion != 4);
        }
    }
}