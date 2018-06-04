package snsoft.fin.inv.service;

import java.util.List;
import snsoft.bas.service.QueryParams;
import snsoft.bas.service.QueryResults;
import snsoft.bas.sheet.busi.annotation.SheetInfo;
import snsoft.bas.sheet.service.BusiService.MainInnerGetter;
import snsoft.commons.annotation.AuthParam;
import snsoft.commons.spring.SpringBean;
import snsoft.fin.inv.vo.InvProfit;
import snsoft.fin.inv.vo.InvRecord;
import snsoft.sql.annotation.SqlColumn;

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

	QueryResults<InvRecord> queryInvRecordDetail(QueryInvRecordDetailParams params);

	QueryResults<InvProfit> queryInvProfitDetail(QueryInvRecordDetailParams params);

	public static class QueryInvRecordParams extends QueryParams
	{
		private static final long	serialVersionUID	= 4670937677229955230L;

		@SqlColumn
		private String				status;

		public String getStatus()
		{
			return status;
		}

		public void setStatus(String status)
		{
			this.status = status;
		}
	}

	@SheetInfo("INF-INV.Invest")
	public static class QueryInvRecordDetailParams extends QueryParams
	{
		private static final long	serialVersionUID	= 4069686930870597214L;

		@SqlColumn(flags = 8)
		private String				invicode;

		@MainInnerGetter
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
