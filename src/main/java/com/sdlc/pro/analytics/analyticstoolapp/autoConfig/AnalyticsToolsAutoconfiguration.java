package com.sdlc.pro.analytics.analyticstoolapp.autoConfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "sdlc.pro.analytics.enable",havingValue = "true")
@ComponentScan(basePackages = "com.sdlc.pro.analytics.analyticstoolapp")
public class AnalyticsToolsAutoconfiguration {


}
