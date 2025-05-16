package com.base.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.base.system.domain.Classification;
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
import com.base.system.service.IClassificationService;
import com.base.common.core.web.controller.BaseController;
import com.base.common.core.web.domain.AjaxResult;
import com.base.common.core.utils.poi.ExcelUtil;

/**
 * classificationController
 * 
 * @author zhangym
 * @date 2025-04-18
 */
@RestController
@RequestMapping("/classification")
public class ClassificationController extends BaseController {
    @Autowired
    private IClassificationService classificationService;

    /**
     * 查询classification列表
     */
    @RequiresPermissions("system:classification:list")
    @GetMapping("/list")
    public AjaxResult list(Classification Classification) {
        List<Classification> list = classificationService.selectClassificationList(Classification);
        return success(list);
    }

    /**
     * 导出classification列表
     */
    @RequiresPermissions("system:classification:export")
    @Log(title = "classification", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Classification Classification) {
        List<Classification> list = classificationService.selectClassificationList(Classification);
        ExcelUtil<Classification> util = new ExcelUtil<Classification>(Classification.class);
        util.exportExcel(response, list, "classification数据");
    }

    /**
     * 获取classification详细信息
     */
    @RequiresPermissions("system:classification:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(classificationService.selectClassificationById(id));
    }

    /**
     * 新增classification
     */
    @RequiresPermissions("system:classification:add")
    @Log(title = "classification", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Classification Classification) {
        return toAjax(classificationService.insertClassification(Classification));
    }

    /**
     * 修改classification
     */
    @RequiresPermissions("system:classification:edit")
    @Log(title = "classification", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Classification Classification) {
        return toAjax(classificationService.updateClassification(Classification));
    }

    /**
     * 删除classification
     */
    @RequiresPermissions("system:classification:remove")
    @Log(title = "classification", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(classificationService.deleteClassificationByIds(ids));
    }


    @RequiresPermissions("system:classification:list")
    @GetMapping("/listClassificationTree")
    public AjaxResult listClassificationTree(String seeDoctorId) {
        List<Classification> list = classificationService.selectClassificationTreeData(seeDoctorId);
        return success(list);
    }


    @RequiresPermissions("system:classification:list")
    @GetMapping("/listByParentId")
    public AjaxResult selectClassificationByParentId(String parentId) {
        List<Classification> list = classificationService.selectClassificationByParentId(parentId);
        return success(list);
    }

}