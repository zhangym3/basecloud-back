package com.base.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.base.common.core.annotation.Excel;
import com.base.common.core.web.domain.BaseEntity;

/**
 * baseinfo对象 patient_basic_info
 * 
 * @author zhangym
 * @date 2025-04-23
 */
public class PatientBasicInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private String id;

    /** 就诊ID */
    @Excel(name = "就诊ID")
    private String seeDoctorId;

    /** 住院号 */
    @Excel(name = "住院号")
    private String hospitalizationId;

    /** 病案号 */
    @Excel(name = "病案号")
    private String medicalRecordNum;

    /** 住院次数 */
    @Excel(name = "住院次数")
    private Long hospitalizationCount;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String name;

    /** 健康卡号 */
    @Excel(name = "健康卡号")
    private String healthcardNo;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 联系人关系 */
    @Excel(name = "联系人关系")
    private String contactRelationship;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String contactPhone;

    /** 国籍 */
    @Excel(name = "国籍")
    private String nationality;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idCardType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idCardNum;

    /** 入院日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入院日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date admissionTime;

    /** 入院科室编码 */
    @Excel(name = "入院科室编码")
    private String admissionDepartmentId;

    /** 入院科室名称 */
    @Excel(name = "入院科室名称")
    private String admissionDepartmentName;

    /** 入院床位号 */
    @Excel(name = "入院床位号")
    private String admissionWardName;

    /** 出院日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出院日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dischargeTime;

    /** 出院科室编码 */
    @Excel(name = "出院科室编码")
    private String dischargeDepartmentId;

    /** 出院科室名称 */
    @Excel(name = "出院科室名称")
    private String dischargeDepartmentName;

    /** 出院床位号 */
    @Excel(name = "出院床位号")
    private String dischargeWardName;

    /** 离院方式 */
    @Excel(name = "离院方式")
    private String dischargeMethod;

    /** 病案状态(1:未提交 2:已提交) */
    @Excel(name = "病案状态(1:未提交 2:已提交)")
    private String recordStatus;

    /** 当前步骤 */
    @Excel(name = "当前步骤")
    private String currentStep;

    /** 本地归档时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "本地归档时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date localFilingTime;

    /** 本地归档人编码 */
    private String localFilingUserId;

    /** 本地归档人姓名 */
    @Excel(name = "本地归档人姓名")
    private String localFilingUserName;

    /** HIS归档时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "HIS归档时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hisFilingTime;

    /** HIS归档人工号 */
    private String hisFilingUserId;

    /** HIS归档人姓名 */
    @Excel(name = "HIS归档人姓名")
    private String hisFilingUserName;

    /** 是否上传 */
    @Excel(name = "是否上传")
    private String uploadStatus;

    /** 是否打印 */
    @Excel(name = "是否打印")
    private String printStatus;

    /** 是否借出 */
    @Excel(name = "是否借出")
    private String borrowStatus;

    /** 封存时间 */
    private Date sealTime;

    /** 封存人编码 */
    private String sealUserId;

    /** 封存状态 */
    private String sealStatus;

    /** 数据来源编码 */
    private String sourceId;

    /** 主诊断编码 */
    @Excel(name = "主诊断编码")
    private String primaryDiagnosisCode;

    /** 主诊断名称 */
    private String primaryDiagnosisName;

    /** 中医主诊断编码 */
    @Excel(name = "中医主诊断编码")
    private String tcmPrimaryDiagnosisCode;

    /** 中医主诊断名称 */
    private String tcmPrimaryDiagnosisName;

    /** 从诊断编码（逗号分隔） */
    @Excel(name = "从诊断编码", readConverterExp = "逗=号分隔")
    private String secondaryDiagnosisCodes;

    /** 从诊断名称（逗号分隔） */
    private String secondaryDiagnosisNames;

    /** 门诊诊断编码 */
    @Excel(name = "门诊诊断编码")
    private String outpatientDiagnosisCode;

    /** 入院诊断编码 */
    @Excel(name = "入院诊断编码")
    private String admissionConditionCode;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setSeeDoctorId(String seeDoctorId) 
    {
        this.seeDoctorId = seeDoctorId;
    }

    public String getSeeDoctorId() 
    {
        return seeDoctorId;
    }
    public void setHospitalizationId(String hospitalizationId) 
    {
        this.hospitalizationId = hospitalizationId;
    }

    public String getHospitalizationId() 
    {
        return hospitalizationId;
    }
    public void setMedicalRecordNum(String medicalRecordNum) 
    {
        this.medicalRecordNum = medicalRecordNum;
    }

    public String getMedicalRecordNum() 
    {
        return medicalRecordNum;
    }
    public void setHospitalizationCount(Long hospitalizationCount) 
    {
        this.hospitalizationCount = hospitalizationCount;
    }

    public Long getHospitalizationCount() 
    {
        return hospitalizationCount;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setHealthcardNo(String healthcardNo) 
    {
        this.healthcardNo = healthcardNo;
    }

    public String getHealthcardNo() 
    {
        return healthcardNo;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setContactRelationship(String contactRelationship) 
    {
        this.contactRelationship = contactRelationship;
    }

    public String getContactRelationship() 
    {
        return contactRelationship;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setNationality(String nationality) 
    {
        this.nationality = nationality;
    }

    public String getNationality() 
    {
        return nationality;
    }
    public void setIdCardType(String idCardType) 
    {
        this.idCardType = idCardType;
    }

    public String getIdCardType() 
    {
        return idCardType;
    }
    public void setIdCardNum(String idCardNum) 
    {
        this.idCardNum = idCardNum;
    }

    public String getIdCardNum() 
    {
        return idCardNum;
    }
    public void setAdmissionTime(Date admissionTime) 
    {
        this.admissionTime = admissionTime;
    }

    public Date getAdmissionTime() 
    {
        return admissionTime;
    }
    public void setAdmissionDepartmentId(String admissionDepartmentId) 
    {
        this.admissionDepartmentId = admissionDepartmentId;
    }

    public String getAdmissionDepartmentId() 
    {
        return admissionDepartmentId;
    }
    public void setAdmissionDepartmentName(String admissionDepartmentName) 
    {
        this.admissionDepartmentName = admissionDepartmentName;
    }

    public String getAdmissionDepartmentName() 
    {
        return admissionDepartmentName;
    }
    public void setAdmissionWardName(String admissionWardName) 
    {
        this.admissionWardName = admissionWardName;
    }

    public String getAdmissionWardName() 
    {
        return admissionWardName;
    }
    public void setDischargeTime(Date dischargeTime) 
    {
        this.dischargeTime = dischargeTime;
    }

    public Date getDischargeTime() 
    {
        return dischargeTime;
    }
    public void setDischargeDepartmentId(String dischargeDepartmentId) 
    {
        this.dischargeDepartmentId = dischargeDepartmentId;
    }

    public String getDischargeDepartmentId() 
    {
        return dischargeDepartmentId;
    }
    public void setDischargeDepartmentName(String dischargeDepartmentName) 
    {
        this.dischargeDepartmentName = dischargeDepartmentName;
    }

    public String getDischargeDepartmentName() 
    {
        return dischargeDepartmentName;
    }
    public void setDischargeWardName(String dischargeWardName) 
    {
        this.dischargeWardName = dischargeWardName;
    }

    public String getDischargeWardName() 
    {
        return dischargeWardName;
    }
    public void setDischargeMethod(String dischargeMethod) 
    {
        this.dischargeMethod = dischargeMethod;
    }

    public String getDischargeMethod() 
    {
        return dischargeMethod;
    }
    public void setRecordStatus(String recordStatus) 
    {
        this.recordStatus = recordStatus;
    }

    public String getRecordStatus() 
    {
        return recordStatus;
    }
    public void setCurrentStep(String currentStep) 
    {
        this.currentStep = currentStep;
    }

    public String getCurrentStep() 
    {
        return currentStep;
    }
    public void setLocalFilingTime(Date localFilingTime) 
    {
        this.localFilingTime = localFilingTime;
    }

    public Date getLocalFilingTime() 
    {
        return localFilingTime;
    }
    public void setLocalFilingUserId(String localFilingUserId) 
    {
        this.localFilingUserId = localFilingUserId;
    }

    public String getLocalFilingUserId() 
    {
        return localFilingUserId;
    }
    public void setLocalFilingUserName(String localFilingUserName) 
    {
        this.localFilingUserName = localFilingUserName;
    }

    public String getLocalFilingUserName() 
    {
        return localFilingUserName;
    }
    public void setHisFilingTime(Date hisFilingTime) 
    {
        this.hisFilingTime = hisFilingTime;
    }

    public Date getHisFilingTime() 
    {
        return hisFilingTime;
    }
    public void setHisFilingUserId(String hisFilingUserId) 
    {
        this.hisFilingUserId = hisFilingUserId;
    }

    public String getHisFilingUserId() 
    {
        return hisFilingUserId;
    }
    public void setHisFilingUserName(String hisFilingUserName) 
    {
        this.hisFilingUserName = hisFilingUserName;
    }

    public String getHisFilingUserName() 
    {
        return hisFilingUserName;
    }
    public void setUploadStatus(String uploadStatus) 
    {
        this.uploadStatus = uploadStatus;
    }

    public String getUploadStatus() 
    {
        return uploadStatus;
    }
    public void setPrintStatus(String printStatus) 
    {
        this.printStatus = printStatus;
    }

    public String getPrintStatus() 
    {
        return printStatus;
    }
    public void setBorrowStatus(String borrowStatus) 
    {
        this.borrowStatus = borrowStatus;
    }

    public String getBorrowStatus() 
    {
        return borrowStatus;
    }
    public void setSealTime(Date sealTime) 
    {
        this.sealTime = sealTime;
    }

    public Date getSealTime() 
    {
        return sealTime;
    }
    public void setSealUserId(String sealUserId) 
    {
        this.sealUserId = sealUserId;
    }

    public String getSealUserId() 
    {
        return sealUserId;
    }
    public void setSealStatus(String sealStatus) 
    {
        this.sealStatus = sealStatus;
    }

    public String getSealStatus() 
    {
        return sealStatus;
    }
    public void setSourceId(String sourceId) 
    {
        this.sourceId = sourceId;
    }

    public String getSourceId() 
    {
        return sourceId;
    }
    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) 
    {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String getPrimaryDiagnosisCode() 
    {
        return primaryDiagnosisCode;
    }
    public void setPrimaryDiagnosisName(String primaryDiagnosisName) 
    {
        this.primaryDiagnosisName = primaryDiagnosisName;
    }

    public String getPrimaryDiagnosisName() 
    {
        return primaryDiagnosisName;
    }
    public void setTcmPrimaryDiagnosisCode(String tcmPrimaryDiagnosisCode) 
    {
        this.tcmPrimaryDiagnosisCode = tcmPrimaryDiagnosisCode;
    }

    public String getTcmPrimaryDiagnosisCode() 
    {
        return tcmPrimaryDiagnosisCode;
    }
    public void setTcmPrimaryDiagnosisName(String tcmPrimaryDiagnosisName) 
    {
        this.tcmPrimaryDiagnosisName = tcmPrimaryDiagnosisName;
    }

    public String getTcmPrimaryDiagnosisName() 
    {
        return tcmPrimaryDiagnosisName;
    }
    public void setSecondaryDiagnosisCodes(String secondaryDiagnosisCodes) 
    {
        this.secondaryDiagnosisCodes = secondaryDiagnosisCodes;
    }

    public String getSecondaryDiagnosisCodes() 
    {
        return secondaryDiagnosisCodes;
    }
    public void setSecondaryDiagnosisNames(String secondaryDiagnosisNames) 
    {
        this.secondaryDiagnosisNames = secondaryDiagnosisNames;
    }

    public String getSecondaryDiagnosisNames() 
    {
        return secondaryDiagnosisNames;
    }
    public void setOutpatientDiagnosisCode(String outpatientDiagnosisCode) 
    {
        this.outpatientDiagnosisCode = outpatientDiagnosisCode;
    }

    public String getOutpatientDiagnosisCode() 
    {
        return outpatientDiagnosisCode;
    }
    public void setAdmissionConditionCode(String admissionConditionCode) 
    {
        this.admissionConditionCode = admissionConditionCode;
    }

    public String getAdmissionConditionCode() 
    {
        return admissionConditionCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("seeDoctorId", getSeeDoctorId())
            .append("hospitalizationId", getHospitalizationId())
            .append("medicalRecordNum", getMedicalRecordNum())
            .append("hospitalizationCount", getHospitalizationCount())
            .append("name", getName())
            .append("healthcardNo", getHealthcardNo())
            .append("gender", getGender())
            .append("phone", getPhone())
            .append("contactName", getContactName())
            .append("contactRelationship", getContactRelationship())
            .append("contactPhone", getContactPhone())
            .append("nationality", getNationality())
            .append("idCardType", getIdCardType())
            .append("idCardNum", getIdCardNum())
            .append("admissionTime", getAdmissionTime())
            .append("admissionDepartmentId", getAdmissionDepartmentId())
            .append("admissionDepartmentName", getAdmissionDepartmentName())
            .append("admissionWardName", getAdmissionWardName())
            .append("dischargeTime", getDischargeTime())
            .append("dischargeDepartmentId", getDischargeDepartmentId())
            .append("dischargeDepartmentName", getDischargeDepartmentName())
            .append("dischargeWardName", getDischargeWardName())
            .append("dischargeMethod", getDischargeMethod())
            .append("recordStatus", getRecordStatus())
            .append("currentStep", getCurrentStep())
            .append("localFilingTime", getLocalFilingTime())
            .append("localFilingUserId", getLocalFilingUserId())
            .append("localFilingUserName", getLocalFilingUserName())
            .append("hisFilingTime", getHisFilingTime())
            .append("hisFilingUserId", getHisFilingUserId())
            .append("hisFilingUserName", getHisFilingUserName())
            .append("createTime", getCreateTime())
            .append("uploadStatus", getUploadStatus())
            .append("printStatus", getPrintStatus())
            .append("borrowStatus", getBorrowStatus())
            .append("sealTime", getSealTime())
            .append("sealUserId", getSealUserId())
            .append("sealStatus", getSealStatus())
            .append("sourceId", getSourceId())
            .append("primaryDiagnosisCode", getPrimaryDiagnosisCode())
            .append("primaryDiagnosisName", getPrimaryDiagnosisName())
            .append("tcmPrimaryDiagnosisCode", getTcmPrimaryDiagnosisCode())
            .append("tcmPrimaryDiagnosisName", getTcmPrimaryDiagnosisName())
            .append("secondaryDiagnosisCodes", getSecondaryDiagnosisCodes())
            .append("secondaryDiagnosisNames", getSecondaryDiagnosisNames())
            .append("outpatientDiagnosisCode", getOutpatientDiagnosisCode())
            .append("admissionConditionCode", getAdmissionConditionCode())
            .toString();
    }
}
