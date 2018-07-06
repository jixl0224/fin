package snsoft.fin.inv.vo;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Table;
import snsoft.api.dx.VO;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年6月11日上午6:01:26</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Table(name = "inv_profit")
public class InvProfit extends VO
{
	private static final long	serialVersionUID	= -4605207459553471358L;
	/**主键*/
	@Column
	private String				invicode;
	/**投资号*/
	@Column
	private String				invcode;
	/**投资用户*/
	@Column
	private String				usercode;
	/**渠道*/
	@Column
	private String				invkind;
	/**状态*/
	@Column
	private String				status;
	/**投资金额*/
	@Column
	private BigDecimal			invfcy;
	/**收益率*/
	@Column
	private BigDecimal			ratio;
	/**年月*/
	@Column
	private String				ym;
	/**累计盈亏*/
	@Column
	private BigDecimal			accfcy;
	/**当期盈亏*/
	@Column
	private BigDecimal			curfcy;

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

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
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

	public String getYm()
	{
		return ym;
	}

	public void setYm(String ym)
	{
		this.ym = ym;
	}

	public BigDecimal getAccfcy()
	{
		return accfcy;
	}

	public void setAccfcy(BigDecimal accfcy)
	{
		this.accfcy = accfcy;
	}

	public BigDecimal getCurfcy()
	{
		return curfcy;
	}

	public void setCurfcy(BigDecimal curfcy)
	{
		this.curfcy = curfcy;
	}
}
