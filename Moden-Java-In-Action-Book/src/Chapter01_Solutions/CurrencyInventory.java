package Chapter01_Solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class CurrencyInventory {

    public static void main(String... args) {

        List<Currency> currencies = new ArrayList<>();

        currencies.add(new Currency("Dollars", 233));
        currencies.add(new Currency("Rupees", 2000));
        currencies.add(new Currency("Dollar", 4000));
        currencies.add(new Currency("rupees", 3244));
        currencies.add(new Currency("Yen", 4543));

        // Requirement is to group all the expensive (value > 1000) currencies

        // Old method
        Map<String, List<Currency>> expensiveCurrencies = getExpensiveCurrencies(currencies);

        // New method using Java8
        Map<String, List<Currency>> expensiveCurrencies2 = currencies.stream().
                                                            filter(curr -> curr.getValue() > 1000).
                                                            collect(groupingBy(Currency::getCurrencyType));

    }

    // Old and verbose method
    public static Map<String, List<Currency>> getExpensiveCurrencies(List<Currency> currencies) {

        Map<String, List<Currency>> expensiveCurrencies = new HashMap<>();

        for (Currency currency : currencies) {

            if (currency.getValue() > 1000) {
                List<Currency> groupOfSameCurrency = expensiveCurrencies.get(currency.getCurrencyType());

                if (groupOfSameCurrency == null) {
                    groupOfSameCurrency = new ArrayList<>();
                }

                groupOfSameCurrency.add(currency);

                expensiveCurrencies.put(currency.getCurrencyType(), groupOfSameCurrency);
            }
        }

        return expensiveCurrencies;
    }
}
