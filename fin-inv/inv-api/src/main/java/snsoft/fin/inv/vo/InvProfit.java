package snsoft.fin.inv.vo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import snsoft.bas.sheet.dft.annotation.DefaultValue;
import snsoft.commons.annotation.VOField;
import snsoft.dx.VO;

/**
 * <p>标题：投资盈亏表</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年5月19日下午5:31:51</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class InvProfit extends VO
{
	private static final long	serialVersionUID	= 3844129865907496507L;

	/**主键*/
	@Id
	@Column
	@DefaultValue("Accode:FIN-BAS.InnerCode")
	private String				invgicode;

	/**外键*/
	@Column
	@NotNull(message = "投资盈亏外键不可为空")
	private String				invicode;

	/**年月*/
	@Column
	@Pattern(regexp = "\\d{4}\\-\\d{2}", message = "年月格式不正确")
	private String				ym;

	/**累计盈亏*/
	@Column
	@VOField(maxdeci = "2")
	private BigDecimal			accfcy;

	/**当期盈亏*/
	@Column
	@VOField(maxdeci = "2")
	private BigDecimal			curfcy;

	/**备注*/
	@Column
	private String				remark;

	/**创建时间*/
	@Column
	private Date				predate;

	public String getInvgicode()
	{
		return invgicode;
	}

	public void setInvgicode(String invgicode)
	{
		this.invgicode = invgicode;
	}

	public String getInvicode()
	{
		return invicode;
	}

	public void setInvicode(String invicode)
	{
		this.invicode = invicode;
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

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public Date getPredate()
	{
		return predate;
	}

	public void setPredate(Date predate)
	{
		this.predate = predate;
	}
}
