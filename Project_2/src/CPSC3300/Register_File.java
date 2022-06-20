package CPSC3300;

import java.util.LinkedHashMap;

public class Register_File{



    private int read_register1;
    private int read_register2;
    private int write_register;
    private Data Reg_Data;


    public Register_File()
    {

        Reg_Data= new Data(32);

    }

    /**
     *
     * @param reg1 read register one being passed in
     * @param reg2 read register two being passed in
     * @param wr possible write register being passed in
     */
    public void setRegisters(int reg1, int reg2, int wr)
    {
        read_register1 = reg1;
        read_register2 = reg2;
        write_register = wr;

    }

    //get read data from reg
    public int readData1()
    {

        return Reg_Data.get(read_register1);

    }

    //get read data from reg

    public int readData2()
    {

        return Reg_Data.get(read_register2);

    }

    public int writeData()
    {

        return Reg_Data.get(write_register);

    }


    /**
     *
     * @param ToWrite  boolean to see if you add a value to the write register
     * @param data value that will be passed into the register to write
     */
    public void write(boolean ToWrite, int data)
    {

        if(ToWrite)
        {

            set(write_register, data);

        }

    }


    /**
     *
     * @param index   position in the register value
     * @return      value of the register stored in the register file
     */
    public int get(int index)
    {

        if(index == 0)
            return 0; // $zero register


        return Reg_Data.get(index);
    }

    /**
     *
     * @param index    position in the register value
     * @param value     value of the register to store in the register file
     */
    public void set(int index, int value)
    {

        Reg_Data.set(index, value);

    }

    public String getAllValues()
    {
        String output = "";
        for(int x = 0; x < 32; x++)
        {
            output += "Register " + x + ":  " + Reg_Data.get(x) + "\n"; 
        }
        return output;
    }



}
