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
package io.igia.cha2ds2vasc.api.service.dto;


import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ChadsVascParameterDTO {
    
    @NotNull
    private Boolean congestiveHeartFailure;
    
    @NotNull
    private Boolean hypertension;

    @NotNull
    private Boolean diabetesMellitus;
    
    @NotNull
    private Boolean priorStroke;

    @NotNull
    private Boolean vascularDisease;

    @NotNull
    private Integer age;

    @NotNull
    private String sexCategory;

    public Boolean getCongestiveHeartFailure() {
        return congestiveHeartFailure;
    }

    public void setCongestiveHeartFailure(Boolean congestiveHeartFailure) {
        this.congestiveHeartFailure = congestiveHeartFailure;
    }

    public Boolean getHypertension() {
        return hypertension;
    }

    public void setHypertension(Boolean hypertension) {
        this.hypertension = hypertension;
    }

    public Boolean getDiabetesMellitus() {
        return diabetesMellitus;
    }

    public void setDiabetesMellitus(Boolean diabetesMellitus) {
        this.diabetesMellitus = diabetesMellitus;
    }

    public Boolean getPriorStroke() {
        return priorStroke;
    }

    public void setPriorStrok(Boolean priorStroke) {
        this.priorStroke = priorStroke;
    }

    public Boolean getVascularDisease() {
        return vascularDisease;
    }

    public void setVascularDisease(Boolean vascularDisease) {
        this.vascularDisease = vascularDisease;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSexCategory() {
        return sexCategory;
    }

    public void setSexCategory(String sexCategory) {
        this.sexCategory = sexCategory;
    }
    
    @Override
    public String toString() {
        return "ChadsVascParameterDTO [congestiveHeartFailure=" + congestiveHeartFailure + ", hypertension="
               + hypertension + ", diabetesMellitus=" + diabetesMellitus + ", priorStrok=" + priorStroke
               + ", vascularDisease=" + vascularDisease + ", age=" + age + ", sexCategory=" + sexCategory + "]";
    }

}
