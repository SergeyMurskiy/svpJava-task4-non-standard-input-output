import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sergey Murskiy on 14.12.2018.
 */

public class NonStandardInputOutput {
    public static void checkPathToDirectory(File inputDirectory) {
        if (!inputDirectory.exists()) {
            System.out.println("Неверный путь " + inputDirectory.getAbsolutePath());
            System.exit(-1);
        }
        if (!inputDirectory.isDirectory()) {
            System.out.println(inputDirectory.getAbsolutePath() + " не является директорией");
            System.exit(-1);
        }
        if (!Files.isReadable(Paths.get(inputDirectory.getAbsolutePath()))) {
            System.out.println("Чтение из директории " + inputDirectory.getAbsolutePath() + " невозможно");
            System.exit(-1);
        }
    }

    public static void checkPathToFile(File outputFile) {
        if (!outputFile.exists()) {
            System.out.println("Неверный путь " + outputFile.getAbsolutePath());
            System.exit(-1);
        }
        if (!outputFile.isFile()) {
            System.out.println(outputFile.getAbsolutePath() + " не является файлом");
            System.exit(-1);
        }
        String[] str = outputFile.getName().split("\\.");
        if (!str[str.length - 1].equals("txt")) {
            System.out.println("Файл " + outputFile.getName() + " не является текстовым файлом");
            System.exit(-1);
        }
        if (!Files.isWritable(Paths.get(outputFile.getAbsolutePath()))) {
            System.out.println("Запись в файл " + outputFile.getAbsolutePath() + " не возможна");
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов");
            System.exit(-1);
        }
        File inputDirectory = new File(args[0]);
        File outputFile = new File(args[1]);
        checkPathToDirectory(inputDirectory);
        checkPathToFile(outputFile);
        System.out.println("Директория: " + args[0]);
        System.out.println("Целевой файл: " + args[1]);
        FileWriter writer = new FileWriter(args[1]);
        File[] list = inputDirectory.listFiles();
        for (int i = 0; i < list.length; i++) {
            writer.write(list[i].getName() + "\r\n");
            writer.flush();
        }
        writer.close();
    }
}
