package com.saqib.localezy.record;

import java.util.List;
import java.util.Map;

public record UpdateCartItemsRecord(
        List<Map<String, Object>> cartItems,
        Long customerId
) {
}
