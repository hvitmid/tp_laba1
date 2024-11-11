import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем у пользователя название приложения
        System.out.print("Введите название приложения: ");
        String appName = scanner.nextLine();

        if (isWorking(appName)) {
            System.out.println(appName + " работает.");
        } else {
            System.out.println(appName + " не работает.");
        }
    }

    private static boolean isWorking(String appName) {
        try {
            String command = "tasklist";
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            List<String> processes = reader.lines()
                    .filter(line -> line.toLowerCase().contains(appName.toLowerCase()))
                    .collect(Collectors.toList());
            return !processes.isEmpty();
        } catch (IOException e) {
            System.out.println("Ошибка при проверке приложений: " + e.getMessage());
            return false;
        }
    }
}