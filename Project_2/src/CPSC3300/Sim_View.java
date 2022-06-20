package CPSC3300;

public class Sim_View {

    public Sim_View()
    {

    }

    public void print_to_user(String text)
    {
        System.out.println(text);
    }



    public void print_pc(int pc_val)
    {
        System.out.println("the PC count is : " + pc_val);
        System.out.println();
    }


    public void print_Add_count(int count)
    {
        System.out.println("the Add count is : " + count);
        System.out.println();
    }


    public void print_instruction(int opcode, int rs, int rt, int rd, int funct)
    {
        System.out.println("opcode: " + opcode + ", rs: " + rs + ", rt: " + rt + ", rd: " + rd + ", funct: " + funct);
        System.out.println();
    }

//    public void Output()
//    {
//        System.out.println("This is the view:");
//
//        System.out.println("Content of the PC:");
//
//        System.out.println("Content of the Registers:");
//
//        System.out.println("Content of the Register Memory");
//
//        System.out.println("# of cycles: ");
//
//        System.out.println(" # of Memory Reads: ");
//
//        System.out.println("# of Memory Writes: ");
//
//        System.out.println("# of instructions: ");
//    }

}
