package snsoft.fin.inv.service.impl;

import java.util.List;
import snsoft.bas.service.util.NullQueryParams;
import snsoft.dx.DefaultDAO;
import snsoft.fin.inv.service.InvkindService;
import snsoft.fin.inv.vo.InvKind;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年6月5日上午5:48:47</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class InvkindServiceImpl implements InvkindService
{
	@Override
	public List<InvKind> query(NullQueryParams params)
	{
		return new DefaultDAO<InvKind>().queryList(InvKind.class, params.buildDBQueryParams());
	}

	@Override
	public void save(List<InvKind> records)
	{
		new DefaultDAO<InvKind>().save(records);
	}
}
