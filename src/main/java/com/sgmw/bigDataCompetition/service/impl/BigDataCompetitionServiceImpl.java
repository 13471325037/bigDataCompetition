package com.sgmw.bigDataCompetition.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgmw.bigDataCompetition.dao.entity.SignUpTable;
import com.sgmw.bigDataCompetition.dao.mapper.SignUpTableMapper;
import com.sgmw.bigDataCompetition.dto.BaseDTO;
import com.sgmw.bigDataCompetition.dto.ScoreDTO;
import com.sgmw.bigDataCompetition.dto.SignUpDTO;
import com.sgmw.bigDataCompetition.exception.BizException;
import com.sgmw.bigDataCompetition.exception.ExceptionEnum;
import com.sgmw.bigDataCompetition.service.IBigDataCompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 报名表 服务实现类
 * </p>
 *
 * @author desu
 * @since 2021-07-12
 */
@Service
@Slf4j
public class BigDataCompetitionServiceImpl extends ServiceImpl<SignUpTableMapper, SignUpTable> implements IBigDataCompetitionService {

    @Resource
    private SignUpTableMapper signUpTableMapper;

    @Override
    public int insertSignUpInformation(SignUpDTO dto) {

        SignUpTable entity = new SignUpTable();
        BeanUtils.copyProperties(dto, entity);
        entity.setCreateTime(new Date());
        return signUpTableMapper.insert(entity);
    }

    @Override
    public int updatePreliminariesScore(ScoreDTO.PreliminariesDTO dto) {

        SignUpTable entity = new SignUpTable();
        entity.setId(dto.getId())
                .setPreliminariesScore(dto.getPreliminariesScore())
                .setPreliminariesRanking(dto.getPreliminariesRanking())
                .setUpdateTime(new Date());
        return signUpTableMapper.updateById(entity);
    }

    @Override
    public int updateFinalsScore(ScoreDTO.FinalsDTO dto) {
        SignUpTable entity = new SignUpTable();
        entity.setId(dto.getId())
                .setFinalsScore(dto.getFinalsScore())
                .setFinalsRanking(dto.getFinalsRanking())
                .setUpdateTime(new Date());
        return signUpTableMapper.updateById(entity);
    }


    @Override
    public SignUpTable findByUrl(String url) {

        QueryWrapper<SignUpTable> wrapper = new QueryWrapper<>();
        wrapper.eq("url", url);
        return signUpTableMapper.selectOne(wrapper);
    }

    @Override
    public List<SignUpTable> selectPreliminariesScore() {

        QueryWrapper<SignUpTable> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("preliminaries_score");
        wrapper.orderByDesc("preliminaries_score");
        return signUpTableMapper.selectList(wrapper);
    }

    @Override
    public List<SignUpTable> selectFinalsScore() {

        QueryWrapper<SignUpTable> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("finals_score");
        wrapper.orderByDesc("finals_score");
        return signUpTableMapper.selectList(wrapper);
    }

    @Override
    public List<SignUpTable> selectWorksSubmit() {

        return signUpTableMapper.selectList(new QueryWrapper<>());
    }



}
