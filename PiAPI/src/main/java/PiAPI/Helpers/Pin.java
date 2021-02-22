package PiAPI.Helpers;

/**
 * Provides pin and pin state values
 */
public class Pin {
    private static String pinOut = "out";
    private static String pinIn = "in";
    private static String pinAll = "*";
    private static int pinHigh = 1;
    private static int pinLow = 0;
    private static int pinToggle = -1;

    /**
     * @return Pin out value
     */
    public static String out() {
        return pinOut;
    }

    /**
     * @return Pin in value
     */
    public static String in() {
        return pinIn;
    }

    /**
     * @return All pins value
     */
    public static String all() {
        return pinAll;
    }

    /**
     * @return High pin state value
     */
    public static int high() {
        return pinHigh;
    }

    /**
     * @return Low pin state value
     */
    public static int low() {
        return pinLow;
    }

    /**
     * @return Toggle pin state value
     */
    public static int toggle() {
        return pinToggle;
    }
}
