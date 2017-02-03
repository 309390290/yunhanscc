package com.yunhan.scc.backto.web.entities.system;

import com.yunhan.scc.tools.component.module.query.QueryResult;

/**     
 * 项目名称：yunhan-scc-backto_2.0   
 * 类名称：DcLocationBacktoDo   
 * 类描述：   
 * 创建人：zzp
 * 创建时间：2016-7-13 下午2:38:16   
 * 修改人：
 * 修改时间： 
 * 修改备注：   
 * @version V0.1 
 */

public class DcLocationBacktoDo extends QueryResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
    private Long id;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 仓库层级
     */
    private Integer nodeLevel;
    /**
     *  名称
     */
    private String name;
    /**
     *编码
     */
    private String code;

    /**
     *  状态(0:启用,9:禁用)
     */
    private Integer status;
    /**
     *  排序序号
     */
    private Integer index;
    /**
     *  收货地址
     */
    private String paddr;
    /**
     *  联系人
     */
    private String contact;
    /**
     *  联系电话
     */
    private String contactNumber;
    /**
     * 仓库类型  1:门店仓库   2:门店组仓库   3：采购商仓库
     */
    private Integer stgTpCd;
    /**
     * 所在仓位数据条数  (非数据库字段) added by luohoudong
     */
    private Long dataNumbs;
    /**
     * 是否补单标记
     */
    private String isSupplement;
    
	public String getIsSupplement() {
		return isSupplement;
	}
	public void setIsSupplement(String isSupplement) {
		this.isSupplement = isSupplement;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * @return the nodeLevel
	 */
	public Integer getNodeLevel() {
		return nodeLevel;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}
	/**
	 * @return the paddr
	 */
	public String getPaddr() {
		return paddr;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * @return the stgTpCd
	 */
	public Integer getStgTpCd() {
		return stgTpCd;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * @param nodeLevel the nodeLevel to set
	 */
	public void setNodeLevel(Integer nodeLevel) {
		this.nodeLevel = nodeLevel;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	/**
	 * @param paddr the paddr to set
	 */
	public void setPaddr(String paddr) {
		this.paddr = paddr;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * @param stgTpCd the stgTpCd to set
	 */
	public void setStgTpCd(Integer stgTpCd) {
		this.stgTpCd = stgTpCd;
	}
	/**
	 * @return the dataNumbs
	 */
	public Long getDataNumbs() {
		return dataNumbs;
	}
	/**
	 * @param dataNumbs the dataNumbs to set
	 */
	public void setDataNumbs(Long dataNumbs) {
		this.dataNumbs = dataNumbs;
	}
}

