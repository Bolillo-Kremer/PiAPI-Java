package PiAPI.Helpers;

/**
 * Provides edge values
 */
public class Edge {
    private static String edgeRising = "rising";
    private static String edgeFalling = "falling";
    private static String edgeBoth = "both";

    /**
     * @return Rising edge value
     */
    public static String rising() {
        return edgeRising;
    }

    /**
     * @return Falling edge value
     */
    public static String falling() {
        return edgeFalling;
    }

    /**
     * @return Both edge value
     */
    public static String both() {
        return edgeBoth;
    }
}