package com.advenoh.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.quartz.CronExpression;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class JobUtilsTest {
    @Test
    public void cronExpressionTest() {
        String CRON_EXPRESSION_EVERY_10_SECONDS = "0/10 * * ? * *";
        assertThat(CronExpression.isValidExpression(CRON_EXPRESSION_EVERY_10_SECONDS)).isTrue();
    }
}