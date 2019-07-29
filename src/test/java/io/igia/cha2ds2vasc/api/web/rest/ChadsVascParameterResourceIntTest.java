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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.igia.cha2ds2vasc.api.Cha2Ds2VascapiApp;
import io.igia.cha2ds2vasc.api.config.Constants;
import io.igia.cha2ds2vasc.api.service.RiskCalculatorService;
import io.igia.cha2ds2vasc.api.service.dto.ChadsVascParameterDTO;
import io.igia.cha2ds2vasc.api.service.dto.ResultDTO;
import io.igia.cha2ds2vasc.api.web.rest.ChadsVascParameterResource;
import io.igia.cha2ds2vasc.api.web.rest.errors.ExceptionTranslator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cha2Ds2VascapiApp.class)
public class ChadsVascParameterResourceIntTest {

    private MockMvc restCha2ds2vascMockMvc;

    @Autowired
    private RiskCalculatorService riskCalculatorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Before
    public void setup() {
        ChadsVascParameterResource chadsVascParameterResource = new ChadsVascParameterResource(riskCalculatorService);

        this.restCha2ds2vascMockMvc = MockMvcBuilders.standaloneSetup(chadsVascParameterResource)
                .setCustomArgumentResolvers(pageableArgumentResolver).setControllerAdvice(exceptionTranslator)
                .setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    public void createRiskScore() throws Exception {
        ChadsVascParameterDTO params = new ChadsVascParameterDTO();
        params.setCongestiveHeartFailure(Constants.DEFAULT_INPUT);
        params.setHypertension(Constants.DEFAULT_INPUT);
        params.setDiabetesMellitus(Constants.DEFAULT_INPUT);
        params.setPriorStrok(Constants.DEFAULT_INPUT);
        params.setVascularDisease(Constants.DEFAULT_INPUT);
        params.setAge(Constants.AGE);
        params.setSexCategory(Constants.FEMALE);

        ResultDTO result = new ResultDTO();
        result.setTotalPoint(9);
        result.setStrokeRiskPercent(Constants.STROKE_RISK_PERCENT_RANGE.get(result.getTotalPoint()));

        restCha2ds2vascMockMvc
                .perform(post("/api/risk-score").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(params)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.totalPoint").value(result.getTotalPoint()))
                .andExpect(jsonPath("$.strokeRiskPercent").value(result.getStrokeRiskPercent()));
    }

    @Test
    public void createRiskScoreWithNull() throws Exception {
        ChadsVascParameterDTO paramsWithNull = new ChadsVascParameterDTO();

        restCha2ds2vascMockMvc
                .perform(post("/api/risk-score").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(paramsWithNull)))
                .andExpect(status().isBadRequest());

    }
}
