#
# This Source Code Form is subject to the terms of the Mozilla Public License, v.
# 2.0 with a Healthcare Disclaimer.
# A copy of the Mozilla Public License, v. 2.0 with the Healthcare Disclaimer can
# be found under the top level directory, named LICENSE.
# If a copy of the MPL was not distributed with this file, You can obtain one at
# http://mozilla.org/MPL/2.0/.
# If a copy of the Healthcare Disclaimer was not distributed with this file, You
# can obtain one at the project website https://github.com/igia.
#
# Copyright (C) 2018-2019 Persistent Systems, Inc.
#

Feature: Test risk score generation

  Background: 
    * def signIn = call read('classpath:login.feature')
    * def accessToken = signIn.accessToken

  Scenario: Test the cha2ds2vasc algorithm to get the stroke risk with positive values
    Given url baseUrl
    And path '/risk-score'
    And header Authorization = 'Bearer ' + accessToken
    And header 'Content-Type' = 'application/json'
    And request
      """
      {
      "age": 80,
      "congestiveHeartFailure": true,
      "diabetesMellitus": true,
      "hypertension": true,
      "priorStroke": true,
      "sexCategory": "Female",
      "vascularDisease": true
      }
      """
    When method post
    Then status 200
    Then match response ==
      """
      {
      "strokeRiskPercent": 15.2,
      "totalPoint": 9
      }
      """

  Scenario: Test the cha2ds2vasc algorithm to get the stroke risk with age less than 75
    Given url baseUrl
    And path '/risk-score'
    And header Authorization = 'Bearer ' + accessToken
    And header 'Content-Type' = 'application/json'
    And request
      """
      {
      "age": 70,
      "congestiveHeartFailure": true,
      "diabetesMellitus": true,
      "hypertension": true,
      "priorStroke": true,
      "sexCategory": "Female",
      "vascularDisease": true
      }
      """
    When method post
    Then status 200
    Then match response ==
      """
      {
      "strokeRiskPercent": 6.7,
      "totalPoint": 8
      }
      """

  Scenario: Test the cha2ds2vasc algorithm to get the stroke risk with null value of field SexCategory
    Given url baseUrl
    And path '/risk-score'
    And header Authorization = 'Bearer ' + accessToken
    And header 'Content-Type' = 'application/json'
    And request
      """
      {
      "age": 70,
      "congestiveHeartFailure": true,
      "diabetesMellitus": true,
      "hypertension": true,
      "priorStroke": true,
      "vascularDisease": true
      }
      """
    When method post
    Then status 400
    Then match response.fieldErrors ==
      """
      [
      {
      "objectName": "chadsVascParameterDTO",
      "field": "sexCategory",
      "message": "NotNull"
      }
      ]
      """
