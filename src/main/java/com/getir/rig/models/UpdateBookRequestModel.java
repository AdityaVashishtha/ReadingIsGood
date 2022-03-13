package com.getir.rig.models;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
public class UpdateBookRequestModel {
    @Min(0)
    @Max(1000)
    private int quantity;
}
