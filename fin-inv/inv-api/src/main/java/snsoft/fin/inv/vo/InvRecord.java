package snsoft.fin.inv.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import snsoft.bas.sheet.dft.annotation.DefaultValue;
import snsoft.dx.VO;

/**
 * <p>标题：投资记录</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年5月19日下午5:05:25</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "inv_record")
public class InvRecord extends VO
{
	private static final long	serialVersionUID	= 8485187138931902735L;
	/**主键*/
	@Id
	@Column
	@DefaultValue("Accode:FIN-BAS.InnerCode")
	private String				invicode;

	/**投资记录编码*/
	@Column
	@DefaultValue("SheetAccode:FIN-INV.Invest")
	private String				invcode;

	/**投资用户*/
	@Column
	@DefaultValue("%EnvParam(USERCODE)")
	private String				usercode;

	/**渠道*/
	@Column
	private String				invkind;

	/**投资金额*/
	@Column
	private BigDecimal			invfcy;

	/**收益率*/
	@Column
	private BigDecimal			ratio;

	/**起息日期*/
	@Column
	@DefaultValue(value = "CURDATE")
	private Date				bedate;

	/**停息日期*/
	@Column
	private Date				ledate;

	/**累计盈亏*/
	@Column
	private BigDecimal			accfcy;

	/**备注*/
	@Column
	private String				remark;

	/**状态*/
	@Column
	@DefaultValue("10")
	private String				status;

	/**创建时间*/
	@Column
	@DefaultValue(value = "CURTIME", uivalue = "new Xjs.dx.util.CurrentDateDefaultValue({serverTime:true})")
	private Date				predate;

	/**修改时间*/
	@Column
	private Date				modifydate;

	@OneToMany
	@JoinColumn(name = "invicode")
	private List<InvProfit>		details;

	public String getInvicode()
	{
		return invicode;
	}

	public void setInvicode(String invicode)
	{
		this.invicode = invicode;
	}

	public String getInvcode()
	{
		return invcode;
	}

	public void setInvcode(String invcode)
	{
		this.invcode = invcode;
	}

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

	public BigDecimal getInvfcy()
	{
		return invfcy;
	}

	public void setInvfcy(BigDecimal invfcy)
	{
		this.invfcy = invfcy;
	}

	public BigDecimal getRatio()
	{
		return ratio;
	}

	public void setRatio(BigDecimal ratio)
	{
		this.ratio = ratio;
	}

	public Date getBedate()
	{
		return bedate;
	}

	public void setBedate(Date bedate)
	{
		this.bedate = bedate;
	}

	public Date getLedate()
	{
		return ledate;
	}

	public void setLedate(Date ledate)
	{
		this.ledate = ledate;
	}

	public BigDecimal getAccfcy()
	{
		return accfcy;
	}

	public void setAccfcy(BigDecimal accfcy)
	{
		this.accfcy = accfcy;
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

	public List<InvProfit> getDetails()
	{
		if (details == null)
		{
			details = new ArrayList<>();
		}
		return details;
	}

	public void setDetails(List<InvProfit> details)
	{
		this.details = details;
	}
}
