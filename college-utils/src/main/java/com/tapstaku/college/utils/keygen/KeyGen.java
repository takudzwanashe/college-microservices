package com.tapstaku.college.utils.keygen;

import org.apache.commons.lang3.RandomStringUtils;

public class KeyGen {

    private KeyGen() {
        super();
    }

    public static Long getUniqueId() {
        final long systemTime = System.currentTimeMillis();
        final String entityId = systemTime + RandomStringUtils.randomNumeric(5);
        return new Long(entityId);
    }
}