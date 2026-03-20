import java.util.HexFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String assemblyOutput = "";
        String machineOutput = "";

        if (input.toLowerCase().startsWith("system.out.println(\"") && input.endsWith("\");")){
            String command = input.split("\"")[1];
            System.out.println(command);
        }

        if (input.contains("=")){
            //"answer=5+6;"
            //String[] equation = input.split("=","+","-","*","/");
        }
    }

    /* How to convert string to hex:
    HexFormat hex = HexFormat.of();
    System.out.println(hex.toHexDigits(Byte.parseByte(string)));
    */
}
