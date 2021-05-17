package io.github.arsonistcook.personapi.util;

import io.github.arsonistcook.personapi.dto.request.PhoneDTO;
import io.github.arsonistcook.personapi.entity.Phone;
import io.github.arsonistcook.personapi.enums.PhoneType;

public class PhoneUtils {
    private static final String PHONE_NUMBER = "0011112-2223";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
