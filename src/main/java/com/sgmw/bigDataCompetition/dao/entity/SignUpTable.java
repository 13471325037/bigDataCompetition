package com.sgmw.bigDataCompetition.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgmw.bigDataCompetition.conf.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 报名表
 * </p>
 *
 * @author desu
 * @since 2021-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SignUpTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 队伍名称
     */
    @TableField("team_name")
    private String teamName;

    /**
     * 队长姓名
     */
    @TableField("leader_name")
    private String leaderName;

    /**
     * 队长所在部门
     */
    @TableField("leader_department")
    private String leaderDepartment;

    /**
     * 队长工号
     */
    @TableField("leader_number")
    private Integer leaderNumber;

    /**
     * 队长联系电话
     */
    @TableField("leader_tel")
    private Long leaderTel;

    /**
     * 队员1姓名
     */
    @TableField("menber_name")
    private String menberName;

    /**
     * 队员1所在部门
     */
    @TableField("menber_department")
    private String menberDepartment;

    /**
     * 队员1工号
     */
    @TableField("menber_number")
    private Integer menberNumber;

    /**
     * 队员1联系电话
     */
    @TableField("menber_tel")
    private Long menberTel;

    /**
     * 队员2姓名
     */
    @TableField("follower_name")
    private String followerName;

    /**
     * 队员2所在部门
     */
    @TableField("follower_department")
    private String followerDepartment;

    /**
     * 队员2工号
     */
    @TableField("follower_number")
    private Integer followerNumber;

    /**
     * 队员2联系电话
     */
    @TableField("follower_tel")
    private Long followerTel;

    /**
     * 外部URL
     */
    @TableField("url")
    private String url;

    /**
     * 文件地址
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 作品名称
     */
    @TableField("work_name")
    private String workName;

    /**
     * 初赛综合成绩
     */
    @TableField("preliminaries_score")
    private String preliminariesScore;

    /**
     * 初赛排名
     */
    @TableField("preliminaries_ranking")
    private Integer preliminariesRanking;

    /**
     * 决赛综合成绩
     */
    @TableField("finals_score")
    private String finalsScore;

    /**
     * 决赛排名
     */
    @TableField("finals_ranking")
    private Integer finalsRanking;

    /**
     * 数据更新时间
     */
    @JsonFormat(pattern = Constant.DATE_FORMAT, timezone = Constant.TIMEZONE_BJ)
    @TableField("update_time")
    private Date updateTime;

    /**
     * 数据插入时间
     */
    @JsonFormat(pattern = Constant.DATE_FORMAT, timezone = Constant.TIMEZONE_BJ)
    @TableField("create_time")
    private Date createTime;


}
