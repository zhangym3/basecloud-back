package com.base.system.service;

import java.util.List;

import com.base.system.domain.PatientBasicInfo;
import com.base.system.domain.PatientImgInfo;
import org.apache.ibatis.annotations.Param;

/**
 * imginfoService接口
 * 
 * @author walker
 * @date 2025-04-25
 */
public interface IPatientImgInfoService 
{
    /**
     * 查询imginfo
     * 
     * @param id imginfo主键
     * @return imginfo
     */
    public PatientImgInfo selectPatientImgInfoById(String id);
    public List<PatientImgInfo> selectPatientImgInfoByseeDoctorId(String id);

    /**
     * 查询imginfo列表
     * 
     * @param patientImgInfo imginfo
     * @return imginfo集合
     */
    public List<PatientImgInfo> selectPatientImgInfoList(PatientImgInfo patientImgInfo);

    /**
     * 新增imginfo
     * 
     * @param patientImgInfo imginfo
     * @return 结果
     */
    public int insertPatientImgInfo(PatientImgInfo patientImgInfo);

    /**
     * 修改imginfo
     * 
     * @param patientImgInfo imginfo
     * @return 结果
     */
    public int updatePatientImgInfo(PatientImgInfo patientImgInfo);

    /**
     * 批量删除imginfo
     * 
     * @param ids 需要删除的imginfo主键集合
     * @return 结果
     */
    public int deletePatientImgInfoByIds(String[] ids);

    /**
     * 删除imginfo信息
     * 
     * @param id imginfo主键
     * @return 结果
     */
    public int deletePatientImgInfoById(String id);

    public List<PatientImgInfo> selectPatientImgInfoByClassificationIds(String seeDoctorId, String ids);


    public List<PatientImgInfo> listDataByImgIds(String seeDoctorId, List<String> ids);

}
