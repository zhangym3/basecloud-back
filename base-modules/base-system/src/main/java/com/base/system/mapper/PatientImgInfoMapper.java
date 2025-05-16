package com.base.system.mapper;

import java.util.List;

import com.base.system.domain.PatientBasicInfo;
import com.base.system.domain.PatientImgInfo;
import com.base.system.domain.Classification;
import org.apache.ibatis.annotations.Param;

/**
 * imginfoMapper接口
 * 
 * @author walker
 * @date 2025-04-25
 */
public interface PatientImgInfoMapper 
{
    /**
     * 查询imginfo
     * 
     * @param id imginfo主键
     * @return imginfo
     */
    public PatientImgInfo selectPatientImgInfoById(String id);

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
     * 删除imginfo
     * 
     * @param id imginfo主键
     * @return 结果
     */
    public int deletePatientImgInfoById(String id);

    /**
     * 批量删除imginfo
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePatientImgInfoByIds(String[] ids);

    public List<PatientImgInfo> selectPatientImgInfoByseeDoctorId(String seeDoctorId);

    public List<PatientImgInfo> selectPatientImgInfoByClassificationIds(@Param("seeDoctorId") String seeDoctorId, @Param("ids")String ids);


    public List<PatientImgInfo> listDataByImgIds(@Param("seeDoctorId") String seeDoctorId, @Param("ids")List<String> ids);



}
