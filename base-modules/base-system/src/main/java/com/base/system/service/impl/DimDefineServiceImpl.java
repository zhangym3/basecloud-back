package com.base.system.service.impl;

import com.base.common.core.utils.DateUtils;
import com.base.system.domain.DimData;
import com.base.system.domain.DimDefine;
import com.base.system.mapper.DimDefineMapper;
import com.base.system.service.IDimDefineService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DimDefineServiceImpl implements IDimDefineService {

    @Autowired
    DimDefineMapper dimDefineMapper;

    @Override
    public DimDefine selecDimDefineById(String dimId) {
        return dimDefineMapper.selecDimDefineById(dimId);
    }

    public List<DimData> getData(DimDefine dim, String parentId,String code,String label){


        String dimId = dim.getDimId();

        StringBuffer sqlBuilder = new StringBuffer("Select ");

        sqlBuilder.append("a.");

        if (dim==null) {
            return new ArrayList<>();
        }
        sqlBuilder.append(dim.getDimKeyCol());

        sqlBuilder.append(" as `code`,");

        sqlBuilder.append("a.");

        sqlBuilder.append(dim.getDimValueCol());

        sqlBuilder.append(" as `label`");

        if (!StringUtils.isBlank(dim.getDimIdCol())) {

            sqlBuilder.append(",a.");
            sqlBuilder.append(dim.getDimIdCol());
            sqlBuilder.append(" as dId from (");
        } else {
            sqlBuilder.append(" from (");
        }

        sqlBuilder.append(dim.getDimSql());

        sqlBuilder.append(") a where 1=1 ");

        if(!StringUtils.isBlank(parentId) ) {
            sqlBuilder.append("and a.");
            sqlBuilder.append(dim.getRelCol());
            sqlBuilder.append("='");
            sqlBuilder.append(parentId);
            sqlBuilder.append("' ");

        }

        if(StringUtils.isNotBlank(code)){

            sqlBuilder.append("and a.");
            sqlBuilder.append(dim.getDimKeyCol());
            sqlBuilder.append("='");
            sqlBuilder.append(code);
            sqlBuilder.append("' ");

        }

        if(StringUtils.isNotBlank(label)){

            sqlBuilder.append("and a.");
            sqlBuilder.append(dim.getDimValueCol());
            sqlBuilder.append(" like '%");
            sqlBuilder.append(label);
            sqlBuilder.append("%' ");

        }
        if(!StringUtils.isBlank(dim.getDimOrderCol()) ) {

            sqlBuilder.append("order by a.");
            sqlBuilder.append(dim.getDimOrderCol());
        }

        if(!StringUtils.isBlank(dim.getDimSortDirection()) ) {



            sqlBuilder.append(dim.getDimSortDirection().equals("1") ? " asc" : " desc");

        }


        return dimDefineMapper.execSQL(sqlBuilder.toString());

    }
    /**
     * 查询码表管理
     *
     * @param dimId 码表管理主键
     * @return 码表管理
     */
    @Override
    public DimDefine selectDimDefineByDimId(String dimId)
    {
        return dimDefineMapper.selectDimDefineByDimId(dimId);
    }

    /**
     * 查询码表管理列表
     *
     * @param DimDefine 码表管理
     * @return 码表管理
     */
    @Override
    public List<DimDefine> selectDimDefineList(DimDefine DimDefine)
    {
        return dimDefineMapper.selectDimDefineList(DimDefine);
    }

    /**
     * 新增码表管理
     *
     * @param DimDefine 码表管理
     * @return 结果
     */
    @Override
    public int insertDimDefine(DimDefine DimDefine)
    {
        DimDefine.setCreateTime(DateUtils.getNowDate());
        return dimDefineMapper.insertDimDefine(DimDefine);
    }

    /**
     * 修改码表管理
     *
     * @param DimDefine 码表管理
     * @return 结果
     */
    @Override
    public int updateDimDefine(DimDefine DimDefine)
    {
        return dimDefineMapper.updateDimDefine(DimDefine);
    }

    /**
     * 批量删除码表管理
     *
     * @param dimIds 需要删除的码表管理主键
     * @return 结果
     */
    @Override
    public int deleteDimDefineByDimIds(String[] dimIds)
    {
        return dimDefineMapper.deleteDimDefineByDimIds(dimIds);
    }

    /**
     * 删除码表管理信息
     *
     * @param dimId 码表管理主键
     * @return 结果
     */
    @Override
    public int deleteDimDefineByDimId(String dimId)
    {
        return dimDefineMapper.deleteDimDefineByDimId(dimId);
    }
}
