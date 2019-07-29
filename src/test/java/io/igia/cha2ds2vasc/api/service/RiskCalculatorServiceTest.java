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
package io.igia.cha2ds2vasc.api.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.igia.cha2ds2vasc.api.Cha2Ds2VascapiApp;
import io.igia.cha2ds2vasc.api.config.Constants;
import io.igia.cha2ds2vasc.api.service.RiskCalculatorService;
import io.igia.cha2ds2vasc.api.service.dto.ChadsVascParameterDTO;
import io.igia.cha2ds2vasc.api.service.dto.ResultDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cha2Ds2VascapiApp.class)
public class RiskCalculatorServiceTest {

    @Autowired
    private RiskCalculatorService riskCalculatorService;

    private ChadsVascParameterDTO params;

    private final int lessAge = 70;

    private ResultDTO result;

    @Before
    public void init() {
        params = new ChadsVascParameterDTO();
        params.setCongestiveHeartFailure(Constants.DEFAULT_INPUT);
        params.setHypertension(Constants.DEFAULT_INPUT);
        params.setDiabetesMellitus(Constants.DEFAULT_INPUT);
        params.setPriorStrok(Constants.DEFAULT_INPUT);
        params.setVascularDisease(Constants.DEFAULT_INPUT);
        params.setAge(Constants.AGE);
        params.setSexCategory(Constants.FEMALE);

        result = new ResultDTO();
        result.setTotalPoint(9);
        result.setStrokeRiskPercent(Constants.STROKE_RISK_PERCENT_RANGE.get(9));
    }

    @Test
    public void calculateRiskWithPositiveValues() {
        ResultDTO res = riskCalculatorService.calculateRisk(params);
        assertEquals(res.getTotalPoint(), result.getTotalPoint());
        assertEquals(res.getStrokeRiskPercent(), result.getStrokeRiskPercent(), 0);
    }

    @Test
    public void calculateRiskWithLessAge() {
        params.setAge(lessAge);
        result.setTotalPoint(8);
        result.setStrokeRiskPercent(Constants.STROKE_RISK_PERCENT_RANGE.get(8));

        ResultDTO res = riskCalculatorService.calculateRisk(params);
        assertEquals(res.getTotalPoint(), result.getTotalPoint());
        assertEquals(res.getStrokeRiskPercent(), result.getStrokeRiskPercent(), 0);
    }

}
