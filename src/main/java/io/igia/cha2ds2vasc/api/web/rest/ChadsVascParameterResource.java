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
package io.igia.cha2ds2vasc.api.web.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.igia.cha2ds2vasc.api.service.RiskCalculatorService;
import io.igia.cha2ds2vasc.api.service.dto.ChadsVascParameterDTO;
import io.igia.cha2ds2vasc.api.service.dto.ResultDTO;


/**
 * Controller for view and managing risk score points at runtime.
 */
@RestController
@RequestMapping("/api")
public class ChadsVascParameterResource {

    private final Logger log = LoggerFactory.getLogger(ChadsVascParameterResource.class);

    private RiskCalculatorService riskCalculatorService;

    public ChadsVascParameterResource(RiskCalculatorService riskCalculatorService) {
        this.riskCalculatorService = riskCalculatorService;
    }

    @PostMapping("/risk-score")
    public ResultDTO createRiskScore(@Valid @RequestBody ChadsVascParameterDTO params){
        log.info("Creating risk score for patient input {} ", params.toString());
        return riskCalculatorService.calculateRisk(params);
    }
}
