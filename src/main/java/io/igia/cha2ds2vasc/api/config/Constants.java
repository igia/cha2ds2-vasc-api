/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v.
 * 2.0 with a Healthcare Disclaimer.
 * A copy of the Mozilla Public License, v. 2.0 with the Healthcare Disclaimer can
 * be found under the top level directory, named LICENSE.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 * If a copy of the Healthcare Disclaimer was not distributed with this file, You
 * can obtain one at the project website https://github.com/igia.
 *
 * Copyright (C) 2018-2019 Persistent Systems, Inc.
 */
package io.igia.cha2ds2vasc.api.config;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";
        
    public static final Boolean DEFAULT_INPUT = true;
    public static final Integer AGE = 76;
    public static final String FEMALE = "female";
    public static final Map<Integer, Double> STROKE_RISK_PERCENT_RANGE = ImmutableMap.<Integer, Double>builder()
            .put(0, 0.0)
            .put(1, 1.3)
            .put(2, 2.2)
            .put(3, 3.2)
            .put(4, 4.0)
            .put(5, 6.7)
            .put(6, 9.8)
            .put(7, 9.6)
            .put(8, 6.7)
            .put(9, 15.2)
            .build();
    
    private Constants() {
    }
}
