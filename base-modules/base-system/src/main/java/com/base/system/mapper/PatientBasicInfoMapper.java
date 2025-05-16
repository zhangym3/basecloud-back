package com.base.system.mapper;

import com.base.system.domain.PatientBasicInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * baseinfoMapper接口
 * 
 * @author zhangym
 * @date 2025-04-23
 */
public interface PatientBasicInfoMapper 
{
    /**
     * 查询baseinfo
     * 
     * @param id baseinfo主键
     * @return baseinfo
     */
    public PatientBasicInfo selectPatientBasicInfoById(String id);

    /**
     * 查询baseinfo列表
     * 
     * @param patientBasicInfo baseinfo
     * @return baseinfo集合
     */
    public List<PatientBasicInfo> selectPatientBasicInfoList(PatientBasicInfo patientBasicInfo);




    /**
     * 新增baseinfo
     * 
     * @param patientBasicInfo baseinfo
     * @return 结果
     */
    public int insertPatientBasicInfo(PatientBasicInfo patientBasicInfo);

    /**
     * 修改baseinfo
     * 
     * @param patientBasicInfo baseinfo
     * @return 结果
     */
    public int updatePatientBasicInfo(PatientBasicInfo patientBasicInfo);

    /**
     * 删除baseinfo
     * 
     * @param id baseinfo主键
     * @return 结果
     */
    public int deletePatientBasicInfoById(String id);

    /**
     * 批量删除baseinfo
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePatientBasicInfoByIds(String[] ids);
}
