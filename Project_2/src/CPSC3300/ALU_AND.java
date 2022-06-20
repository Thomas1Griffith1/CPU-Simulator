package CPSC3300;

/**
 * this class will take in two binary values to be AND them together
 * then return that value
 */
public class ALU_AND {


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

    public static int and(int one, int two)
    {
        return one & two;
    }


}
