package cs.vsu.ru.skogorev_m_a.gr12;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        List<String> text = new ArrayList<>();
        System.out.println("Введите текст: ");
        text.add(scan.nextLine());
        //List<String> text = readTextFromFile("input.txt");
        List<String> correctedText = correctText(text);
        //saveTextToFile("output.txt", correctedText);
        System.out.println(correctedText);
    }

    public static List<String> correctText(List<String> text) {
        StringBuilder sb = new StringBuilder();
        List<String> corrected = new ArrayList<>();
        char prevChar;
        boolean name;
        int wordsCount;
        for (String str : text) {
            name = false;
            prevChar = ' ';
            wordsCount = 0;
            for (char ch : str.toCharArray()) {
                if (prevChar == ' ' || prevChar == '.') {
                    if (Character.isUpperCase(ch)) {
                        name = true;
                        wordsCount++;
                    }
                    if (Character.isLowerCase(ch)) {
                        name = false;
                        wordsCount = 0;
                        sb.delete(0, sb.length());
                    }
                }
                if ((ch == ' ' || ch == '.') && wordsCount == 3) {
                    name = false;
                    wordsCount = 0;
                    sb.append(ch);
                    corrected.add(sb.toString());
                    sb.delete(0, sb.length());
                }
                if (name) {
                    sb.append(ch);
                }
                prevChar = ch;
            }
        }
        return corrected;
    }

    public static List<String> readTextFromFile(String filename) throws NullPointerException, FileNotFoundException {
        try (Scanner scan = new Scanner(new File(filename))) {
            List<String> endList = new ArrayList<>();
            while (scan.hasNext()) {
                endList.add(scan.nextLine());
            }
            return endList;
        }
    }

    public static void saveTextToFile(String filename, List<String> stringList) throws NullPointerException, FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(filename)) {
            for (String str : stringList) {
                pw.println(str);
            }
        }
    }
}
