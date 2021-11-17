package com.sgmw.bigDataCompetition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgmw.bigDataCompetition.dao.entity.SignUpTable;
import com.sgmw.bigDataCompetition.dto.ScoreDTO;
import com.sgmw.bigDataCompetition.dto.SignUpDTO;

import java.util.List;

/**
 * <p>
 * 报名表 服务类
 * </p>
 *
 * @author desu
 * @since 2021-07-12
 */
public interface IBigDataCompetitionService extends IService<SignUpTable> {

    int insertSignUpInformation(SignUpDTO signUpDTO);

    int updatePreliminariesScore(ScoreDTO.PreliminariesDTO dto);

    int updateFinalsScore(ScoreDTO.FinalsDTO dto);

    SignUpTable findByUrl(String url);

    List<SignUpTable> selectPreliminariesScore();

    List<SignUpTable> selectFinalsScore();

    List<SignUpTable> selectWorksSubmit();





}
