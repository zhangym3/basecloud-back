package com.base.system.mapper;

import java.util.List;

import com.base.system.domain.Classification;

/**
 * classificationMapper接口
 * 
 * @author zhangym
 * @date 2025-04-18
 */
public interface ClassificationMapper
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
     * 删除classification
     * 
     * @param id classification主键
     * @return 结果
     */
    public int deleteClassificationById(String id);

    /**
     * 批量删除classification
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClassificationByIds(String[] ids);

    public List<Classification> selectClassificationTreeData(String seeDoctorId);
    
    public List<Classification> selectClassificationByParentId(String parentId);


}
