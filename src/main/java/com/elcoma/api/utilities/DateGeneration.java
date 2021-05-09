package com.elcoma.api.utilities;

import java.util.Calendar;
import java.util.Date;

public class DateGeneration {
    public static Date getDateAfterMonths(int months) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }
}
