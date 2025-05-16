package com.base.system.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.base.common.core.annotation.Excel;
import com.base.common.core.web.domain.BaseEntity;

/**
 * printplan对象 t_print_plan
 * 
 * @author walker
 * @date 2025-04-28
 */
public class PrintPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id;

    @Excel(name = "套餐名称")
    private String planName;

    @Excel(name = "创建人")
    private String createUserId;
    @Excel(name = "套餐编码")
    private String classificationIds;
    @Excel(name = "套餐名称")
    private String classificationNames;

    public String getClassificationNames() {
        return classificationNames;
    }

    public void setClassificationNames(String classificationNames) {
        this.classificationNames = classificationNames;
    }

    /** $table.subTable.functionName信息 */
    private List<PrintPlanClassification> printPlanClassificationList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPlanName(String planName) 
    {
        this.planName = planName;
    }

    public String getPlanName() 
    {
        return planName;
    }
    public void setCreateUserId(String createUserId) 
    {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() 
    {
        return createUserId;
    }

    public List<PrintPlanClassification> getPrintPlanClassificationList() {
        return printPlanClassificationList;
    }

    public void setPrintPlanClassificationList(List<PrintPlanClassification> printPlanClassificationList) {
        this.printPlanClassificationList = printPlanClassificationList;
    }

    public String getClassificationIds() {
        return classificationIds;
    }

    public void setClassificationIds(String classificationIds) {
        this.classificationIds = classificationIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("planName", getPlanName())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("PrintPlanClassificationList", getPrintPlanClassificationList())
            .toString();
    }
}
