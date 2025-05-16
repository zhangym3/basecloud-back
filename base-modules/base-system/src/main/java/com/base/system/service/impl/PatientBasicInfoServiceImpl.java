package com.base.system.service.impl;

import java.util.List;
import com.base.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.system.mapper.PatientBasicInfoMapper;
import com.base.system.domain.PatientBasicInfo;
import com.base.system.service.IPatientBasicInfoService;

/**
 * baseinfoService业务层处理
 * 
 * @author zhangym
 * @date 2025-04-23
 */
@Service
public class PatientBasicInfoServiceImpl implements IPatientBasicInfoService 
{
    @Autowired
    private PatientBasicInfoMapper patientBasicInfoMapper;

    /**
     * 查询baseinfo
     * 
     * @param id baseinfo主键
     * @return baseinfo
     */
    @Override
    public PatientBasicInfo selectPatientBasicInfoById(String id)
    {
        return patientBasicInfoMapper.selectPatientBasicInfoById(id);
    }

    /**
     * 查询baseinfo列表
     * 
     * @param patientBasicInfo baseinfo
     * @return baseinfo
     */
    @Override
    public List<PatientBasicInfo> selectPatientBasicInfoList(PatientBasicInfo patientBasicInfo)
    {
        return patientBasicInfoMapper.selectPatientBasicInfoList(patientBasicInfo);
    }

    /**
     * 新增baseinfo
     * 
     * @param patientBasicInfo baseinfo
     * @return 结果
     */
    @Override
    public int insertPatientBasicInfo(PatientBasicInfo patientBasicInfo)
    {
        patientBasicInfo.setCreateTime(DateUtils.getNowDate());
        return patientBasicInfoMapper.insertPatientBasicInfo(patientBasicInfo);
    }

    /**
     * 修改baseinfo
     * 
     * @param patientBasicInfo baseinfo
     * @return 结果
     */
    @Override
    public int updatePatientBasicInfo(PatientBasicInfo patientBasicInfo)
    {
        return patientBasicInfoMapper.updatePatientBasicInfo(patientBasicInfo);
    }

    /**
     * 批量删除baseinfo
     * 
     * @param ids 需要删除的baseinfo主键
     * @return 结果
     */
    @Override
    public int deletePatientBasicInfoByIds(String[] ids)
    {
        return patientBasicInfoMapper.deletePatientBasicInfoByIds(ids);
    }

    /**
     * 删除baseinfo信息
     * 
     * @param id baseinfo主键
     * @return 结果
     */
    @Override
    public int deletePatientBasicInfoById(String id)
    {
        return patientBasicInfoMapper.deletePatientBasicInfoById(id);
    }
}
