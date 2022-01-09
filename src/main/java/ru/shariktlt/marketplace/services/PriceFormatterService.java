package ru.shariktlt.marketplace.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Сервис для форматирования цен с учетом разделителей.
 */
@Service
public class PriceFormatterService {

    private static final DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS = DecimalFormatSymbols.getInstance(new Locale("ru", "RU"));

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00", DECIMAL_FORMAT_SYMBOLS);


    public String format(BigDecimal bigDecimal) {
        return DECIMAL_FORMAT.format(bigDecimal);
    }
}
