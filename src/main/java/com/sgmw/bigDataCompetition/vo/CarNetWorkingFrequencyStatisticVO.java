package com.sgmw.bigDataCompetition.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述:
 *
 * @outhor WeiTongMing
 * @create 2020-02-11 19:50
 */
@Data
@ApiModel
public class CarNetWorkingFrequencyStatisticVO {

    @ApiModelProperty("车型")
    private String model ;

    @ApiModelProperty("标签")
    private String tag ;

    @ApiModelProperty("日期")
    private String day ;

    @ApiModelProperty("频次")
    private Integer frequency ;

}

