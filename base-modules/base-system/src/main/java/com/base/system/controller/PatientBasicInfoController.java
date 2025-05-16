package com.base.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.base.system.domain.PatientBasicInfo;
import com.base.system.service.IPatientBasicInfoService;
import com.base.common.core.web.controller.BaseController;
import com.base.common.core.web.domain.AjaxResult;
import com.base.common.core.utils.poi.ExcelUtil;
import com.base.common.core.web.page.TableDataInfo;

/**
 * baseinfoController
 * 
 * @author zhangym
 * @date 2025-04-23
 */
@RestController
@RequestMapping("/baseinfo")
public class PatientBasicInfoController extends BaseController
{
    @Autowired
    private IPatientBasicInfoService patientBasicInfoService;

    /**
     * 查询baseinfo列表
     */
    @RequiresPermissions("system:baseinfo:list")
    @GetMapping("/list")
    public TableDataInfo list(PatientBasicInfo patientBasicInfo)
    {
        startPage();
        List<PatientBasicInfo> list = patientBasicInfoService.selectPatientBasicInfoList(patientBasicInfo);
        return getDataTable(list);
    }

    /**
     * 导出baseinfo列表
     */
    @RequiresPermissions("system:baseinfo:export")
    @Log(title = "baseinfo", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PatientBasicInfo patientBasicInfo)
    {
        List<PatientBasicInfo> list = patientBasicInfoService.selectPatientBasicInfoList(patientBasicInfo);
        ExcelUtil<PatientBasicInfo> util = new ExcelUtil<PatientBasicInfo>(PatientBasicInfo.class);
        util.exportExcel(response, list, "baseinfo数据");
    }

    /**
     * 获取baseinfo详细信息
     */
    @RequiresPermissions("system:baseinfo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(patientBasicInfoService.selectPatientBasicInfoById(id));
    }

    /**
     * 新增baseinfo
     */
    @RequiresPermissions("system:baseinfo:add")
    @Log(title = "baseinfo", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PatientBasicInfo patientBasicInfo)
    {
        return toAjax(patientBasicInfoService.insertPatientBasicInfo(patientBasicInfo));
    }

    /**
     * 修改baseinfo
     */
    @RequiresPermissions("system:baseinfo:edit")
    @Log(title = "baseinfo", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PatientBasicInfo patientBasicInfo)
    {
        return toAjax(patientBasicInfoService.updatePatientBasicInfo(patientBasicInfo));
    }

    /**
     * 删除baseinfo
     */
    @RequiresPermissions("system:baseinfo:remove")
    @Log(title = "baseinfo", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(patientBasicInfoService.deletePatientBasicInfoByIds(ids));
    }
}
