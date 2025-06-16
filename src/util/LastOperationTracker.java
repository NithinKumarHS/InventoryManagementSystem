package util;

public class LastOperationTracker {
    public static String lastOperation = "";
    public static int lastItemId = -1;
    public static String lastItemName = "";
    public static int lastQuantity = 0;
    public static double lastPrice = 0.0;

    public static void clear() {
        lastOperation = "";
        lastItemId = -1;
        lastItemName = "";
        lastQuantity = 0;
        lastPrice = 0.0;
    }
}
