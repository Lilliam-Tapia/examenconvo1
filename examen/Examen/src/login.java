public class login extends UsuarioBase {

    public static void main(String[] args) {
        int intentos = 3;

        while (intentos > 0) {
            limpiarConsola();

            System.out.println("=== Sistema de Inicio de Sesión ===");
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();

            System.out.print("Contraseña: ");
            String contraseña = scanner.nextLine();

            if ("admin".equals(usuario) && "123".equals(contraseña)) {
                limpiarConsola();
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");
                Menu menu = new Menu();
                menu.mostrarMenuDespuesInicioSesion();
                break;
            } else {
                limpiarConsola();
                System.out.println("Usuario o contraseña incorrectos. Intentos restantes: " + --intentos);
                if (intentos == 0) {
                    System.out.println("Has excedido el límite de intentos. El programa se cerrará.");
                } else {
                    System.out.println("Vuelve a intentarlo.");
                    scanner.nextLine();
                }
            }
        }

        scanner.close();
    }
}
