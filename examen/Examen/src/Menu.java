
public class Menu extends UsuarioBase {

    public void mostrarMenuDespuesInicioSesion() {
        int opcion;
        do {
            limpiarConsola();
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 6);
    }

    private void mostrarMenu() {
        System.out.println("Menú Principal");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Actualizar Producto");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Buscar Producto");
        System.out.println("5. Realizar Venta");
        System.out.println("6. Salir");
    }


    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
            limpiarConsola();
                Inventario.agregarProducto();
                break;
            case 2:
            limpiarConsola();
                Inventario.actualizarProducto();
                break;
            case 3:
            limpiarConsola();
                Inventario.eliminarProducto();
                break;
            case 4:
            limpiarConsola();
                Inventario.buscarProducto();
                break;
            case 5:
            limpiarConsola();
                Inventario.realizarVenta();
                break;
            case 6:
                System.out.println("Saliendo del programa. ¡Hasta luego!");
                break;
            default:
                System.out.println("Opción incorrecta. Por favor, elija una opción válida.");
        }
    }
    
}

