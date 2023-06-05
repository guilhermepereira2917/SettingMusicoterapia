package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class BigDecimalUtils {

    private static NumberFormat bigDecimalNumberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

    public static String getValorFormatado(BigDecimal bigDecimal) {
        bigDecimalNumberFormat.setMinimumFractionDigits(2);

        return "R$ " + bigDecimalNumberFormat.format(bigDecimal);
    }

}
