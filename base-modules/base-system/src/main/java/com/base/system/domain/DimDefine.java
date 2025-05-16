package com.base.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.base.common.core.annotation.Excel;
import com.base.common.core.web.domain.TreeEntity;

/**
 * 码表管理对象 t_dim_define
 * 
 * @author walker
 * @date 2025-04-23
 */
@Data
public class DimDefine extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private String dimId;

    /** 码表名称 */
    @Excel(name = "码表名称")
    private String dimName;

    /** 编码字段名称 */
    @Excel(name = "编码字段名称")
    private String dimIdCol;

    /** 标签字段名称 */
    @Excel(name = "标签字段名称")
    private String dimKeyCol;

    /** 值字段名称 */
    @Excel(name = "值字段名称")
    private String dimValueCol;

    /** 排序字段名称 */
    @Excel(name = "排序字段名称")
    private String dimOrderCol;

    /** 排序规则 ASC/DESC */
    @Excel(name = "排序规则 ASC/DESC")
    private String dimSortDirection;

    /** 关联字段名称 */
    @Excel(name = "关联字段名称")
    private String relCol;

    /** 码表SQL */
    @Excel(name = "码表SQL")
    private String dimSql;

    /** 是否有效 */
    @Excel(name = "是否有效")
    private Integer isValid;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dimId", getDimId())
            .append("dimName", getDimName())
            .append("dimIdCol", getDimIdCol())
            .append("dimKeyCol", getDimKeyCol())
            .append("dimValueCol", getDimValueCol())
            .append("dimOrderCol", getDimOrderCol())
            .append("dimSortDirection", getDimSortDirection())
            .append("parentId", getParentId())
            .append("relCol", getRelCol())
            .append("dimSql", getDimSql())
            .append("createTime", getCreateTime())
            .append("isValid", getIsValid())
            .toString();
    }
}
