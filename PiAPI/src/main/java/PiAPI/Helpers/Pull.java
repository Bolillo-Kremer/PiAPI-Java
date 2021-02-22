package PiAPI.Helpers;

/**
 * Provides pull values
 */
public class Pull {
    private static String pullUp = "up";
    private static String pullDown = "down";

    /**
     * @return Pull up value
     */
    public static String up() {
        return pullUp;
    } 

    /**
     * @return Pull down value
     */
    public static String down() {
        return pullDown;
    }
}
