package com.sgmw.bigDataCompetition.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author desu
 * @created at 2021-07-12 17:15
 * @since JDK1.8
 */
@Data
@ApiModel
public class ScoreDTO {

    @Data
    public static class  PreliminariesDTO {

        @ApiModelProperty("队伍主键")
        @NotNull
        private Integer id;

        @ApiModelProperty("初赛综合成绩")
        private String preliminariesScore;

        @ApiModelProperty("初赛排名")
        private Integer preliminariesRanking;

    }

    @Data
    public static class  FinalsDTO {

        @ApiModelProperty("队伍主键")
        @NotNull
        private Integer id;

        @ApiModelProperty("决赛综合成绩")
        private String finalsScore;

        @ApiModelProperty("决赛排名")
        private Integer finalsRanking;

    }



}
