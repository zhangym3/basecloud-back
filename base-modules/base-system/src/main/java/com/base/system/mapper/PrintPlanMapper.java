package com.base.system.mapper;

import java.util.List;
import com.base.system.domain.PrintPlan;
import com.base.system.domain.PrintPlanClassification;

/**
 * printplanMapper接口
 * 
 * @author walker
 * @date 2025-04-28
 */
public interface PrintPlanMapper 
{
    /**
     * 查询printplan
     * 
     * @param id printplan主键
     * @return printplan
     */
    public PrintPlan selectPrintPlanById(String id);

    /**
     * 查询printplan列表
     * 
     * @param printPlan printplan
     * @return printplan集合
     */
    public List<PrintPlan> selectPrintPlanList(PrintPlan printPlan);

    /**
     * 新增printplan
     * 
     * @param printPlan printplan
     * @return 结果
     */
    public int insertPrintPlan(PrintPlan printPlan);

    /**
     * 修改printplan
     * 
     * @param printPlan printplan
     * @return 结果
     */
    public int updatePrintPlan(PrintPlan printPlan);

    /**
     * 删除printplan
     * 
     * @param id printplan主键
     * @return 结果
     */
    public int deletePrintPlanById(String id);

    /**
     * 批量删除printplan
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePrintPlanByIds(String[] ids);

    /**
     * 批量删除${subTable.functionName}
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePrintPlanClassificationByPlanIds(String[] ids);
    
    /**
     * 批量新增${subTable.functionName}
     * 
     * @param PrintPlanClassificationList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchPrintPlanClassification(List<PrintPlanClassification> PrintPlanClassificationList);
    

    /**
     * 通过printplan主键删除${subTable.functionName}信息
     * 
     * @param id printplanID
     * @return 结果
     */
    public int deletePrintPlanClassificationByPlanId(String id);
}
