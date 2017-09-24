package com.fastdeliveryservice.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Martina
 */
public abstract class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    public static void logInfo(final Object obj) {
        logger.info(String.format("%s", obj));
    }

    public static void logWarn(final Object obj) {
        logger.warn(String.format("%s", obj));
    }

    public static void logDebug(final Object obj) {
        logger.debug(String.format("%s", obj));
    }

    public static void logError(final Object obj) {
        logger.error(String.format("%s", obj));
    }

    public static void logException(final Exception e) {
        logError(e);
        Arrays.asList(e.getStackTrace()).forEach(element -> logError(element));
    }

    public static Date getCurrentDate() {
        Calendar calendar = getCalendar();
        return calendar.getTime();
    }

    public static Calendar getCalendar() {
        final TimeZone tz = TimeZone.getTimeZone("Europe/Rome");
        final Locale loc = Locale.ITALIAN;

        return Calendar.getInstance(tz, loc);
    }

}