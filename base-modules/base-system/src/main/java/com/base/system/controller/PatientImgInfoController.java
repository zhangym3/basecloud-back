package com.base.system.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.base.system.domain.Classification;
import com.base.system.domain.vo.ClassificationTreeVo;
import com.base.system.service.IClassificationService;
import com.base.system.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.base.common.log.annotation.Log;
import com.base.common.log.enums.BusinessType;
import com.base.common.security.annotation.RequiresPermissions;
import com.base.system.domain.PatientImgInfo;
import com.base.system.service.IPatientImgInfoService;
import com.base.common.core.web.controller.BaseController;
import com.base.common.core.web.domain.AjaxResult;
import com.base.common.core.utils.poi.ExcelUtil;
import com.base.common.core.web.page.TableDataInfo;

/**
 * imginfoController
 * 
 * @author walker
 * @date 2025-04-25
 */
@RestController
@RequestMapping("/imginfo")
public class PatientImgInfoController extends BaseController
{
    @Autowired
    private IPatientImgInfoService patientImgInfoService;

    @Autowired
    private IClassificationService classificationService;

    @Autowired
    private MinioService minioService;
    /**
     * 查询imginfo列表
     */
    @RequiresPermissions("system:info:list")
    @GetMapping("/list")
    public TableDataInfo list(PatientImgInfo patientImgInfo)
    {
        startPage();
        List<PatientImgInfo> list = patientImgInfoService.selectPatientImgInfoList(patientImgInfo);
        return getDataTable(list);
    }

    /**
     * 导出imginfo列表
     */
    @RequiresPermissions("system:info:export")
    @Log(title = "imginfo", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PatientImgInfo patientImgInfo)
    {
        List<PatientImgInfo> list = patientImgInfoService.selectPatientImgInfoList(patientImgInfo);
        ExcelUtil<PatientImgInfo> util = new ExcelUtil<PatientImgInfo>(PatientImgInfo.class);
        util.exportExcel(response, list, "imginfo数据");
    }

    /**
     * 获取imginfo详细信息
     */
    @RequiresPermissions("system:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(patientImgInfoService.selectPatientImgInfoById(id));
    }

    /**
     * 新增imginfo
     */
    @RequiresPermissions("system:info:add")
    @Log(title = "imginfo", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PatientImgInfo patientImgInfo)
    {
        return toAjax(patientImgInfoService.insertPatientImgInfo(patientImgInfo));
    }

    /**
     * 修改imginfo
     */
    @RequiresPermissions("system:info:edit")
    @Log(title = "imginfo", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PatientImgInfo patientImgInfo)
    {
        return toAjax(patientImgInfoService.updatePatientImgInfo(patientImgInfo));
    }

    /**
     * 删除imginfo
     */
    @RequiresPermissions("system:info:remove")
    @Log(title = "imginfo", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(patientImgInfoService.deletePatientImgInfoByIds(ids));
    }
    @RequiresPermissions("system:info:list")
    @GetMapping("/listTreeData")
    public AjaxResult listTreeData(String seeDoctorId){

        List<ClassificationTreeVo> list = new ArrayList<>();
        List<Classification> cls = classificationService.selectClassificationTreeData(seeDoctorId);

        List<PatientImgInfo> piis = patientImgInfoService.selectPatientImgInfoByseeDoctorId(seeDoctorId);
        cls.forEach(cl -> {

            ClassificationTreeVo clstvo = new ClassificationTreeVo();
            clstvo.setData(cl);
            clstvo.setId(cl.getId());
            clstvo.setParentId(cl.getParentId());
            clstvo.setLabel(cl.getClassificationName());
            clstvo.setType(1);
            list.add(clstvo);

        });
        piis.forEach(pii -> {
            ClassificationTreeVo clstvo = new ClassificationTreeVo();
            clstvo.setData(pii);
            clstvo.setId(pii.getId());
            clstvo.setParentId(pii.geClassificationId());
            clstvo.setLabel(pii.getClassificationName());
            clstvo.setType(2);

            list.add(clstvo);
        });

        return success(list);
    }

    @RequiresPermissions("system:info:list")
    @GetMapping("/listDataByClassificationIds")
    public AjaxResult listDataByClassificationIds(String seeDoctorId,String ids){

        List<PatientImgInfo> list = this.patientImgInfoService.selectPatientImgInfoByClassificationIds(seeDoctorId, ids);

        list.forEach(l -> {

            l.setPresignedObjectUrl(minioService.getObjectUrl(l.getBucket(), l.getObjName()));

        });
        return success(list);

    }

    @RequiresPermissions("system:info:list")
    @GetMapping("/listDataByImgIds")
    public AjaxResult listDataByImgIds(@RequestParam(name = "seeDoctorId")String seeDoctorId,@RequestParam(name = "ids") String ids){

        List<PatientImgInfo> imgs = this.patientImgInfoService.listDataByImgIds(seeDoctorId, Arrays.asList(ids.split(",")));
        imgs.forEach(l -> {

            l.setPresignedObjectUrl(minioService.getObjectUrl(l.getBucket(), l.getObjName()));

        });
        return success(imgs);

    }
}
