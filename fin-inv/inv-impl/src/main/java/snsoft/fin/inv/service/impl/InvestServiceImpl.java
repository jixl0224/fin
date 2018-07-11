package snsoft.fin.inv.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import snsoft.api.bas.QueryResults;
import snsoft.api.sql.QueryColumn;
import snsoft.commons.util.ArrayUtils;
import snsoft.commons.util.DateUtils;
import snsoft.dx.DBUtils;
import snsoft.dx.Database;
import snsoft.dx.DefaultDAO;
import snsoft.dx.util.ParamUtils;
import snsoft.fin.inv.service.InvestService;
import snsoft.fin.inv.vo.InvProfit;
import snsoft.fin.inv.vo.InvRecord;
import snsoft.fin.inv.vo.InvYm;
import snsoft.sql.SqlExpr;

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
		/**
		 * 已经生效的，且已经起息还没有终止的
		 */
		Optional.<Date> ofNullable(params.getUncalcdate()).ifPresent(date -> {
			Object[] invicodes = DBUtils.read("inv_ym", db -> {
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM");
				String ym = format.format(date);
				int year = DateUtils.getDateYear(date);
				int month = DateUtils.getDateMonth(date);
				Date bedate = DateUtils.toDate(year, month + 1, 0);
				String sql = "select m.invicode from inv_record m left join inv_ym g";
				sql += " on m.invicode=g.invicode and " + SqlExpr.columnEqValue("ym", ym);
				sql += " where status='70' and ym is null";
				sql += " and " + new SqlExpr(SqlExpr.GE, SqlExpr.constExpr(bedate), SqlExpr.id("bedate")).toString(db.getDialect());
				Object[][] values = db.query3(sql);
				return values.length == 0 ? new Object[0] : ArrayUtils.getArray2E(values, 0);
			});
			params.addExtQueryParams(new QueryColumn("invicode", SqlExpr.IN, invicodes));
		});
		Database.QueryParams p = ParamUtils.buildDBQueryParams(params);
		List<InvRecord> list = new DefaultDAO<InvRecord>(InvRecord.class).queryList(p);
		QueryResults<InvRecord> results = new QueryResults<>(list);
		results.setTotalRows(p.totalRows);
		return results;
	}

	@Override
	public void saveInvRecord(List<InvRecord> records)
	{
		new DefaultDAO<InvRecord>(InvRecord.class).save(records);
	}

	@Override
	public QueryResults<InvRecord> queryInvRecordDetail(QueryInvRecordDetailParams params)
	{
		Database.QueryParams p = ParamUtils.buildDBQueryParams(params);
		List<InvRecord> list = new DefaultDAO<InvRecord>(InvRecord.class).queryList(p);
		QueryResults<InvRecord> results = new QueryResults<>(list);
		results.setTotalRows(p.totalRows);
		return results;
	}

	@Override
	public QueryResults<InvYm> queryInvProfitDetail(QueryInvRecordDetailParams params)
	{
		Database.QueryParams p = ParamUtils.buildDBQueryParams(params);
		List<InvYm> list = new DefaultDAO<InvYm>(InvYm.class).queryList(p);
		QueryResults<InvYm> results = new QueryResults<>(list);
		results.setTotalRows(p.totalRows);
		return results;
	}

	@Override
	public List<InvProfit> queryInvProfit(QueryInvProfitParams params)
	{
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM");
		Optional.<Date> ofNullable(params.getYm()).ifPresent(ym -> {
			params.addExtQueryParams(new QueryColumn("ym", format.format(ym)));
		});
		//
		Optional.<Date> ofNullable(params.getYmfm()).ifPresent(ymfm -> {
			params.addExtQueryParams(new QueryColumn("ym", SqlExpr.GE, format.format(ymfm)));
		});
		Optional.<Date> ofNullable(params.getYmto()).ifPresent(ymto -> {
			params.addExtQueryParams(new QueryColumn("ym", SqlExpr.LE, format.format(ymto)));
		});
		return new DefaultDAO<InvProfit>(InvProfit.class).queryList(ParamUtils.buildDBQueryParams(params));
	}
}
