package kz.ilotterytea.maxon;

import kz.ilotterytea.maxon.items.PurchasableItem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Some constants for the app.
 * @since a_1.0
 */
public class MaxonConsts {
    // Application:
    public static final String APP_NAME = "Maxon Petting Simulator";
    public static final String APP_VER = "pocket-a_1.0";
    public static final String[] APP_DEV = {"ilotterytea"};
    public static final String[] APP_CONT = {"GreDDySS", "Enotegg_", "Saopin_", "NameIsNamesake"};

    // Formatters:
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###.##");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.US);

    // Other:
    public static final long START_TIME = System.currentTimeMillis();
}
