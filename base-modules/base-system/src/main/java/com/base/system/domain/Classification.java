package com.base.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.base.common.core.annotation.Excel;
import com.base.common.core.web.domain.TreeEntity;

/**
 * classification对象 t_classification
 *
 * @author zhangym
 * @date 2025-04-18
 */
public class Classification extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    private String id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String classificationName;

    /** 分类类型 */
    @Excel(name = "分类类型")
    private Integer classificationType;

    private String ids;

    /** 排序 */
    private Long sortnum;

    /** 全局排序 */
    private Long globalSort;

    /** 是否权限控制 */
    @Excel(name = "是否权限控制")
    private String isRule;

    /** 是否为必须 */
    private String isMust;

    /** 层级 */
    private String level;

    /** 排版方向 */
    private String direction;

    /** 是否降噪 */
    private String isDenoise;

    /** 是否保留颜色 */
    private String isColorful;

    /** 降噪点大小 */
    private String nodesize;

    /** 最小处理区域百分比 */
    private Integer minpercent;

    /** 最小处理区域百分比 */
    private Integer maxpercent;

    /** 是否脱敏 */
    @Excel(name = "是否脱敏")
    private String isDesensitized;


    private Integer imgNums;

    public Integer getImgNums() {
        return imgNums;
    }

    public void setImgNums(Integer imgNums) {
        this.imgNums = imgNums;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String geClassificationName() {
        return classificationName;
    }

    public void seClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public Integer geClassificationType() {
        return classificationType;
    }

    public void seClassificationType(Integer classificationType) {
        this.classificationType = classificationType;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Long getSortnum() {
        return sortnum;
    }

    public void setSortnum(Long sortnum) {
        this.sortnum = sortnum;
    }

    public Long getGlobalSort() {
        return globalSort;
    }

    public void setGlobalSort(Long globalSort) {
        this.globalSort = globalSort;
    }

    public String getIsRule() {
        return isRule;
    }

    public void setIsRule(String isRule) {
        this.isRule = isRule;
    }

    public String getIsMust() {
        return isMust;
    }

    public void setIsMust(String isMust) {
        this.isMust = isMust;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getIsDenoise() {
        return isDenoise;
    }

    public void setIsDenoise(String isDenoise) {
        this.isDenoise = isDenoise;
    }

    public String getIsColorful() {
        return isColorful;
    }

    public void setIsColorful(String isColorful) {
        this.isColorful = isColorful;
    }

    public String getNodesize() {
        return nodesize;
    }

    public void setNodesize(String nodesize) {
        this.nodesize = nodesize;
    }

    public Integer getMinpercent() {
        return minpercent;
    }

    public void setMinpercent(Integer minpercent) {
        this.minpercent = minpercent;
    }

    public Integer getMaxpercent() {
        return maxpercent;
    }

    public void setMaxpercent(Integer maxpercent) {
        this.maxpercent = maxpercent;
    }

    public String getIsDesensitized() {
        return isDesensitized;
    }

    public void setIsDesensitized(String isDesensitized) {
        this.isDesensitized = isDesensitized;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public Integer getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(Integer classificationType) {
        this.classificationType = classificationType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("parentId", getParentId())
                .append("parentName", getParentName())
                .append("classificationName", geClassificationName())
                .append("classificationType", geClassificationType())
                .append("ids", getIds())
                .append("sortnum", getSortnum())
                .append("globalSort", getGlobalSort())
                .append("isRule", getIsRule())
                .append("isMust", getIsMust())
                .append("level", getLevel())
                .append("direction", getDirection())
                .append("isDenoise", getIsDenoise())
                .append("isColorful", getIsColorful())
                .append("nodesize", getNodesize())
                .append("minpercent", getMinpercent())
                .append("maxpercent", getMaxpercent())
                .append("isDesensitized", getIsDesensitized())
                .toString();
    }
}
