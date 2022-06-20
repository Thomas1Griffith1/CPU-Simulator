package CPSC3300;

public class Program_Counter {

    int pc_val;

    public Program_Counter()
    {
        pc_val = 0;

    }

    public void pc_increment()
    {
        pc_val = ALU_Add.PC_Add(pc_val);
    }

    public void setPc_val(int val)
    {
        pc_val = val;
    }

    public int getPc_val()
    {
        return pc_val;
    }
}
