package CPSC3300;

public class Instruction {

    private int opcode = 0;
    private int funct = 0;
    private int rd = 0;
    private int rs = 0;
    private int rt = 0;
    private int offset = 0;
    private boolean r_type = false;
    private boolean lw_type = false;
    private boolean sw_type = false;
    private boolean branch = false;


    private int rnum = 0;
    private int lw_swnum = 0;
    private int branchnum = 0;

    private boolean is_exit;
    private boolean is_nop;



    public Instruction()
    {

    }

    /**
     *
     * @param instr is the 4 byte instruction that we will be using to set all variables of the class needed
     */
    public void set_instr(byte[] instr)
    {

         opcode = instr[0];

        opcode = opcode >> 2;

        if(opcode == 0)
        {
            r_type = true;
            rs = ((instr[0] & 3) << 3) + + (instr[1] >> 5);
            rt = (instr[1]  & 31);
            rd = (instr[2] >> 3);
            funct = (instr[3] & 63);
            rnum++;

        }

        if(opcode == 35)
        {
            lw_type = true;
            rs = ((instr[0] & 3) << 3) + + (instr[1] >> 5);
            rt = (instr[1]  & 31);
            offset = instr[2] >> 8 + instr[3];
            lw_swnum++;
        }

        if(opcode == 43)
        {
            sw_type = true;
            rs = ((instr[0] & 3) << 3) + + (instr[1] >> 5);
            rt = (instr[1]  & 31);
            offset = instr[2] >> 8 + instr[3];
            lw_swnum++;


        }

        if(opcode == 4)
        {
            branch = true;
            rs = ((instr[0] & 3) << 3) + + (instr[1] >> 5);
            rt = (instr[1]  & 31);
            offset = instr[2] >> 8 + instr[3];
            branchnum++;

        }

    }





    public int get_opcode()
    {
        return opcode;
    }

    public int get_funct()
    {
        return funct;
    }

    public int get_rd()
    {
        return rd;
    }

    public int get_rs()
    {
        return rs;
    }
    public int get_rt()
    {
        return rt;
    }

    public int get_offset()
    {
        return offset;
    }

    public boolean get_r_type()
    {
        return r_type;
    }

    public boolean get_lw_type()
    {
        return lw_type;
    }

    public boolean get_sw_type()
    {
        return sw_type;
    }

    public boolean get_branch()
    {
        return branch;
    }

    public boolean get_is_exit()
    {
        return is_exit;
    }

    public boolean get_is_nop()
    {
        return is_nop;
    }

    public int get_rnum()
    {
        return rnum;
    }

    public int get_lw_swnum()
    {
        return lw_swnum;
    }

    public int get_branchnum()
    {
        return branchnum;
    }



}
