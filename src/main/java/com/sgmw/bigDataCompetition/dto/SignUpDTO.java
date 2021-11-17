package com.sgmw.bigDataCompetition.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author desu
 * @created at 2021-07-12 15:16
 * @since JDK1.8
 */
@Data
@ApiModel("SignUpDTO")
public class SignUpDTO {

    @ApiModelProperty("队伍名称")
    @NotEmpty
    private String teamName;

    @ApiModelProperty("队长姓名")
    @NotEmpty
    private String leaderName;

    @ApiModelProperty("队长所在部门")
    @NotEmpty
    private String leaderDepartment;

    @ApiModelProperty("队长工号")
    @NotNull
    private Integer leaderNumber;

    @ApiModelProperty("队长联系电话")
    @NotNull
    private Long leaderTel;

    @ApiModelProperty("队员1姓名")
    private String menberName;

    @ApiModelProperty("队员1所在部门")
    private String menberDepartment;

    @ApiModelProperty("队员1工号")
    private Integer menberNumber;

    @ApiModelProperty("队员1联系电话")
    private Long menberTel;

    @ApiModelProperty("队员2姓名")
    private String followerName;

    @ApiModelProperty("队员2所在部门")
    private String followerDepartment;

    @ApiModelProperty("队员2工号")
    private Integer followerNumber;

    @ApiModelProperty("队员2联系电话")
    private Long followerTel;


}
