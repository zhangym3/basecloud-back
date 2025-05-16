package com.base.system.service;

import java.util.List;
import com.base.system.domain.Classification;

/**
 * classificationService接口
 * 
 * @author zhangym
 * @date 2025-04-18
 */
public interface IClassificationService
{
    /**
     * 查询classification
     * 
     * @param id classification主键
     * @return classification
     */
    public Classification selectClassificationById(String id);

    /**
     * 查询classification列表
     * 
     * @param Classification classification
     * @return classification集合
     */
    public List<Classification> selectClassificationList(Classification Classification);

    /**
     * 新增classification
     * 
     * @param Classification classification
     * @return 结果
     */
    public int insertClassification(Classification Classification);

    /**
     * 修改classification
     * 
     * @param Classification classification
     * @return 结果
     */
    public int updateClassification(Classification Classification);

    /**
     * 批量删除classification
     * 
     * @param ids 需要删除的classification主键集合
     * @return 结果
     */
    public int deleteClassificationByIds(String[] ids);

    /**
     * 删除classification信息
     * 
     * @param id classification主键
     * @return 结果
     */
    public int deleteClassificationById(String id);

    public List<Classification> selectClassificationTreeData(String seeDoctorId);
    public List<Classification> selectClassificationByParentId(String parentId);

}
