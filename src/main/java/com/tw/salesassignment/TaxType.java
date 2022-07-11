package com.tw.salesassignment;

import java.math.BigDecimal;

public enum TaxType {

    EXEMPTED_UNIMPORTED_TAX(BigDecimal.valueOf(0,2)),
    NOT_EXEMPTED_UNIMPORTED_TAX(BigDecimal.valueOf(10,2)),
    EXEMPTED_IMPORTED_TAX(BigDecimal.valueOf(5,2)),
    NOT_EXEMPTED_IMPORTED_TAX(BigDecimal.valueOf(15,2));

    BigDecimal rate;

    TaxType(BigDecimal rate) {
        this.rate = rate;
    }
}
