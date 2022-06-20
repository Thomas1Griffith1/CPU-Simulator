package CPSC3300;


import java.util.*;

public class Data {


    private int[] data_Memory;



    //initialization data values to default zero and create data array
    public Data(int size) {
        data_Memory = new int[size];
        setdata();

    }

    /**
     * set data values
     * @param index register number
     * @param value new current register value
     */
    public void set(int index, int value)
    {
        data_Memory[index] = value;
    }

    /**
     *
     * @param index register number
     * @return value present under register number at this time
     */
    public int get(int index)
    {
        return data_Memory[index];
    }


    /**
     * set all values to default and new register file
     */
    private void setdata()
    {

        for(int i = 0; i < data_Memory.length; i++)
        {
            data_Memory[i] = 0;
        }

    }


}
