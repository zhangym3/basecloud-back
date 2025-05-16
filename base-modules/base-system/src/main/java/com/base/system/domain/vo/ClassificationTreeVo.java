package com.base.system.domain.vo;

import com.base.common.core.web.domain.TreeEntity;
import lombok.Data;

@Data
public class ClassificationTreeVo extends TreeEntity {

    private String id;
    private String label;
    private Object data;
    private int type;

}
