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

    private Filter(Builder builder) {
        languageIndex = builder.languageIndex;
        numberOfForksIndex = builder.numberOfForksIndex;
        licenseIndex = builder.licenseIndex;
        searchInIndex = builder.searchInIndex;
        orderBy = builder.orderBy;
        sortBy = builder.sortBy;
    }

    public int getLanguageIndex() {
        return languageIndex;
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

    public static final class Builder {
        private int languageIndex;
        private int numberOfForksIndex;
        private int licenseIndex;
        private int searchInIndex;
        private String orderBy;
        private String sortBy;

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

        public Filter build() {
            return new Filter(this);
        }
    }


}
