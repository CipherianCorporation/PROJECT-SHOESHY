package com.edu.graduationproject.utils;

import java.text.DateFormat;
import java.util.Date;

public class DateUtils {
    public static String formatDateTime(Date dateOrNull) {
        return (dateOrNull == null ? null : DateFormat.getDateTimeInstance().format(dateOrNull));
    }
}
