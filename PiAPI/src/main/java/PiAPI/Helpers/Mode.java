package PiAPI.Helpers;

/**
 * Gets Board Mode values
 */
public class Mode {
    private static String bcmMode = "bcm";
    private static String boardMode = "board";

    /**
     * @return BCM value
     */
    public static String BCM() {
        return bcmMode;
    }

    /**
     * @return Board value
     */
    public static String board() {
        return boardMode;
    }
}