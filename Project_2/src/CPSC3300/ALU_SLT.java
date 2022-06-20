package CPSC3300;

public class ALU_SLT {


    static int counter = 0;

    /**
     *
     * @return is the count for the number of times
     *      this class has been called
     */
    public static int get_count()
    {
        return counter;
    }

    public static int math(int one, int two)
    {
        if(one < two)
            return 1;
        return 0;

    }

}
