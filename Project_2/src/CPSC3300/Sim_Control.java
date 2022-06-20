package CPSC3300;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 *
 *
 */
public class Sim_Control {

    public static Sim_View view;
    public static Sim_Model model;

    public static void main(String[] args)
    {


        //Demo for R-Type instruction through CPU
        String fileName = "output.bin";

        String fileName2 = "jmemory.bin";
        //This is the real file to read from when testing is done
//        String fileName = "jmemory.bin";
        String line = null;
        byte[] allBytes = new byte[2];

        ArrayList<String> instructions = new ArrayList<>();



        try (
                InputStream inputStream = new FileInputStream(fileName);
        ) {

            long fileSize = new File(fileName).length();

             allBytes = new byte[(int) fileSize];

            inputStream.read(allBytes);



        } catch (IOException ex) {
            ex.printStackTrace();
        }



        view = new Sim_View();
        model = new Sim_Model(allBytes);

        model.set_up_instructions();
        model.instruction_steps();

        view.print_to_user(model.setoutput());



    }


    public static void print_to_view()
    {
        view.print_pc(model.get_pc_count());
        view.print_Add_count(model.get_ALU_Add_count());
        int[] parts = model.get_instruction_parts();
        view.print_instruction(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }



}
