import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class SistemaInventario {

    static ArrayList<Producto> inventario = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenuDespuesInicioSesion() {
        int opcion;
        limpiarConsola();
        do {

            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    limpiarConsola();
                    agregarProducto();
                    break;
                case 2:
                    limpiarConsola();
                    actualizarProducto();
                    break;
                case 3:
                    limpiarConsola();
                    eliminarProducto();
                    break;
                case 4:
                    limpiarConsola();
                    buscarProducto();
                    break;
                case 5:
                    limpiarConsola();
                    realizarVenta();
                    break;
                case 6:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, elija una opción válida.");
            }
        } while (opcion != 6);
    }

    static void mostrarMenu() {

        System.out.println("Menú Principal");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Actualizar Producto");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Buscar Producto");
        System.out.println("5. Realizar Venta");
        System.out.println("6. Salir");
    }

    static int leerOpcion() {
        int opcion = 0;
        boolean opcionValida = false;

        do {
            try {
                System.out.print("Ingrese su opción: ");
                opcion = scanner.nextInt();
                opcionValida = true;
            } catch (InputMismatchException e) {
                // Limpiar el búfer del scanner
                scanner.nextLine();
                System.out.println("¡Error! Ingrese un número válido.");
            }
        } while (!opcionValida);

        return opcion;
    }

    static void agregarProducto() {
        scanner.nextLine(); // Limpiar el buffer

        // Variables para almacenar los datos del nuevo producto
        String codigo;
        String nombre;
        int cantidad;
        double precio;
        int categoriaIndex;

        // Pedir al usuario que ingrese un código único
        do {
            System.out.print("Código del producto: ");
            codigo = scanner.nextLine();

            if (buscarProductoPorCodigo(codigo) != null) {
                System.out.println("¡Error! El código ya está en uso. Intente con otro.");
            }
        } while (buscarProductoPorCodigo(codigo) != null);

        // Pedir al usuario que ingrese el nombre del producto
        System.out.print("Nombre del producto: ");
        nombre = scanner.nextLine();

        // Validar y pedir al usuario que ingrese la cantidad del producto
        do {
            System.out.print("Cantidad de ingreso de producto: ");
            try {
                cantidad = scanner.nextInt();
                if (cantidad <= 0) {
                    System.out.println("¡Error! Ingrese un número positivo para la cantidad.");
                }
            } catch (InputMismatchException e) {
                // Limpiar el búfer del scanner
                scanner.nextLine();
                System.out.println("¡Error! Ingrese un número válido para la cantidad.");
                cantidad = -1; // Establecer un valor inválido para que el bucle continúe
            }
        } while (cantidad <= 0);

        // Validar y pedir al usuario que ingrese el precio del producto
        do {
            System.out.print("Precio del producto: ");
            try {
                precio = scanner.nextDouble();
                if (precio <= 0) {
                    System.out.println("¡Error! Ingrese un número positivo para el precio.");
                }
            } catch (InputMismatchException e) {
                // Limpiar el búfer del scanner
                scanner.nextLine();
                System.out.println("¡Error! Ingrese un número válido para el precio.");
                precio = -1; // Establecer un valor inválido para que el bucle continúe
            }
        } while (precio <= 0);

        // Mostrar categorías disponibles y pedir al usuario que seleccione una
        mostrarCategorias();
        int numCategorias = Categoria.values().length;

        do {
            System.out.print("Seleccione la categoría (número): ");
            while (!scanner.hasNextInt()) {
                System.out.println("¡Error! Ingrese un número válido para la categoría.");
                scanner.nextLine(); // Limpiar el buffer
                System.out.print("Seleccione la categoría (número): ");
            }
            categoriaIndex = scanner.nextInt();

            if (categoriaIndex < 1 || categoriaIndex > numCategorias) {
                System.out.println("¡Error! Seleccione una categoría válida.");
            }
        } while (categoriaIndex < 1 || categoriaIndex > numCategorias);

        // Crear el nuevo producto y agregarlo al inventario
        limpiarConsola();
        Categoria[] categorias = Categoria.values();
        String categoriaSeleccionada = categorias[categoriaIndex - 1].toString();

        Producto nuevoProducto = new Producto(codigo, nombre, cantidad, precio, categoriaSeleccionada);
        inventario.add(nuevoProducto);

        // Mostrar mensaje de éxito
        System.out.println("Producto agregado con éxito:");
        System.out.println(nuevoProducto);
    }

    public enum Categoria {
        ELECTRONICA,
        ROPA,
        ALIMENTOS,
        // Agrega otras categorías según tus necesidades
    }

    static void mostrarCategorias() {
        System.out.println("Categorías disponibles:");
        Categoria[] categorias = Categoria.values();
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }
    }

    static void actualizarProducto() {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el código del producto a actualizar: ");
        String codigo = scanner.nextLine();

        Producto producto = buscarProductoPorCodigo(codigo);

        if (producto != null) {
            System.out.println("Producto encontrado. ¿Qué campo desea actualizar?");
            System.out.println("1. Nombre");
            System.out.println("2. Código");
            System.out.println("3. Cantidad");
            System.out.println("4. Precio");
            System.out.println("5. Categoría");
            System.out.println("6. Todo");
            System.out.println("7. Cancelar");

            int opcionCampo = leerOpcion();

            switch (opcionCampo) {
                case 1:
                    actualizarNombre(producto);
                    break;
                case 2:
                    actualizarCodigo(producto);
                    break;
                case 3:
                    actualizarCantidad(producto);
                    break;
                case 4:
                    actualizarPrecio(producto);
                    break;
                case 5:
                    actualizarCategoria(producto);
                    break;
                case 6:
                    actualizarTodo(producto);
                    break;
                case 7:
                    System.out.println("Operación cancelada.");
                    break;
                default:
                    System.out.println("Opción incorrecta. No se realizaron cambios.");
            }
        } else {
            System.out.println("¡Error! Producto no encontrado.");
        }
    }

    static void actualizarNombre(Producto producto) {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Nuevo nombre del producto: ");
        String nuevoNombre = scanner.nextLine();
        producto.setNombre(nuevoNombre);
        System.out.println("Nombre actualizado con éxito.");
    }

    static void actualizarCodigo(Producto producto) {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Nuevo código del producto: ");
        String nuevoCodigo = scanner.nextLine();

        // Validar si el nuevo código ya está en uso
        if (buscarProductoPorCodigo(nuevoCodigo) != null) {
            System.out.println("¡Error! El nuevo código ya está en uso. No se realizaron cambios.");
        } else {
            producto.setCodigo(nuevoCodigo);
            System.out.println("Código actualizado con éxito.");
        }
    }

    static void actualizarCantidad(Producto producto) {
        System.out.print("Nueva cantidad del producto: ");
        int nuevaCantidad = scanner.nextInt();
        producto.setCantidad(nuevaCantidad);
        System.out.println("Cantidad actualizada con éxito.");
    }

    static void actualizarPrecio(Producto producto) {
        System.out.print("Nuevo precio del producto: ");
        double nuevoPrecio = scanner.nextDouble();
        producto.setPrecio(nuevoPrecio);
        System.out.println("Precio actualizado con éxito.");
    }

    static void actualizarCategoria(Producto producto) {
        scanner.nextLine(); // Limpiar el buffer
        // Mostrar categorías disponibles y pedir al usuario que seleccione una
        mostrarCategorias();
        int numCategorias = Categoria.values().length;

        int categoriaIndex;
        do {
            System.out.print("Seleccione la nueva categoría (número): ");
            while (!scanner.hasNextInt()) {
                System.out.println("¡Error! Ingrese un número válido para la categoría.");
                scanner.nextLine(); // Limpiar el buffer
                System.out.print("Seleccione la nueva categoría (número): ");
            }
            categoriaIndex = scanner.nextInt();

            if (categoriaIndex < 1 || categoriaIndex > numCategorias) {
                System.out.println("¡Error! Seleccione una categoría válida.");
            }
        } while (categoriaIndex < 1 || categoriaIndex > numCategorias);

        Categoria[] categorias = Categoria.values();
        String nuevaCategoria = categorias[categoriaIndex - 1].toString();
        producto.setCategoria(nuevaCategoria);

        System.out.println("Categoría actualizada con éxito.");
    }

    static void actualizarTodo(Producto producto) {
        actualizarNombre(producto);
        actualizarCodigo(producto);
        actualizarCantidad(producto);
        actualizarPrecio(producto);
        actualizarCategoria(producto);

        System.out.println("Producto actualizado con éxito.");
    }

    static void eliminarProducto() {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();

        Producto producto = buscarProductoPorCodigo(codigo);

        if (producto != null) {
            // Mostrar detalles del producto
            System.out.println("Detalles del producto a eliminar:");
            System.out.println(producto);

            // Preguntar al usuario si desea eliminar el producto
            System.out.print("¿Desea eliminar este producto? (S/N): ");
            char respuesta = scanner.next().charAt(0);

            // Validar la respuesta
            if (respuesta == 'S' || respuesta == 's') {
                // Eliminar el producto
                inventario.remove(producto);
                System.out.println("Producto eliminado con éxito.");
            } else if (respuesta == 'N' || respuesta == 'n') {
                System.out.println("Operación cancelada. El producto no ha sido eliminado.");
            } else {
                System.out.println("Respuesta no válida. Operación cancelada.");
            }
        } else {
            System.out.println("¡Error! Producto no encontrado.");
        }
    }

    static void buscarProducto() {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el código o nombre del producto a buscar: ");
        String clave = scanner.nextLine();

        Producto producto = buscarProductoPorCodigo(clave);

        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        } else {
            System.out.println("¡Error! Producto no encontrado.");
        }
    }

    static void realizarVenta() {
        double totalCompra = 0;

        do {
            // Mostrar productos disponibles para la venta
            mostrarInventario();

            System.out.print("Ingrese el código del producto a vender: ");
            String codigo = scanner.next();

            Producto producto = buscarProductoPorCodigo(codigo);

            if (producto != null) {
                System.out.println("Detalles del producto seleccionado:");
                System.out.println(producto);

                System.out.print("Ingrese la cantidad a vender: ");
                int cantidadVenta = scanner.nextInt();

                if (cantidadVenta > 0 && cantidadVenta <= producto.getCantidad()) {
                    // Almacena la cantidad inicial antes de realizar la venta

                    // Actualizar la cantidad en el inventario
                    producto.setCantidad(producto.getCantidad() - cantidadVenta);

                    double subtotal = cantidadVenta * producto.getPrecio();
                    double iva = subtotal * 0.15;
                    double total = subtotal + iva;
                    totalCompra += total;

                    // Mostrar detalles de la venta
                    System.out.println("\nDetalles de la venta:");
                    System.out.println("Producto: " + producto.getNombre());
                    System.out.println("Cantidad vendida: " + cantidadVenta);
                    System.out.println("Precio unitario: $" + producto.getPrecio());
                    System.out.println("Subtotal: $" + subtotal);
                    System.out.println("IVA (15%): $" + iva);
                    System.out.println("Total: $" + total);

                    // Preguntar si desea realizar otra compra
                    System.out.print("\n¿Desea realizar otra compra? (S/N): ");
                } else {
                    System.out.println("¡Error! Cantidad no válida o insuficiente en inventario.");
                }
            } else {
                System.out.println("¡Error! Producto no encontrado.");
            }
        } while (scanner.next().equalsIgnoreCase("S"));

        // Mostrar factura final
        System.out.println("\nFactura final de la compra:");
        System.out.println("Total de la compra: $" + totalCompra);
        System.out.println("¡Gracias por su compra!");
    }

    static void mostrarInventario() {
        System.out.println("Inventario actual:");
        for (Producto producto : inventario) {
            System.out.println(producto.getCodigo() + " - " + producto.getNombre() + " - Cantidad: "
                    + producto.getCantidad() + " - Precio: $" + producto.getPrecio());
        }
    }

    static void mostrarFactura(int cantidadInicial) {
        System.out.println("\nFactura de la compra:");
        double totalCompra = 0;

        for (Producto producto : inventario) {
            if (producto.getCantidad() < producto.getCantidadInicial()) {
                int unidadesCompradas = producto.getCantidadInicial() - producto.getCantidad();
                double subtotalProducto = unidadesCompradas * producto.getPrecio();
                totalCompra += subtotalProducto;

                System.out.println("Producto: " + producto.getNombre());
                System.out.println("Unidades compradas: " + unidadesCompradas);
                System.out.println("Subtotal: $" + subtotalProducto);
                System.out.println("------------------------");
            }
        }

        System.out.println("Total de la compra: $" + totalCompra);
        System.out.println("¡Gracias por su compra!");
    }

    static Producto buscarProductoPorCodigo(String codigo) {
        for (Producto producto : inventario) {
            if (producto.getCodigo().equals(codigo) || producto.getNombre().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    static void limpiarConsola() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // En sistemas Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // En sistemas UNIX y Linux
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir al intentar limpiar la consola
            e.printStackTrace();
        }
    }
}

class Producto {
    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private String categoria;
    private int cantidadInicial;

    public Producto(String codigo, String nombre, int cantidad, double precio, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidadInicial = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\nNombre: " + nombre + "\nCantidad: " + cantidad +
                "\nPrecio: $" + precio + "\nCategoría: " + categoria;
    }
}