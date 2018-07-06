package snsoft.fin.inv.vo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import snsoft.api.dft.DefaultValue;
import snsoft.api.dx.VO;

/**
 * <p>标题：投资渠道</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年5月19日下午5:01:48</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "inv_kind")
public class InvKind extends VO
{
	private static final long	serialVersionUID	= -6798898240805375231L;
	/**用户*/
	@Id
	@Column
	@DefaultValue("%EnvParam(USERCODE)")
	private String				usercode;

	/**渠道*/
	@Column
	@DefaultValue("AutoAlloc:___")
	private String				invkind;

	/**名称*/
	@Column
	@Length(max = 64)
	private String				invname;

	/**说明*/
	@Column
	@Length(max = 512)
	private String				remark;

	/**状态*/
	@DefaultValue("70")
	@Column
	private String				status;

	/**创建时间*/
	@Column
	@DefaultValue(value = "CURTIME", uivalue = "new Xjs.dx.util.CurrentDateDefaultValue({serverTime:true})")
	private Date				predate;

	/**修改时间*/
	@Column
	private Date				modifydate;

	public String getUsercode()
	{
		return usercode;
	}

	public void setUsercode(String usercode)
	{
		this.usercode = usercode;
	}

	public String getInvkind()
	{
		return invkind;
	}

	public void setInvkind(String invkind)
	{
		this.invkind = invkind;
	}

	public String getInvname()
	{
		return invname;
	}

	public void setInvname(String invname)
	{
		this.invname = invname;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Date getPredate()
	{
		return predate;
	}

	public void setPredate(Date predate)
	{
		this.predate = predate;
	}

	public Date getModifydate()
	{
		return modifydate;
	}

	public void setModifydate(Date modifydate)
	{
		this.modifydate = modifydate;
	}
}
