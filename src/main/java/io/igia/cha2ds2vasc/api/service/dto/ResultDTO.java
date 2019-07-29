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

public class ResultDTO {

    private int totalPoint;
    private double strokeRiskPercent;

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

    public double getStrokeRiskPercent() {
        return strokeRiskPercent;
    }

    public void setStrokeRiskPercent(double strokeRiskPercent) {
        this.strokeRiskPercent = strokeRiskPercent;
    }

    @Override
    public String toString() {
        return "ResultDTO [totalPoint=" + totalPoint + ", strokeRiskPercent=" + strokeRiskPercent + "]";
    }

}
