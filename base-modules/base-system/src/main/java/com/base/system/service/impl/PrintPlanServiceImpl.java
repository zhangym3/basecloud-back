package com.base.system.service.impl;

import java.util.List;
import com.base.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.base.common.core.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.base.system.domain.PrintPlanClassification;
import com.base.system.mapper.PrintPlanMapper;
import com.base.system.domain.PrintPlan;
import com.base.system.service.IPrintPlanService;

/**
 * printplanService业务层处理
 * 
 * @author walker
 * @date 2025-04-28
 */
@Service
public class PrintPlanServiceImpl implements IPrintPlanService 
{
    @Autowired
    private PrintPlanMapper printPlanMapper;

    /**
     * 查询printplan
     * 
     * @param id printplan主键
     * @return printplan
     */
    @Override
    public PrintPlan selectPrintPlanById(String id)
    {
        return printPlanMapper.selectPrintPlanById(id);
    }

    /**
     * 查询printplan列表
     * 
     * @param printPlan printplan
     * @return printplan
     */
    @Override
    public List<PrintPlan> selectPrintPlanList(PrintPlan printPlan)
    {
        return printPlanMapper.selectPrintPlanList(printPlan);
    }

    /**
     * 新增printplan
     * 
     * @param printPlan printplan
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPrintPlan(PrintPlan printPlan)
    {
        printPlan.setCreateTime(DateUtils.getNowDate());
        int rows = printPlanMapper.insertPrintPlan(printPlan);
        insertPrintPlanClassification(printPlan);
        return rows;
    }

    /**
     * 修改printplan
     * 
     * @param printPlan printplan
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePrintPlan(PrintPlan printPlan)
    {
        printPlanMapper.deletePrintPlanClassificationByPlanId(printPlan.getId());
        insertPrintPlanClassification(printPlan);
        return printPlanMapper.updatePrintPlan(printPlan);
    }

    /**
     * 批量删除printplan
     * 
     * @param ids 需要删除的printplan主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePrintPlanByIds(String[] ids)
    {
        printPlanMapper.deletePrintPlanClassificationByPlanIds(ids);
        return printPlanMapper.deletePrintPlanByIds(ids);
    }

    /**
     * 删除printplan信息
     * 
     * @param id printplan主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePrintPlanById(String id)
    {
        printPlanMapper.deletePrintPlanClassificationByPlanId(id);
        return printPlanMapper.deletePrintPlanById(id);
    }

    /**
     * 新增${subTable.functionName}信息
     * 
     * @param printPlan printplan对象
     */
    public void insertPrintPlanClassification(PrintPlan printPlan)
    {
        List<PrintPlanClassification> PrintPlanClassificationList = printPlan.getPrintPlanClassificationList();
        String id = printPlan.getId();
        if (StringUtils.isNotNull(PrintPlanClassificationList))
        {
            List<PrintPlanClassification> list = new ArrayList<PrintPlanClassification>();
            for (PrintPlanClassification PrintPlanClassification : PrintPlanClassificationList)
            {
                PrintPlanClassification.setPlanId(id);
                list.add(PrintPlanClassification);
            }
            if (list.size() > 0)
            {
                printPlanMapper.batchPrintPlanClassification(list);
            }
        }
    }
}
