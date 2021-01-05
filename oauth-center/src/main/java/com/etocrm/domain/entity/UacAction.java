
package com.etocrm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UacAction  {

	/**
	 * 资源路径
	 */
	private String url;

	/**
	 * 权限名称
	 */
	private String actionName;

	/**
	 * 权限编码
	 */
	private String actionCode;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remark;

//	/**
//	 * 菜单ID
//	 */
//	private Long menuId;
//
//	/**
//	 * 菜单ID
//	 */
//	private List<Long> menuIdList;
}