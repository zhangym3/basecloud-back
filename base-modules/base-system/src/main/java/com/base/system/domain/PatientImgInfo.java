package com.base.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.base.common.core.annotation.Excel;
import com.base.common.core.web.domain.BaseEntity;

/**
 * imginfo对象 patient_img_info
 *
 * @author walker
 * @date 2025-04-25
 */
public class PatientImgInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private String id;

    private String seeDoctorId;

    private String bucket;

    private String objName;

    private String classificationId;
    private String classificationName;

    private String dataId;

    private Long sortnum;

    private String ocrStr;

    private String receiveId;

    private Integer isAuto;

    private Integer isEncrypted;

    private Integer sourceId;

    private Integer isValid;
    private String presignedObjectUrl;

    private Integer imgNums = 1;

    public Integer getImgNums() {
        return imgNums;
    }

    public void setImgNums(Integer imgNums) {
        this.imgNums = imgNums;
    }

    public String getPresignedObjectUrl() {
        return presignedObjectUrl;
    }

    public void setPresignedObjectUrl(String presignedObjectUrl) {
        this.presignedObjectUrl = presignedObjectUrl;
    }

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
    public void setBucket(String bucket)
    {
        this.bucket = bucket;
    }

    public String getBucket()
    {
        return bucket;
    }
    public void setObjName(String objName)
    {
        this.objName = objName;
    }

    public String getObjName()
    {
        return objName;
    }
    public void seClassificationId(String classificationId)
    {
        this.classificationId = classificationId;
    }

    public String geClassificationId()
    {
        return classificationId;
    }
    public void setDataId(String dataId)
    {
        this.dataId = dataId;
    }

    public String getDataId()
    {
        return dataId;
    }
    public void setSortnum(Long sortnum)
    {
        this.sortnum = sortnum;
    }

    public Long getSortnum()
    {
        return sortnum;
    }
    public void setOcrStr(String ocrStr)
    {
        this.ocrStr = ocrStr;
    }

    public String getOcrStr()
    {
        return ocrStr;
    }
    public void setReceiveId(String receiveId)
    {
        this.receiveId = receiveId;
    }

    public String getReceiveId()
    {
        return receiveId;
    }
    public void setIsAuto(Integer isAuto)
    {
        this.isAuto = isAuto;
    }

    public Integer getIsAuto()
    {
        return isAuto;
    }
    public void setIsEncrypted(Integer isEncrypted)
    {
        this.isEncrypted = isEncrypted;
    }

    public Integer getIsEncrypted()
    {
        return isEncrypted;
    }
    public void setSourceId(Integer sourceId)
    {
        this.sourceId = sourceId;
    }

    public Integer getSourceId()
    {
        return sourceId;
    }
    public void setIsValid(Integer isValid)
    {
        this.isValid = isValid;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public Integer getIsValid()
    {
        return isValid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("seeDoctorId", getSeeDoctorId())
                .append("bucket", getBucket())
                .append("objName", getObjName())
                .append("classificationId", geClassificationId())
                .append("dataId", getDataId())
                .append("sortnum", getSortnum())
                .append("createTime", getCreateTime())
                .append("ocrStr", getOcrStr())
                .append("receiveId", getReceiveId())
                .append("isAuto", getIsAuto())
                .append("isEncrypted", getIsEncrypted())
                .append("sourceId", getSourceId())
                .append("isValid", getIsValid())
                .toString();
    }
}
