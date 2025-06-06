package com.base.system.service;

import java.util.List;
import com.base.system.domain.PatientBasicInfo;
import com.base.system.domain.PatientImgInfo;

/**
 * baseinfoService接口
 * 
 * @author zhangym
 * @date 2025-04-23
 */
public interface IPatientBasicInfoService 
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
     * 批量删除baseinfo
     * 
     * @param ids 需要删除的baseinfo主键集合
     * @return 结果
     */
    public int deletePatientBasicInfoByIds(String[] ids);

    /**
     * 删除baseinfo信息
     * 
     * @param id baseinfo主键
     * @return 结果
     */
    public int deletePatientBasicInfoById(String id);


}
