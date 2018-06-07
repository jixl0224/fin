/*#
 lib=snsoft/fin/inv.js
#*/
package snsoft.fin.inv;

import js.FunctionCall;
import xjs.dx.DataSet;
import xjs.dx.DataSetEvent;
import xjs.table.DefaultListener;

/**
 * <p>标题：盈余计算监听类</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：冀小雷</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2018年6月7日上午8:39:31</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class ProfitListener extends DefaultListener
{
	@Override
	public void dataSetFieldPosted(DataSet dataSet, DataSetEvent event)
	{
		if ("curfcy".equals(event.columnName))
		{
			FunctionCall call = new FunctionCall($getAsFunction("setAccfcy"), this, new Object[] { dataSet });
			lockInvoke(call);
			setAccfcy(dataSet);
		} else if ("accfcy".equals(event.columnName))
		{
			FunctionCall call = new FunctionCall($getAsFunction("setCurfcy"), this, new Object[] { dataSet });
			lockInvoke(call);
		}
	}

	private int lock = 0;

	void lockInvoke(FunctionCall call)
	{
		if (lock > 0)
		{
			return;
		}
		try
		{
			lock++;
			call.func.apply(call.scorp, call.args);
		} finally
		{
			lock--;
		}
	}

	void setAccfcy(DataSet dataSet)
	{
		double curfcy = dataSet.getValueAsDouble("curfcy");
		if ($bool(curfcy))
		{
			double lastacc = getLastAccfcy(dataSet);
			double thisacc = $bool(lastacc) ? lastacc + curfcy : curfcy;
			dataSet.setValue("accfcy", thisacc);
		}
	}

	double getLastAccfcy(DataSet dataSet)
	{
		int col = dataSet.columnAt("ym");
		String curym = (String) dataSet.getValue(col);
		int year = js.Number.obj2int(curym.substring(0, 4), 0);
		int month = js.Number.obj2int(curym.substring(5, 7), 0) - 1;
		js.Date date = new js.Date(year, month - 1, 1);
		String lastym = date.format(2);
		lastym = lastym.substring(0, lastym.lastIndexOf("-"));
		for (int row = 0; row < dataSet.getRowCount(); row++)
		{
			if (lastym == dataSet.getValue(col, row))
			{
				return (double) dataSet.getValue("accfcy", row);
			}
		}
		return (Double) undefined;
	}

	void setCurfcy(DataSet dataSet)
	{
		double curacc = dataSet.getValueAsDouble("accfcy");
		if ($bool(curacc))
		{
			double lastacc = getLastAccfcy(dataSet);
			double thiscur = $bool(lastacc) ? curacc - lastacc : curacc;
			dataSet.setValue("curfcy", thiscur);
		}
	}
}
