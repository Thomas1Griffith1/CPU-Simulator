package CPSC3300;

/**
 * this class will act as the ALU control and the ALU and the adder
 */
public class ALU_Control {

    private ALU_Add add;
    private ALU_AND and;
    private ALU_Or or;
    private ALU_SLT slt;
    private ALU_Sub sub;

    private int addi;
    private int andi;
    private int ori;
    private int slti;
    private int subi;

    private int alu_control_cnt = 0;

    private boolean zero = false;

    private int result = 0;



    public ALU_Control()
    {
        add = new ALU_Add();
        and = new ALU_AND();
        or = new ALU_Or();
        slt = new ALU_SLT();
        sub = new ALU_Sub();
    }

    public int get_result()
    {
        return result;
    }

    public boolean get_zero()
    {
        return zero;
    }


    //this will determine what actions to take from the control aluop booleans
    //
    public void ALU_command_control_action(int op, int data2, int data1)
    {
        zero = false;
        switch (op)
        {
            case 0:
                addi++;
                result = add.add(data1, data2);
                break;
            case 2:
                subi++;
                result = sub.sub(data1,data2);
                break;
            case 3:
                andi++;
                result = and.and(data1, data2);
                break;
            case 4:
                ori++;
                result = or.or(data1, data2);
                break;
            case 10:
                slti++;
                result = slt.math(data1,data2);
                break;
        }


    }

    /**
     *
     * @param one ALU op 1 from control
     * @param two ALU op 2 from control
     * @param funct Instruction funct value
     * @return operation int value performed in ALU
     */
    public int ALU_op_value(boolean one, boolean two, int funct)
    {
        alu_control_cnt++;
        //branch
        if(!one && !two)
            return 0;
        //load or store else r_type after
        if(two)
        {
            return 2;
        }
        int funct_Rtype_val = funct & 15;


        if (funct_Rtype_val == 0)
        {
            return 0;
        }
        if (funct_Rtype_val == 2)
        {
            return 2;
        }
        if (funct_Rtype_val == 3)
            return 3;
        if (funct_Rtype_val == 4)
            return 4;
        if (funct_Rtype_val == 10)
            return 10;

        return 0;

    }




    public boolean isZero(int one , int two)
    {
        if (one == two)
            return true;
        return false;
    }




    public int getAdd()
    {
        return addi;
    }

    public int getSub()
    {
        return subi;
    }
    public int getAnd()
    {
        return andi;
    }

    public int getOr()
    {
        return ori;
    }

    public int getSlt()
    {
        return slti;
    }


    public int getAlu_control_cnt()
    {
        return alu_control_cnt;
    }

}
