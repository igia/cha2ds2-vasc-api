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
package io.igia.cha2ds2vasc.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.igia.cha2ds2vasc.api.config.Constants;
import io.igia.cha2ds2vasc.api.service.RiskCalculatorService;
import io.igia.cha2ds2vasc.api.service.dto.ChadsVascParameterDTO;
import io.igia.cha2ds2vasc.api.service.dto.ResultDTO;

@Service
public class RiskCalculatorServiceImpl implements RiskCalculatorService {

    private final Logger log = LoggerFactory.getLogger(RiskCalculatorServiceImpl.class);

    @Override
    public ResultDTO calculateRisk(ChadsVascParameterDTO params) {
        int totalScore = 0;
        ResultDTO result = new ResultDTO();
        if (params.getCongestiveHeartFailure()) {
            totalScore += 1;
        }
        if (params.getDiabetesMellitus()) {
            totalScore += 1;
        }
        if (params.getHypertension()) {
            totalScore += 1;
        }
        if (params.getPriorStroke()) {
            totalScore += 2;
        }
        if (params.getVascularDisease()) {
            totalScore += 1;
        }
        if (params.getSexCategory().equalsIgnoreCase(Constants.FEMALE)) {
            totalScore += 1;
        }
        if (params.getAge() > 74) {
            totalScore += 2;
        } else if (params.getAge() > 64 && params.getAge() < 75) {
            totalScore += 1;
        }
        result.setTotalPoint(new Integer(totalScore));
        result.setStrokeRiskPercent(Constants.STROKE_RISK_PERCENT_RANGE.get(totalScore));
        log.info("Result is {}", result.toString());
        return result;
    }
}
