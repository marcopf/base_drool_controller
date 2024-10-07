package com.drool.demo.object;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pricecompare")
public class PriceCompare {
    private Integer maxvalue;

    public Integer getMaxValue() {
        return maxvalue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxvalue = maxValue;
    }
}
