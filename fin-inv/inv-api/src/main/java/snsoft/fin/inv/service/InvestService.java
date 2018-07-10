package snsoft.fin.inv.service;

import java.util.Date;
import java.util.List;
import snsoft.api.bas.QueryParams;
import snsoft.api.bas.QueryResults;
import snsoft.api.service.AuthParam;
import snsoft.api.service.SpringBean;
import snsoft.api.sql.SqlColumn;
import snsoft.fin.inv.vo.InvProfit;
import snsoft.fin.inv.vo.InvRecord;
import snsoft.fin.inv.vo.InvYm;
import snsoft.sql.SqlExpr;

/**
 * <p>标题：投资功能模块服务</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年5月21日上午8:45:12</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBean(name = "INF-INV.InvestService", group = "${INF-INV.Name?}", version = "1")
public interface InvestService
{
	@AuthParam(sheetCode = "FIN-INV.Invest", opids = { "R", "C" })
	QueryResults<InvRecord> queryInvRecord(QueryInvRecordParams params);

	@AuthParam(sheetCode = "FIN-INV.Invest", opids = { "C" })
	void saveInvRecord(List<InvRecord> records);

	@AuthParam(sheetCode = "FIN-INV.Invest", opids = { "C" ,"R"})
	QueryResults<InvRecord> queryInvRecordDetail(QueryInvRecordDetailParams params);

	@AuthParam(sheetCode = "FIN-INV.Invest", opids = { "C" ,"R"},joinColumn="invicode")
	QueryResults<InvYm> queryInvProfitDetail(QueryInvRecordDetailParams params);

	@AuthParam(sheetCode = "FIN-INV.Invest", opids = { "R", "C" })
	List<InvProfit> queryInvProfit(QueryInvProfitParams params);

	public static class QueryInvProfitParams extends QueryParams
	{
		private static final long	serialVersionUID	= 5709928549516679913L;

		@SqlColumn
		String[]					invkind;

		private Date				ym;

		private Date				ymfm;

		private Date				ymto;

		public String[] getInvkind()
		{
			return invkind;
		}

		public void setInvkind(String[] invkind)
		{
			this.invkind = invkind;
		}

		public Date getYm()
		{
			return ym;
		}

		public void setYm(Date ym)
		{
			this.ym = ym;
		}

		public Date getYmfm()
		{
			return ymfm;
		}

		public void setYmfm(Date ymfm)
		{
			this.ymfm = ymfm;
		}

		public Date getYmto()
		{
			return ymto;
		}

		public void setYmto(Date ymto)
		{
			this.ymto = ymto;
		}
	}

	public static class QueryInvRecordParams extends QueryParams
	{
		private static final long	serialVersionUID	= 4670937677229955230L;

		@SqlColumn(column = "bedate", sqlop = SqlExpr.GE)
		private Date				bedatefm;

		@SqlColumn(column = "bedate", sqlop = SqlExpr.LT)
		private Date				bedateto;

		@SqlColumn(column = "predate", sqlop = SqlExpr.GE)
		private Date				predatefm;

		private Date				uncalcdate;

		@SqlColumn(column = "predate", sqlop = SqlExpr.LT)
		private Date				predateto;

		@SqlColumn
		private String[]			status;

		public Date getBedatefm()
		{
			return bedatefm;
		}

		public void setBedatefm(Date bedatefm)
		{
			this.bedatefm = bedatefm;
		}

		public Date getBedateto()
		{
			return bedateto;
		}

		public void setBedateto(Date bedateto)
		{
			this.bedateto = bedateto;
		}

		public Date getPredatefm()
		{
			return predatefm;
		}

		public void setPredatefm(Date predatefm)
		{
			this.predatefm = predatefm;
		}

		public Date getPredateto()
		{
			return predateto;
		}

		public void setPredateto(Date predateto)
		{
			this.predateto = predateto;
		}

		public Date getUncalcdate()
		{
			return uncalcdate;
		}

		public void setUncalcdate(Date uncalcdate)
		{
			this.uncalcdate = uncalcdate;
		}

		public String[] getStatus()
		{
			return status;
		}

		public void setStatus(String[] status)
		{
			this.status = status;
		}
	}

	public static class QueryInvRecordDetailParams extends QueryParams
	{
		private static final long	serialVersionUID	= 4069686930870597214L;

		@SqlColumn(flags = 8)
		private String				invicode;

		public String getInvicode()
		{
			return invicode;
		}

		public void setInvicode(String invicode)
		{
			this.invicode = invicode;
		}
	}
}
