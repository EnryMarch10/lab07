package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final int SHORT_MONTH = 28;
    private static final int USUAL_MONTH = 30;
    private static final int LONG_MONTH = 31;

    private enum Month {
        GENNAIO("january", LONG_MONTH),
        FEBBRAIO("february", SHORT_MONTH),
        MARZO("march", LONG_MONTH),
        APRILE("april", USUAL_MONTH),
        MAGGIO("may", LONG_MONTH),
        GIUGNO("june", USUAL_MONTH),
        LUGLIO("july", LONG_MONTH),
        AGOSTO("august", LONG_MONTH),
        SETTEMBRE("september", USUAL_MONTH),
        OTTOBRE("october", LONG_MONTH),
        NOVEMBRE("november", USUAL_MONTH),
        DICEMBRE("december", LONG_MONTH);

        private final String name;
        private final int days;

        private Month(String name, int days) {
            this.name = name;
            this.days = days;
        }

        public static Month valueFromName(String month) {
            Month result = null;
            for(Month item: Month.values()) {
                if (item.toString().toLowerCase().startsWith(month.toLowerCase())) {
                    if (result != null) {
                        throw new IllegalArgumentException();
                    }
                    result = item;
                }
            }
            if (result == null) {
                throw new IllegalArgumentException();
            }
            return result;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private static class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(String arg0, String arg1) {
            return Objects.requireNonNull(Month.valueFromName(arg0)).ordinal() - Objects.requireNonNull(Month.valueFromName(arg1)).ordinal();
        }
    }
    private static class SortByDate implements Comparator<String> {
        @Override
        public int compare(String arg0, String arg1) {
            return Objects.requireNonNull(Month.valueFromName(arg0)).days - Objects.requireNonNull(Month.valueFromName(arg1)).days;
        }
    }
}
