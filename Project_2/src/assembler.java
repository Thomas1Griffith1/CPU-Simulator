import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

import javax.lang.model.type.NullType;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;
import java.nio.*;
import java.io.IOException;
import java.io.*;
import java.lang.Object;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class assembler {

    public static LinkedHashMap<String,Integer> look_up_table = new LinkedHashMap<String,Integer>();


    public static void add_to_table(){
        look_up_table.put("ADD", 32);
        look_up_table.put("SUB", 34);
        look_up_table.put("AND", 36);
        look_up_table.put("OR", 37);
        look_up_table.put("SLT", 42);
        look_up_table.put("LW", 35);
        look_up_table.put("SW", 43);
        look_up_table.put("BEQ", 4);
        look_up_table.put("j", 4);
        look_up_table.put("$zero", 0);
        look_up_table.put("$at", 1);
        look_up_table.put("$v0", 2);
        look_up_table.put("$v1", 3);
        look_up_table.put("$a0", 4);
        look_up_table.put("$a1", 5);
        look_up_table.put("$a2", 6);
        look_up_table.put("$a3", 7);
        look_up_table.put("$t0", 8);
        look_up_table.put("$t1", 9);
        look_up_table.put("$t2", 10);
        look_up_table.put("$t3", 11);
        look_up_table.put("$t4", 12);
        look_up_table.put("$t5", 13);
        look_up_table.put("$t6", 14);
        look_up_table.put("$t7", 15);
        look_up_table.put("$s0", 16);
        look_up_table.put("$s1", 17);
        look_up_table.put("$s2", 18);
        look_up_table.put("$s3", 19);
        look_up_table.put("$s4", 20);
        look_up_table.put("$s5", 21);
        look_up_table.put("$s6", 22);
        look_up_table.put("$s7",23);
        look_up_table.put("$t8", 24);
        look_up_table.put("$t9", 25);
        look_up_table.put("$k0", 26);
        look_up_table.put("$k1", 27);
        look_up_table.put("$gp", 28);
        look_up_table.put("$sp", 29);
        look_up_table.put("$fp", 30);
        look_up_table.put("$ra", 31);
        for(int i = 0; i < 32; i ++)
        {
            String regis = "$R"+Integer.toString(i);
            look_up_table.put(regis,i);
        }



    }

    public static void main(String[] args)
    {




        String fileName = "input.txt";
        String line = null;
        add_to_table();

        ArrayList <String> instructions = new ArrayList<>();


        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
                instructions.add(line);


            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }



        writeOutput(instructions);
    }



    /*
     *
     * @param
     *
     * this takes the parameter(s) and converts it
     * to binary and writes it to the binary file
     */
    public static void writeOutput(ArrayList <String> instructions)
    {
        byte[] instruction;
        String outputName = "output.bin";
        String outputPath = "/Users/ThomasGriff/Documents/CPSC_3300/Project_2";

        try (

                OutputStream outputStream = new FileOutputStream(outputName);
        ) {


            int i = 0;
            while (i < instructions.size()) {
                outputStream.write(getLine(instructions.get(i)));
                i++;
            }

//            for(int x = 0; x < instructions.size(); x++)
//            {
//                string line = instructions[x];
//                outputStream.write(getLine(instructions[x]));
//            }

            outputStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    /**
     *
     * @param instruction this is the instruction format string from the "user" or input file
     *
     *   This function decides what instruction class and type is being processed
     */
    public static byte[] getLine(String instruction)
    {
        String[] R_type = {"ADD", "SUB", "AND", "OR", "SLT"};
//        String[] LoadStore = {"LW", "SW"};
//        String[] Branch = {"BEQ", ""};
        String[] splitter = instruction.split(" ");
        String instruction_type = splitter[0];
        byte[] binaryLine = new byte[4];


        if (instruction_type.equalsIgnoreCase("ADD"))
            binaryLine = R_Type(splitter, look_up_table.get("ADD"));

        if (instruction_type.equalsIgnoreCase("SUB"))
            binaryLine = R_Type(splitter, look_up_table.get("SUB"));

        if (instruction_type.equalsIgnoreCase("AND"))
            binaryLine = R_Type(splitter, look_up_table.get("AND"));

        if (instruction_type.equalsIgnoreCase("OR"))
            binaryLine = R_Type(splitter, look_up_table.get("OR"));

        if (instruction_type.equalsIgnoreCase("SLT"))
            binaryLine = R_Type(splitter, look_up_table.get("SLT"));


        if (instruction_type.equalsIgnoreCase("LW"))
            binaryLine = Load_Store_Type(splitter, look_up_table.get("LW"));

        if (instruction_type.equalsIgnoreCase("SW"))
            binaryLine = Load_Store_Type(splitter, look_up_table.get("SW"));


        if (instruction_type.equalsIgnoreCase("BEQ"))
            binaryLine = Branch_Type(splitter, "BEQ");

        if (instruction_type.equalsIgnoreCase("J"))
            binaryLine = Branch_Type(splitter, "J");


        return  binaryLine;
    }


    /**
     *
     * @param input string array of an R-type instruction
     *
     * This will get the binary related in values for each position of the R-type
     *              Instruction before calling write output to print it out to file
     */
    public static byte[] R_Type(String[] input, int funct_Val)
    {
        int type = 0 <<26;
        int rs = look_up_table.get(input[2].substring(0,input[2].length()-1)) <<21;
        int rt = look_up_table.get(input[3]) << 16;
        int rd = look_up_table.get(input[1].substring(0,input[1].length()-1)) << 11;
        int shamt = 0 << 6;

        int R_type = type| rs | rt | rd | shamt | funct_Val;





        byte[] byteInstruction;
        ByteBuffer bytebuf = ByteBuffer.allocate(4).putInt(R_type);
        byteInstruction = bytebuf.array();



        return byteInstruction;
    }





    /**
     *
     * @param input string array of an Load-Store-type instruction
     *
     * This will get the binary related in values for each position of the Load-Store-type
     *              Instruction before calling write output to print it out to file
     */
    public static byte[] Load_Store_Type(String[] input, int Type)
    {

        /*

       Check for offset and calculate address
       type easy, some with register
       address hard parse smart. Also they have commas
         */
        int type = Type <<26;
        int rt = look_up_table.get(input[1].substring(0,input[1].length()-1)) << 16;

//        this is to get the address and rs values
        int rs;
        int offset;
        String address = input[2];


        String addrString = address.substring(0,address.indexOf('('));
        rs = look_up_table.get(address.substring(address.indexOf('(')+1,address.indexOf(')'))) <<21;

        offset = Integer.decode(address.substring(0, address.indexOf('(')));

        int R_type = type | rs | rt | offset;

        System.out.println(R_type);


        byte[] byteInstruction;
        ByteBuffer bytebuf = ByteBuffer.allocate(4).putInt(R_type);
        byteInstruction = bytebuf.array();

//        writeOutput(byteInstruction);
        return byteInstruction;

    }





    /**
     *
     * @param input string array of an Branch-type instruction
     *
     * This will get the binary related in values for each position of the Branch-type
     *              Instruction before calling write output to print it out to file
     */
    public static byte[] Branch_Type(String[] input, String Type)
    {


        /*

       Check for offset and calculate address
       type easy, some with register
       address hard parse smart. Also they have commas
         */
        int type;
        int rs;
        int rt;
        int offset;
        int R_type = 0;
        if (Type.equalsIgnoreCase("BEQ"))
        {
            type = 4 <<26;
            rt = look_up_table.get(input[2].substring(0,input[2].length()-1)) << 16;
            rs = look_up_table.get(input[1].substring(0,input[1].length()-1)) << 21;
            offset = Integer.decode(input[3]);

            R_type = type | rs | rt | offset;
        }else{
            offset = Integer.decode(input[1]);
            type = 4 <<26;
            R_type = type ;//| offset;
        }




        byte[] byteInstruction;
        ByteBuffer bytebuf = ByteBuffer.allocate(4).putInt(R_type);
        byteInstruction = bytebuf.array();


        return byteInstruction;

    }

}
