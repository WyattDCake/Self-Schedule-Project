import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        UserInterface UI = new UserInterface(scanner);
        UI.start();
    }
}
