package CPSC3300;

public class Sim_Model {


    private Program_Counter pc; // use

    private Memory_File memory_file;

    private ALU_Control ALU; // use

    private Instruction_Memory instruction_memory; // use

    private byte[] instruction_input;

    private byte[] register_values;

    private Register_File register_file; // use

    private Control_Instr control_instr; // use


    private Instruction[] instruction_decoded;

    private String outputview = "";

    private int muxval = 0;
    private int instructionnum = 0;

    private int rnum = 0;
    private int lw_swnum = 0;
    private int branchnum = 0;


    /**
     *
     * @param file_instructions is the binary encoded instructions that need to be processed
     *
     *       creates implementation of all classes that will be used in model
     */
    public Sim_Model(byte[] file_instructions)
    {
        pc = new Program_Counter();

        instruction_input = file_instructions;

        ALU = new ALU_Control();

        instruction_memory = new Instruction_Memory();

        register_file = new Register_File();

        memory_file = new Memory_File();


    }


    public void set_up_instructions()
    {
        int instr_num = instruction_input.length /4;
        int count = 0;
        instruction_decoded = new Instruction[instr_num];
        for(int x = 0; x < instruction_input.length; x= x + 4)
        {

            byte[] intr = new byte[4];
            intr[0] = instruction_input[x];
            intr[1] = instruction_input[x+1];
            intr[2] = instruction_input[x+2];
            intr[3] = instruction_input[x+3];

            Instruction instruction = new Instruction();
            instruction.set_instr(intr);

            instruction_decoded[count] = instruction;



            count++;


            //Set the register values as well


        }
    }

    /**
     * This function will process each instruction and take it through the CPU
     * and update and change everything the instruction does
     * will go through all instructions at once
     *
     * to start will just be one R-Type instruction for testing purpose
     */
    public void instruction_steps()
    {
        while((get_pc_count()/4) < instruction_decoded.length)
        {
            //this is the fetch stage of the cycle for one instruction
            //this gets the pc val and the fetches the instruction from instruction memory
            int pcCount = get_pc_count();
            Instruction instruction = instruction_decoded[pcCount / 4];
            rnum += instruction.get_rnum();
            lw_swnum += instruction.get_lw_swnum();
            branchnum += instruction.get_branchnum();


            instructionnum = pcCount/4;
            //this passes the instruction opcode to the control unit to set its bit signals for the instr.
            Control_Instr control = new Control_Instr(instruction);


            //this passes the instruction registers to the register file after seeing if reigister write is used
            int useReg_Write = mux(instruction.get_rt(), instruction.get_rd(), control.get_regDst());
            register_file.setRegisters(instruction.get_rs(), instruction.get_rt(), useReg_Write);

       //this will perform the ALU operation using the ALU control and the registers from the register file
        // this uses the control unit and mux to determine what operation will be done in the ALU state
            int val = ALU.ALU_op_value(control.get_Aluop2(), control.get_Aluop1(), instruction.get_funct());
            int muxs = mux(register_file.readData2(), register_file.writeData(), control.get_Alusrc());

            // Action the ALU takes with the two read datas it was passed
            ALU.ALU_command_control_action(val, muxs, register_file.readData1());

        //store result of the ALU command
            int result = ALU.get_result();
            boolean zero = ALU.get_zero();


        //use values of the
            int mem_address = 0;
            mem_address = memory_file.Rise_endofCycle(register_file.readData2(), result, control.get_memRead(), control.get_MemWrite());


        //Writeback
            int data_to_write = mux(ALU.get_result(), mem_address, control.get_memtoReg());
            register_file.write(control.get_RegWrite(), data_to_write);



            int pc_val = pc.getPc_val() + 4;
            int branch_val = pc_val + (instruction.get_offset() << 2);
            pc_val = mux(pc_val, branch_val, control.get_branch() && zero);
            pc.setPc_val(pc_val);


            make_message();

        }

    }

    /**
     *
     * @return the current PC count variable
     */
    public int get_pc_count()
    {
        return pc.pc_val;
    }

    /**
     *
     * @return the current ALU Add count
     */
    public int get_ALU_Add_count()
    {
        return ALU_Add.get_count();
    }


    /**
     *
     * @return View to print the instruction
     * not needed btw
     */
    public int[] get_instruction_parts()
    {
        int[] parts = new int[5];
        parts[0] = instruction_decoded[1].get_opcode();
        parts[1] = instruction_decoded[1].get_rs();
        parts[2] = instruction_decoded[1].get_rt();
        parts[3] = instruction_decoded[1].get_rd();
        parts[4] = instruction_decoded[1].get_funct();

        return parts;
    }


    /**
     *
     * @param one rd
     * @param two rt
     * @param regDst boolean on which one will be written to
     * @return the correct register to write to
     */
    public int mux(int one, int two, boolean regDst)
    {
        muxval++;
        if(regDst)
            return two;
        return one;
    }


    public String make_message()
    {
        outputview += "PC content: 0x" + pc.getPc_val()/4 + "\n";
        outputview += "Register Content: " + register_file.getAllValues() + "\n";
        outputview += "Memory Content: " +  memory_file.getAllValues() + "\n";
        outputview += "# of Cycles: " + memory_file.getCycle_val() + "\n";
        outputview += "# of memory reads: " + memory_file.getMemread() + "\n";
        outputview += "# of memory writes: " + memory_file.getMemwrite() + "\n";
        outputview += "# of Add operations: " + ALU.getAdd() + "\n";
        outputview += "# of Sub operations: " + ALU.getSub() + "\n";
        outputview += "# of And operations: " + ALU.getAnd() + "\n";
        outputview += "# of Or operations: " + ALU.getOr() + "\n";
        outputview += "# of Stl operations: " + ALU.getSlt() + "\n";

        outputview += "PC : " + pc.getPc_val()/4 + "\n";
        outputview += "Mux: " + muxval + "\n";
        outputview += "ALU: " + (ALU.getAdd() + ALU.getSub() + ALU.getAnd() + ALU.getOr() + ALU.getSlt())  + "\n";
        outputview += "Instruction Memory: " + memory_file.getCycle_val() + "\n";
        outputview += "Register file: " + memory_file.getMemread() + "\n";

        outputview += "Memory " + memory_file.getCycle_val() + "\n";
        outputview += "ALU Control " + ALU.getAlu_control_cnt() + "\n";
        outputview += "R-Type Instruction " + rnum + "\n";
        outputview += "LW-SW Type Instruction " + lw_swnum + "\n";
        outputview += "Branch Instruction " + branchnum + "\n";
        outputview += "________________________________________________________________________________\n";

        return outputview;
    }

    public String setoutput()
    {


        return outputview;



    }


}
