package com.github.example.model;

/**
 * Created by hardik on 30/12/17.
 */

public class Filter {
    private int languageIndex;
    private int numberOfForksIndex;
    private int licenseIndex;
    private int searchInIndex;
    private String orderBy;
    private String sortBy;
    private String fromDate;
    private String toDate;


    private Filter(Builder builder) {
        languageIndex = builder.languageIndex;
        numberOfForksIndex = builder.numberOfForksIndex;
        licenseIndex = builder.licenseIndex;
        searchInIndex = builder.searchInIndex;
        orderBy = builder.orderBy;
        sortBy = builder.sortBy;
        toDate = builder.toDate;
        fromDate = builder.fromDate;
    }

    public int getLanguageIndex() {
        return languageIndex;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public int getNumberOfForksIndex() {
        return numberOfForksIndex;
    }


    public int getLicenseIndex() {
        return licenseIndex;
    }

    public int getSearchInIndex() {
        return searchInIndex;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter filter = (Filter) o;

        if (languageIndex != filter.languageIndex) return false;
        if (numberOfForksIndex != filter.numberOfForksIndex) return false;
        if (licenseIndex != filter.licenseIndex) return false;
        if (searchInIndex != filter.searchInIndex) return false;
        if (!orderBy.equals(filter.orderBy)) return false;
        if (!sortBy.equals(filter.sortBy)) return false;
        if (!fromDate.equals(filter.fromDate)) return false;
        return toDate.equals(filter.toDate);
    }

    @Override
    public int hashCode() {
        int result = languageIndex;
        result = 31 * result + numberOfForksIndex;
        result = 31 * result + licenseIndex;
        result = 31 * result + searchInIndex;
        result = 31 * result + orderBy.hashCode();
        result = 31 * result + sortBy.hashCode();
        result = 31 * result + fromDate.hashCode();
        result = 31 * result + toDate.hashCode();
        return result;
    }


    public static final class Builder {
        private int languageIndex;
        private int numberOfForksIndex;
        private int licenseIndex;
        private int searchInIndex;
        private String orderBy;
        private String sortBy;
        private String fromDate;
        private String toDate;

        public Builder() {
        }

        public Builder languageIndex(int val) {
            languageIndex = val;
            return this;
        }

        public Builder numberOfForksIndex(int val) {
            numberOfForksIndex = val;
            return this;
        }


        public Builder licenseIndex(int val) {
            licenseIndex = val;
            return this;
        }

        public Builder searchInIndex(int val) {
            searchInIndex = val;
            return this;
        }

        public Builder orderBy(String val) {
            orderBy = val;
            return this;
        }

        public Builder sortBy(String val) {
            sortBy = val;
            return this;
        }

        public Builder fromDate(String val) {
            fromDate = val;
            return this;
        }

        public Builder toDate(String val) {
            toDate = val;
            return this;
        }

        public Filter build() {
            return new Filter(this);
        }
    }


}
