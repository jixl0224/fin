/*#
 lib=snsoft/fin/inv.js
#*/
package snsoft.fin.inv;

import xjs.dx.DataSet;
import xjs.table.ColumnValueAccess;
import xjs.table.TableColumn;

/**
 * <p>标题：年月转换类</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年6月7日上午8:12:44</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年6月7日上午8:15:00</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class YMAccess extends ColumnValueAccess
{
	@Override
	public Object getValue(DataSet dataSet, TableColumn column, int row, ColumnValueAccessEvent e)
	{
		String ym = (String) dataSet.getValue(column.name, row);
		//		if (ym != null)
		//		{
		//			return ym + "-1";
		//		}
		return ym;
	}

	@Override
	public void setValue(DataSet dataSet, TableColumn column, Object value, ColumnValueAccessEvent e)
	{
		if (value == null)
		{
			dataSet.setValue(column.name, null);
		}
		String ymd = (String) value;
		String ym = ymd.substring(0, ymd.lastIndexOf("-"));
		dataSet.setValue(column.name, ym);
	}
}
