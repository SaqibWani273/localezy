package com.saqib.localezy.record;

import com.saqib.localezy.entity.MyUser;

public record AdminPasswordRecord(MyUser myUser, String secretCode) {
}
