//Team Name: Team17
//Members: Sam Allen, Ashelyn Reilly

import java.util.HexFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        HexFormat hex = HexFormat.of();
        String assemblyOutput = "";
        String machineOutput = "";


        System.out.println("""
                Input Java Code Here:
                If you want to print, type it like this: System.out.println("Your message");
                If you want to add or subtract, type it like this: result=3+5;""");
        String input = scanner.nextLine();

        if (input.toLowerCase().startsWith("system.out.println(\"") && input.endsWith("\");")) {
            String command = input.split("\"")[1];
            System.out.println(command);

            StringBuilder assemblyOutputBuilder = new StringBuilder();
            StringBuilder machineOutputBuilder = new StringBuilder();
            for (int i = 0; i < command.length(); i++){
                char c = command.charAt(i);
                int unicodeValue = (int) c;
                String hexValue = hex.toHexDigits((byte) unicodeValue);

                assemblyOutputBuilder.append("LDBA 0x00").append(hexValue).append(", i\nSTBA 0xFC16, d \n");
                machineOutputBuilder.append("D0 00 ").append(hexValue).append(" F1 FC 16 \n");
            }
            assemblyOutputBuilder.append("\nSTOP\n\n" + "\n.END");
            machineOutputBuilder.append("00 zz");
            machineOutput = machineOutputBuilder.toString();
            assemblyOutput = assemblyOutputBuilder.toString();
        } else if (input.contains("=")){
            //split up equation into key elements
            String[] equation = input.replaceAll(" ", "").split("[=+\\-;]");
            //load first number to accumulator
            assemblyOutput += "LDWA " + equation[1] + ", i\n";
            machineOutput += "C0 00 " + hex.toHexDigits(Byte.parseByte(equation[1])) + " ";
            //decide whether to add or subtract
            if (input.contains("+")){
                assemblyOutput += "ADDA ";
                machineOutput += "60 ";
            } else if (input.contains("-")){
                assemblyOutput += "SUBA ";
                machineOutput += "70 ";
            }
            //do operation on second number
            assemblyOutput += equation[2] + ", i\n";
            machineOutput += "00 " + hex.toHexDigits(Byte.parseByte(equation[2])).toUpperCase() + " ";
            //store answer in accumulator
            assemblyOutput += "STWA " + equation[0] + ", d\nSTOP\n\n" + equation[0] + ": .WORD 0\n.END";
            machineOutput += "E1 00 0A 00 00 00 zz";
        } else {
            System.out.println("    Invalid Entry:");
            System.out.println("    If you wish to print a line, type it like this:\nSystem.out.println(\"Your text\");");
            System.out.println("    If you wish to do math, type it like this:\nresult=num1±num2;\n");
        }

        System.out.println("    Assembly Code: \n" + assemblyOutput);
        System.out.println("    Machine Code: \n" + machineOutput);
    }
}
