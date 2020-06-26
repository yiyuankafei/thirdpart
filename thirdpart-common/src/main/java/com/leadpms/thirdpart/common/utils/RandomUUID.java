package com.leadpms.thirdpart.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class RandomUUID {

    public static String getUUID() {
        return StringUtils.replaceAll(UUID.randomUUID().toString(), "-", "");
    }
}
