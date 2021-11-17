package com.sgmw.bigDataCompetition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fantasy.api.common.ApiResponseBuilder;
import com.sgmw.bigDataCompetition.dao.entity.SignUpTable;
import com.sgmw.bigDataCompetition.dto.ScoreDTO;
import com.sgmw.bigDataCompetition.dto.SignUpDTO;
import com.sgmw.bigDataCompetition.exception.BizException;
import com.sgmw.bigDataCompetition.exception.ExceptionEnum;
import com.sgmw.bigDataCompetition.service.IBigDataCompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.*;

import static com.sgmw.bigDataCompetition.conf.Constant.*;


/**
 * <p>
 * 报名表 前端控制器
 * </p>
 *
 * @author desu
 * @since 2021-07-12
 */
@RestController
@RequestMapping("/big_data_competition")
@Slf4j
@Api(tags = "大数据应用竞赛接口")
public class BigDataCompetitionController {

    private String filePath = "D:\\";
    //private String filePath = "/opt/sgmw/bigDataCompetition/files/";


    @Resource
    private IBigDataCompetitionService bigDataCompetitionService;

    @PostMapping("/insertSignUpInformation")
    @ApiOperation("插入报名信息")
    @ResponseBody
    public ApiResponseBuilder.ApiResponse insertSignUpInformation(@RequestBody @Valid SignUpDTO dto) {

        QueryWrapper<SignUpTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("team_name", dto.getTeamName());
        Integer count = bigDataCompetitionService.count(queryWrapper);
        if (count > 0) {
            return ApiResponseBuilder.buildFail(REPEAT_CODE, REPEAT_TEAM_NAME);
        }
        return ApiResponseBuilder.buildOK(bigDataCompetitionService.insertSignUpInformation(dto));
    }

    @GetMapping("/selectWorksSubmit")
    @ApiOperation("查询作品提交情况")
    @ResponseBody
    public ApiResponseBuilder.ApiResponse selectWorksSubmit() {

        return ApiResponseBuilder.buildOK(bigDataCompetitionService.selectWorksSubmit());
    }

    @PostMapping("/updatePreliminariesScore")
    @ApiOperation("更新初赛成绩")
    @ResponseBody
    public ApiResponseBuilder.ApiResponse updatePreliminariesScore(@RequestBody @Valid ScoreDTO.PreliminariesDTO dto) {

        return ApiResponseBuilder.buildOK(bigDataCompetitionService.updatePreliminariesScore(dto));
    }

    @PostMapping("/updateFinalsScore")
    @ApiOperation("更新决赛成绩")
    @ResponseBody
    public ApiResponseBuilder.ApiResponse updateFinalsScore(@RequestBody @Valid ScoreDTO.FinalsDTO dto) {

        return ApiResponseBuilder.buildOK(bigDataCompetitionService.updateFinalsScore(dto));
    }

    @GetMapping("/selectPreliminariesScore")
    @ApiOperation("查询初赛成绩")
    public ApiResponseBuilder.ApiResponse<List<SignUpTable>> selectPreliminariesScore() {

        return ApiResponseBuilder.buildOK(bigDataCompetitionService.selectPreliminariesScore());
    }

    @GetMapping("/selectFinalsScore")
    @ApiOperation("查询决赛成绩")
    public ApiResponseBuilder.ApiResponse<List<SignUpTable>> selectFinalsScore() {

        return ApiResponseBuilder.buildOK(bigDataCompetitionService.selectFinalsScore());
    }


    @PostMapping("/upload")
    @ApiOperation("文件上传")
    @ResponseBody
    public ApiResponseBuilder.ApiResponse upload(@RequestParam("file") MultipartFile file) {

        String url ;
        if (file.isEmpty()) {
            return ApiResponseBuilder.buildFail(FAIL_UPLOAD_CODE, FAIL_UPLOAD_PLEASE_TRY_AGAIN);
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        try {

            String[] split = fileName.split("_");
            if (split.length != 3) {
                return ApiResponseBuilder.buildFail(NON_STANDARD_CODE, NON_STANDARD_WORK_NAME);
            }
            List<String> list = new ArrayList<>();
            for (String tmp : split) {
                list.add(tmp);
            }
            String workName = list.get(0).trim();
            String teamName = list.get(1).trim();
            QueryWrapper<SignUpTable> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("team_name", teamName);
            Integer count = bigDataCompetitionService.count(queryWrapper);
            if (count < 1) {
                return ApiResponseBuilder.buildFail(NON_EXIST_CODE, NON_EXIST_TEAM_NAME);
            }
            file.transferTo(dest);
            SignUpTable signUpTable = new SignUpTable();
            url = UUID.randomUUID().toString().replace("-", "");
            signUpTable.setUrl(url)
                    .setFilePath(dest.getPath())
                    .setWorkName(workName)
                    .setUpdateTime(new Date());
            UpdateWrapper<SignUpTable> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("team_name", teamName);
            bigDataCompetitionService.update(signUpTable, updateWrapper);
            return ApiResponseBuilder.buildOK(url);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return ApiResponseBuilder.buildFail(FAIL_CODE, FAIL_UPLOAD);
    }

    @GetMapping("/preview/{url}")
    @ApiOperation("文件预览")
    public ApiResponseBuilder.ApiResponse<SignUpTable> preview(@PathVariable String url) {
        return ApiResponseBuilder.buildOK(bigDataCompetitionService.findByUrl(url));
    }

    @GetMapping(value = "/deriveVehicle")
    @ApiOperation("下载文件")
    public void deriveVehicle(@RequestParam @Valid String url, HttpServletResponse response) {

        SignUpTable signUpTable = bigDataCompetitionService.findByUrl(url);

        if (Objects.isNull(signUpTable)) {
            throw new BizException(ExceptionEnum.INEXISTENCE_FILE);
        }
        downLoad(signUpTable.getFilePath(), response);

    }

    private void downLoad(String inFile, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        int lastIndexOf = inFile.lastIndexOf("/");
        //int lastIndexOf = inFile.lastIndexOf("\\");
        String fileName = inFile.substring(lastIndexOf + 1);
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            InputStream inputStream = new FileInputStream(inFile);
            OutputStream os = response.getOutputStream();

            //循环写入输出流
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
        } catch (Exception e) {
            throw new BizException(ExceptionEnum.DERIVE_UNKNOWN_EXCEPTION);
        }
    }


}
