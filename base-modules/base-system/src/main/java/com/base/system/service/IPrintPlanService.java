package com.base.system.service;

import java.util.List;
import com.base.system.domain.PrintPlan;

/**
 * printplanService接口
 * 
 * @author walker
 * @date 2025-04-28
 */
public interface IPrintPlanService 
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
     * 批量删除printplan
     * 
     * @param ids 需要删除的printplan主键集合
     * @return 结果
     */
    public int deletePrintPlanByIds(String[] ids);

    /**
     * 删除printplan信息
     * 
     * @param id printplan主键
     * @return 结果
     */
    public int deletePrintPlanById(String id);
}
