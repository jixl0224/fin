package snsoft.fin.inv.service;

import java.util.List;
import snsoft.bas.service.util.NullQueryParams;
import snsoft.commons.annotation.AuthParam;
import snsoft.commons.spring.SpringBean;
import snsoft.fin.inv.vo.InvKind;

/**
 * <p>标题：投资渠道</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年6月5日上午5:47:50</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBean(name = "INF-INV.InvkindService", group = "${INF-INV.Name?}", version = "1")
public interface InvkindService
{
	@AuthParam(sheetCode = "FIN-INV.InvKind", opids = { "R", "C" })
	List<InvKind> query(NullQueryParams params);

	@AuthParam(sheetCode = "FIN-INV.InvKind", opids = { "C" })
	void save(List<InvKind> records);
}
