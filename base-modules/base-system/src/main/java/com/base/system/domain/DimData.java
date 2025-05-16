package com.base.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chens
 */
@Data
public class DimData  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String label;
	private String dId;
	private String pId;
	private List<DimData> children;
}
