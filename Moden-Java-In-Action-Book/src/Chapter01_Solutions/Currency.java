package Chapter01_Solutions;

public class Currency {

    private String currencyType;
    private int value;

    public Currency(String currencyType, int value) {
        this.currencyType = currencyType;
        this.value = value;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public int getValue() {
        return value;
    }
}
