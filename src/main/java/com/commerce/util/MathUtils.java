package com.commerce.util;

import java.text.DecimalFormat;

public class MathUtils {

    public double formatdouble(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        String formattedNumber = decimalFormat.format(number);
        double formattedDouble = Double.parseDouble(formattedNumber);
        return  formattedDouble;
    }
}
