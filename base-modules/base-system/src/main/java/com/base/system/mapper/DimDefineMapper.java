package com.base.system.mapper;

import com.base.system.domain.DimData;
import com.base.system.domain.DimDefine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DimDefineMapper {

    @Select("Select * from t_dim_define where 1=1 and dim_id = #{dimId}")
    public DimDefine selecDimDefineById(String dimId);


    @Select("${sql}")
    public List<DimData> execSQL(String sql);


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
     * 删除码表管理
     *
     * @param dimId 码表管理主键
     * @return 结果
     */
    public int deleteDimDefineByDimId(String dimId);

    /**
     * 批量删除码表管理
     *
     * @param dimIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDimDefineByDimIds(String[] dimIds);

}
