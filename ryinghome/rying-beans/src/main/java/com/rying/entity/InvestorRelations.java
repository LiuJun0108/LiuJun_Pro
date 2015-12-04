package com.rying.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 投资者关系
 * 
 * @author Jun.L
 *
 */

@Entity
@Table(name = "t_investor_relations")
public class InvestorRelations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1220734411549267404L;

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String filePath;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;
	@ManyToOne
	private SysUser publishUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public SysUser getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(SysUser publishUser) {
		this.publishUser = publishUser;
	}

}
