package util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class JspUtils {
    public static String paymentFormatter(String payment) {
        switch (payment) {
            case "PIX":
                return "Pix";
            case "CARTAO_CREDITO":
                return "Cartão de crédito";
            case "CARTAO_DEBITO":
                return "Cartão de débito";
            default:
                return payment;
        }
    }

    public static String phoneFormatter(String phone) {
        if (phone.length() == 10) {
            return phone.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        } else if (phone.length() == 11) {
            return phone.replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        } else {
            return phone;
        }
    }

    public static String cpfFormatter(String input) {
        return input.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d)", "$1.$2.$3-$4");
    }

    public static String costFormatter(String input) {
        double value = Double.parseDouble(input);
        Currency currency = Currency.getInstance("BRL");
        NumberFormat currencyFormatter = DecimalFormat.getCurrencyInstance(Locale.getDefault());
        currencyFormatter.setCurrency(currency);
        currencyFormatter.setMinimumFractionDigits(2);
        currencyFormatter.setMaximumFractionDigits(2);
        return currencyFormatter.format(value);
    }
}
