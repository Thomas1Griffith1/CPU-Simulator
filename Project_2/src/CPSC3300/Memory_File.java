package CPSC3300;

public class Memory_File {


    private Data memory_Data;

    private int cycle_val = 0;

    private int memwrite = 0;

    private int memread = 0;



    public Memory_File() {
        memory_Data = new Data(500);
    }

    public int Rise_endofCycle(int memory_address, int write_data, boolean memRead, boolean memWrite)
    {
        if(memWrite)
        {
            memory_Data.set(memory_address, write_data);
            memwrite++;
        }

        if(memRead)
        {
            memread++;
            return memory_Data.get(memory_address);

        }

        cycle_val ++;

        return 0;
    }

    public int getCycle_val()
    {
        return cycle_val;
    }

    public int getMemwrite()
    {
        return memwrite;
    }

    public int getMemread()
    {
        return memread;
    }

    public  void setDefault()
    {


    }


    public String getAllValues()
    {
        String output = "";
        for(int x = 0; x < 32; x++)
        {
            output += "Memory Address " + x + ":  " + memory_Data.get(x) + "\n";
        }
        return output;
    }



}
