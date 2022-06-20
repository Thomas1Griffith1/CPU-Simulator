package CPSC3300;

public class ALU_Or {



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

    public static int or(int one, int two)
    {
        return one | two;
    }



}
