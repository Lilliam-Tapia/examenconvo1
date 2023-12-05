import java.util.Scanner;

public class UsuarioBase {
    protected static final Scanner scanner = new Scanner(System.in);

    protected static void limpiarConsola() {
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

    protected static int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("¡Error! Ingrese un número válido.");
            scanner.nextLine(); // Limpiar el buffer
        }
        return scanner.nextInt();
    }
}
