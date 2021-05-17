package io.github.arsonistcook.personapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessageDTO {
    private String message;
}
