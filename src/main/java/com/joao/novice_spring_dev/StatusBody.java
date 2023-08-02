package com.joao.novice_spring_dev;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.NoArgsConstructor;
    @NoArgsConstructor
@JsonSerialize
public class StatusBody {
    String status;
}
