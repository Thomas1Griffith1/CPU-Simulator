package CPSC3300;

public class Control_Instr {

    private boolean regDst = false;
    private boolean Branch = false;
    private boolean MemRead = false;
    private boolean MemtoReg = false;
    private boolean ALUop1 = false;
    private boolean ALUop2 = false;
    private boolean MemWrite = false;
    private boolean ALUsrc = false;
    private boolean RegWrite = false;


    /**
     *
     * @param instruction the current instruction from instruction memory
     *       this will determine the control of the CPU and actions taken on
     *       the instruction being passed in, sets all the boolean or (1 bit)
     *       signals the control uses
     *
     */
    public Control_Instr(Instruction instruction)
    {


        if (instruction.get_r_type())
        {
            regDst = true;
            RegWrite = true;
            ALUop2 = true;
        }


        if (instruction.get_lw_type())
        {
            MemRead = true;
            MemtoReg = true;
            RegWrite = true;
            ALUsrc = true;
        }

        if (instruction.get_sw_type())
        {
            MemWrite = true;
            ALUsrc = true;
        }

        if (instruction.get_branch())
        {
            Branch = true;
            ALUop1 = true;
        }




    }

    public boolean get_RegWrite()
    {
        return RegWrite;
    }

    public boolean get_Alusrc()
    {
        return ALUsrc;
    }

    public boolean get_MemWrite()
    {
        return MemWrite;
    }

    public boolean get_Aluop1()
    {
        return ALUop1;
    }

    public boolean get_Aluop2()
    {
        return ALUop2;
    }

    public boolean get_memRead()
    {
        return MemRead;
    }

    public boolean get_memtoReg()
    {
        return MemtoReg;
    }

    public boolean get_branch()
    {
        return Branch;
    }

    public boolean get_regDst()
    {
        return regDst;
    }

}
