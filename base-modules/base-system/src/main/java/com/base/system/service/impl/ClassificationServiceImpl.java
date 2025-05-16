package com.base.system.service.impl;

import java.util.List;

import com.base.common.core.utils.StringUtils;
import com.base.common.core.utils.uuid.UUID;
import com.base.system.domain.Classification;
import com.base.system.mapper.ClassificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.system.service.IClassificationService;

/**
 * classificationService业务层处理
 * 
 * @author zhangym
 * @date 2025-04-18
 */
@Service
public class ClassificationServiceImpl implements IClassificationService
{
    @Autowired
    private ClassificationMapper classificationMapper;

    /**
     * 查询classification
     * 
     * @param id classification主键
     * @return classification
     */
    @Override
    public Classification selectClassificationById(String id)
    {
        return classificationMapper.selectClassificationById(id);
    }

    /**
     * 查询classification列表
     * 
     * @param Classification classification
     * @return classification
     */
    @Override
    public List<Classification> selectClassificationList(Classification Classification)
    {
        return classificationMapper.selectClassificationList(Classification);
    }

    /**
     * 新增classification
     * 
     * @param Classification classification
     * @return 结果
     */
    @Override
    public int insertClassification(Classification Classification)
    {
        if(StringUtils.isEmpty(Classification.getId())) {

            Classification.setId(UUID.randomUUID().toString());

        }
        return classificationMapper.insertClassification(Classification);
    }

    /**
     * 修改classification
     * 
     * @param Classification classification
     * @return 结果
     */
    @Override
    public int updateClassification(Classification Classification)
    {
        return classificationMapper.updateClassification(Classification);
    }

    /**
     * 批量删除classification
     * 
     * @param ids 需要删除的classification主键
     * @return 结果
     */
    @Override
    public int deleteClassificationByIds(String[] ids)
    {
        return classificationMapper.deleteClassificationByIds(ids);
    }

    /**
     * 删除classification信息
     * 
     * @param id classification主键
     * @return 结果
     */
    @Override
    public int deleteClassificationById(String id)
    {
        return classificationMapper.deleteClassificationById(id);
    }

    @Override
    public List<Classification> selectClassificationTreeData(String seeDoctorId) {

        return classificationMapper.selectClassificationTreeData(seeDoctorId);
    }

    @Override
    public List<Classification> selectClassificationByParentId(String parentId){

        return classificationMapper.selectClassificationByParentId(parentId);
    }
}
