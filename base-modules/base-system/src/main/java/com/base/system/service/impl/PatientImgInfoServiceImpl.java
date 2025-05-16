package com.base.system.service.impl;

import java.util.List;
import com.base.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.system.mapper.PatientImgInfoMapper;
import com.base.system.domain.PatientImgInfo;
import com.base.system.service.IPatientImgInfoService;

/**
 * imginfoService业务层处理
 * 
 * @author walker
 * @date 2025-04-25
 */
@Service
public class PatientImgInfoServiceImpl implements IPatientImgInfoService 
{
    @Autowired
    private PatientImgInfoMapper patientImgInfoMapper;

    /**
     * 查询imginfo
     * 
     * @param id imginfo主键
     * @return imginfo
     */
    @Override
    public PatientImgInfo selectPatientImgInfoById(String id)
    {
        return patientImgInfoMapper.selectPatientImgInfoById(id);
    }
    /**
     * 查询imginfo
     *
     * @param seeDoctorId 就诊ID
     * @return imginfo
     */
    @Override
    public List<PatientImgInfo> selectPatientImgInfoByseeDoctorId(String seeDoctorId)
    {
        return patientImgInfoMapper.selectPatientImgInfoByseeDoctorId(seeDoctorId);
    }
    /**
     * 查询imginfo列表
     * 
     * @param patientImgInfo imginfo
     * @return imginfo
     */
    @Override
    public List<PatientImgInfo> selectPatientImgInfoList(PatientImgInfo patientImgInfo)
    {
        return patientImgInfoMapper.selectPatientImgInfoList(patientImgInfo);
    }

    /**
     * 新增imginfo
     * 
     * @param patientImgInfo imginfo
     * @return 结果
     */
    @Override
    public int insertPatientImgInfo(PatientImgInfo patientImgInfo)
    {
        patientImgInfo.setCreateTime(DateUtils.getNowDate());
        return patientImgInfoMapper.insertPatientImgInfo(patientImgInfo);
    }

    /**
     * 修改imginfo
     * 
     * @param patientImgInfo imginfo
     * @return 结果
     */
    @Override
    public int updatePatientImgInfo(PatientImgInfo patientImgInfo)
    {
        return patientImgInfoMapper.updatePatientImgInfo(patientImgInfo);
    }

    /**
     * 批量删除imginfo
     * 
     * @param ids 需要删除的imginfo主键
     * @return 结果
     */
    @Override
    public int deletePatientImgInfoByIds(String[] ids)
    {
        return patientImgInfoMapper.deletePatientImgInfoByIds(ids);
    }

    /**
     * 删除imginfo信息
     * 
     * @param id imginfo主键
     * @return 结果
     */
    @Override
    public int deletePatientImgInfoById(String id)
    {
        return patientImgInfoMapper.deletePatientImgInfoById(id);
    }


    public List<PatientImgInfo> selectPatientImgInfoByClassificationIds(String seeDoctorId, String ids){

        return patientImgInfoMapper.selectPatientImgInfoByClassificationIds(seeDoctorId,ids);
    }

    public List<PatientImgInfo> listDataByImgIds(String seeDoctorId, List<String> ids){

        return patientImgInfoMapper.listDataByImgIds(seeDoctorId,ids);
    }
}
