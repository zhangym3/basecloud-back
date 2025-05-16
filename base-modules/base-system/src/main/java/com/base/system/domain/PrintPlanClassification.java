package com.base.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.base.common.core.annotation.Excel;
import com.base.common.core.web.domain.BaseEntity;

/**
 * ${subTable.functionName}对象 t_print_plan_classification
 * 
 * @author walker
 * @date 2025-04-28
 */
public class PrintPlanClassification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "套餐编码")
    private String planId;

    @Excel(name = "分类编码")
    private String classificationId;
    @Excel(name = "分类名称")
    private String classificationName;

    public void setPlanId(String planId) 
    {
        this.planId = planId;
    }

    public String getPlanId() 
    {
        return planId;
    }
    public void setClassificationId(String classificationId) 
    {
        this.classificationId = classificationId;
    }

    public String getClassificationId() 
    {
        return classificationId;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("planId", getPlanId())
            .append("classificationId", getClassificationId())
            .toString();
    }
}
