package com.elink.growlog.utils;

import com.elink.growlog.domain.model.valueobjects.accounttype.AccountType;

import java.util.regex.Pattern;

public class Assert {
    public static void matchesPattern(final Pattern pattern, final String value, final String fieldName) {
        if(!pattern.matcher(value).matches()) {
            throw new IllegalArgumentException(fieldName + " with value: " + value + " does not respect the pattern: " + pattern );
        }
    }

    public static void notNull(String type, String fieldName) {
        if(type == null) {
            throw new IllegalArgumentException(fieldName + " cannot should be defined ");
        }
    }
}
