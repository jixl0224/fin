package snsoft.fin.inv.service.impl;

import java.util.List;
import snsoft.bas.service.QueryResults;
import snsoft.dx.Database;
import snsoft.dx.DefaultDAO;
import snsoft.fin.inv.service.InvestService;
import snsoft.fin.inv.vo.InvProfit;
import snsoft.fin.inv.vo.InvRecord;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年6月5日上午5:45:34</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class InvestServiceImpl implements InvestService
{
	@Override
	public QueryResults<InvRecord> queryInvRecord(QueryInvRecordParams params)
	{
		Database.QueryParams p = params.buildDBQueryParams();
		List<InvRecord> list = new DefaultDAO<InvRecord>().queryList(InvRecord.class, p);
		QueryResults<InvRecord> results = new QueryResults<>(list);
		results.setPageTotalRows(p.totalRows);
		return results;
	}

	@Override
	public void saveInvRecord(List<InvRecord> records)
	{
		new DefaultDAO<InvRecord>().save(records);
	}

	@Override
	public QueryResults<InvRecord> queryInvRecordDetail(QueryInvRecordDetailParams params)
	{
		Database.QueryParams p = params.buildDBQueryParams();
		List<InvRecord> list = new DefaultDAO<InvRecord>().queryList(InvRecord.class, p);
		QueryResults<InvRecord> results = new QueryResults<>(list);
		results.setPageTotalRows(p.totalRows);
		return results;
	}

	@Override
	public QueryResults<InvProfit> queryInvProfitDetail(QueryInvRecordDetailParams params)
	{
		Database.QueryParams p = params.buildDBQueryParams();
		List<InvProfit> list = new DefaultDAO<InvProfit>().queryList(InvProfit.class, p);
		QueryResults<InvProfit> results = new QueryResults<>(list);
		results.setPageTotalRows(p.totalRows);
		return results;
	}
}
