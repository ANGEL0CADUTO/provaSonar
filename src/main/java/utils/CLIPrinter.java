package utils;

public class CLIPrinter {
    private CLIPrinter(){}

    public static void print(String message){
        System.out.print(message);
    }

    public static void println(String message){
        print(String.format("%s%n", message));
    }
}
