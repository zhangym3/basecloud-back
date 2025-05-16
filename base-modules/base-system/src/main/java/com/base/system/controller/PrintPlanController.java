package com.base.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.common.core.utils.StringUtils;
import com.base.common.core.utils.uuid.IdUtils;
import com.base.common.security.utils.SecurityUtils;
import com.base.system.api.domain.SysUser;
import com.base.system.api.model.LoginUser;
import com.base.system.domain.PrintPlanClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.base.common.log.annotation.Log;
import com.base.common.log.enums.BusinessType;
import com.base.common.security.annotation.RequiresPermissions;
import com.base.system.domain.PrintPlan;
import com.base.system.service.IPrintPlanService;
import com.base.common.core.web.controller.BaseController;
import com.base.common.core.web.domain.AjaxResult;
import com.base.common.core.utils.poi.ExcelUtil;
import com.base.common.core.web.page.TableDataInfo;

/**
 * printplanController
 * 
 * @author walker
 * @date 2025-04-28
 */
@RestController
@RequestMapping("/printplan")
public class PrintPlanController extends BaseController
{
    @Autowired
    private IPrintPlanService printPlanService;

    /**
     * 查询printplan列表
     */
    @RequiresPermissions("system:printplan:list")
    @GetMapping("/list")
    public TableDataInfo list(PrintPlan printPlan)
    {
        startPage();
        List<PrintPlan> list = printPlanService.selectPrintPlanList(printPlan);
        return getDataTable(list);
    }
    @RequiresPermissions("system:printplan:list")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<PrintPlan> list = printPlanService.selectPrintPlanList(new PrintPlan());
        return success(list);
    }

    /**
     * 导出printplan列表
     */
    @RequiresPermissions("system:printplan:export")
    @Log(title = "printplan", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PrintPlan printPlan)
    {
        List<PrintPlan> list = printPlanService.selectPrintPlanList(printPlan);
        ExcelUtil<PrintPlan> util = new ExcelUtil<PrintPlan>(PrintPlan.class);
        util.exportExcel(response, list, "printplan数据");
    }

    /**
     * 获取printplan详细信息
     */
    @RequiresPermissions("system:printplan:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(printPlanService.selectPrintPlanById(id));
    }

    /**
     * 新增printplan
     */
    @RequiresPermissions("system:printplan:add")
    @Log(title = "printplan", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JSONObject data)
    {
        PrintPlan printPlan = new PrintPlan();
        String planName = data.getString("planName");

        printPlan.setPlanName(planName);
        LoginUser loginUser = SecurityUtils.getLoginUser();

        printPlan.setId(IdUtils.randomUUID());
        printPlan.setCreateUserId(loginUser.getUserid() + "");
        printPlan.setCreateTime(new Date());

        JSONArray ll = data.getJSONArray("printPlanClassificationList");

        List<PrintPlanClassification> ppcl = new ArrayList<>();
        
        for(int i =0 ; i < ll.size(); i ++) {
            JSONObject l = ll.getJSONObject(i);
            PrintPlanClassification ppc = new PrintPlanClassification();
            ppc.setPlanId(printPlan.getId());
            ppc.setClassificationId(l.getString("classificationId"));
            ppcl.add(ppc);
        }

        printPlan.setPrintPlanClassificationList(ppcl);
        return toAjax(printPlanService.insertPrintPlan(printPlan));
    }

    /**
     * 修改printplan
     */
    @RequiresPermissions("system:printplan:edit")
    @Log(title = "printplan", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PrintPlan printPlan)
    {
        return toAjax(printPlanService.updatePrintPlan(printPlan));
    }

    /**
     * 删除printplan
     */
    @RequiresPermissions("system:printplan:remove")
    @Log(title = "printplan", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(printPlanService.deletePrintPlanByIds(ids));
    }
}
