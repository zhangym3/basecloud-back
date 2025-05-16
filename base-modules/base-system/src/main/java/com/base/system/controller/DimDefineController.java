package com.base.system.controller;

import com.base.common.core.web.controller.BaseController;
import com.base.common.core.web.domain.AjaxResult;
import com.base.common.core.web.page.TableDataInfo;
import com.base.common.security.annotation.RequiresPermissions;
import com.base.system.domain.DimDefine;
import com.base.system.service.IDimDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.base.common.log.annotation.Log;
import com.base.common.log.enums.BusinessType;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.base.common.core.web.domain.AjaxResult;
import com.base.common.core.utils.poi.ExcelUtil;

@RestController
@RequestMapping("/define")
public class DimDefineController extends BaseController {

    @Autowired
    IDimDefineService dimDefineService;

    @GetMapping("/getDataByDimId")
    public TableDataInfo getDataByDimId(String dimId,String parentId,String code,String label){
        DimDefine dim = dimDefineService.selecDimDefineById(dimId);
        startPage();
        return getDataTable(dimDefineService.getData(dim , parentId,code , label));
    }
    /**
     * 查询码表管理列表
     */
    @RequiresPermissions("system:define:list")
    @GetMapping("/list")
    public AjaxResult list(DimDefine DimDefine)
    {
        List<DimDefine> list = dimDefineService.selectDimDefineList(DimDefine);
        return success(list);
    }

    /**
     */
    @RequiresPermissions("system:define:export")
    @Log(title = "码表管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DimDefine DimDefine)
    {
        List<DimDefine> list = dimDefineService.selectDimDefineList(DimDefine);
        ExcelUtil<DimDefine> util = new ExcelUtil<DimDefine>(DimDefine.class);
        util.exportExcel(response, list, "码表管理数据");
    }

    /**
     * 获取码表管理详细信息
     */
    @RequiresPermissions("system:define:query")
    @GetMapping(value = "/{dimId}")
    public AjaxResult getInfo(@PathVariable("dimId") String dimId)
    {
        return success(dimDefineService.selectDimDefineByDimId(dimId));
    }

    /**
     * 新增码表管理
     */
    @RequiresPermissions("system:define:add")
    @Log(title = "码表管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DimDefine DimDefine)
    {
        return toAjax(dimDefineService.insertDimDefine(DimDefine));
    }

    /**
     * 修改码表管理
     */
    @RequiresPermissions("system:define:edit")
    @Log(title = "码表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DimDefine DimDefine)
    {
        return toAjax(dimDefineService.updateDimDefine(DimDefine));
    }

    /**
     * 删除码表管理
     */
    @RequiresPermissions("system:define:remove")
    @Log(title = "码表管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dimIds}")
    public AjaxResult remove(@PathVariable String[] dimIds)
    {
        return toAjax(dimDefineService.deleteDimDefineByDimIds(dimIds));
    }
}
