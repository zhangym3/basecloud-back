package com.base.system.service;


import com.base.system.domain.DimData;
import com.base.system.domain.DimDefine;

import java.util.List;

public interface IDimDefineService {

    public DimDefine selecDimDefineById(String dimId);

    public List<DimData> getData(DimDefine dim, String parentId,String code,String label);
    /**
     * 查询码表管理
     *
     * @param dimId 码表管理主键
     * @return 码表管理
     */
    public DimDefine selectDimDefineByDimId(String dimId);

    /**
     * 查询码表管理列表
     *
     * @param DimDefine 码表管理
     * @return 码表管理集合
     */
    public List<DimDefine> selectDimDefineList(DimDefine DimDefine);

    /**
     * 新增码表管理
     *
     * @param DimDefine 码表管理
     * @return 结果
     */
    public int insertDimDefine(DimDefine DimDefine);

    /**
     * 修改码表管理
     *
     * @param DimDefine 码表管理
     * @return 结果
     */
    public int updateDimDefine(DimDefine DimDefine);

    /**
     * 批量删除码表管理
     *
     * @param dimIds 需要删除的码表管理主键集合
     * @return 结果
     */
    public int deleteDimDefineByDimIds(String[] dimIds);

    /**
     * 删除码表管理信息
     *
     * @param dimId 码表管理主键
     * @return 结果
     */
    public int deleteDimDefineByDimId(String dimId);
}
