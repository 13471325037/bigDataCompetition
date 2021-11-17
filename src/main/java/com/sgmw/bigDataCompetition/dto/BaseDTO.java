package com.sgmw.bigDataCompetition.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author desu
 * @created at 2021-07-14 14:16
 * @since JDK1.8
 */
@Data
@ApiModel
public class BaseDTO {

    @ApiModelProperty("页数")
    @NotNull
    private Integer page;

    @ApiModelProperty("条数")
    @NotNull
    private Integer size;
}
