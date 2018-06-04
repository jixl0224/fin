/*#
 lib=snsoft/fin/bas.js
#*/
package snsoft.fin.bas.comm;

import js.JSCode;
import js.JSObject;
import xjs.core.Xjs;
import xjs.table.DefaultListener;
import xjs.table.Table;
import xjs.ui.util.UIUtil;

/**
 * <p>标题：界面打开监听类</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年6月5日上午5:56:08</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class FinOpenMuiListener extends DefaultListener
{
	protected UIInfo info;

	public FinOpenMuiListener(JSObject<Object> params)
	{
		Xjs.apply(this, params);
	}

	protected void oncmd_openUI(Table table)
	{
		if (!table.dataSet.isOpen())
		{
			return;
		}
		String muiid = $or(info.muiid, UIUtil.getUiid(info.funcid));
		JSObject<Object> pm = $o();
		Xjs.apply(pm, info.pm);
		if ($bool(info.initMapper))
		{
			for (String name : $names(info.initMapper))
			{
				String colname = info.initMapper.$get(name);
				pm.$set("InitValue." + name, table.dataSet.getValue(colname));
			}
		}
		UIUtil.wopenUI(info.title, muiid, pm);
	}

	protected void oncmd_newUI(Table table)
	{
		String muiid = $or(info.muiid, UIUtil.getUiid(info.funcid));
		JSObject<Object> pm = $o();
		Xjs.apply(pm, info.pm);
		if ($bool(info.initMapper))
		{
			for (String name : $names(info.initMapper))
			{
				pm.$set("InitValue." + name, "<NULL>");
			}
		}
		UIUtil.wopenUI(info.title, muiid, pm);
	}

	@JSCode(jscode = "false")
	public static class UIInfo
	{
		public String			title;
		public String			funcid;
		public String			muiid;
		public JSObject<Object>	pm;
		public JSObject<String>	initMapper;
	}
}
