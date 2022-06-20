package CPSC3300;

/**
 * this class will take in two binary values and add them together
 * and return that value back
 */
public class ALU_Add {

    static int counter = 0;

    public static int PC_Add(int PC_val)
    { ;
        counter ++;
        return PC_val +4;
    }

    /**
     *
     * @return is the count for the number of times
     *      this class has been called
     */
    public static int get_count()
    {
        return counter;
    }

    public static int add(int one, int two)
    {
        return one + two;
    }


}
