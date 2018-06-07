Xjs.loadedXjs.push("snsoft/fin/inv");
/*snsoft/fin/inv/ProfitListener.java*/
Xjs.namespace("snsoft.fin.inv");
snsoft.fin.inv.ProfitListener=Xjs.extend(Xjs.table.DefaultListener,{
  _js$className_:"snsoft.fin.inv.ProfitListener",
    lock:0,
    /*snsoft.fin.inv.ProfitListener.dataSetFieldPosted*/
    dataSetFieldPosted:function(dataSet,event)
    {
        if("curfcy"==event.columnName)
        {
            var call = {func:this.setAccfcy,scorp:this,args:[dataSet]};
            this.lockInvoke(call);
            this.setAccfcy(dataSet);
        } else if("accfcy"==event.columnName)
        {
            var call = {func:this.setCurfcy,scorp:this,args:[dataSet]};
            this.lockInvoke(call);
        }
    },
    /*snsoft.fin.inv.ProfitListener.lockInvoke*/
    lockInvoke:function(call)
    {
        if(this.lock > 0)
        {
            return;
        }
        try
        {
            this.lock++;
            call.func.apply(call.scorp,call.args);
        }finally
        {
            this.lock--;
        }
    },
    /*snsoft.fin.inv.ProfitListener.setAccfcy*/
    setAccfcy:function(dataSet)
    {
        var curfcy = dataSet.getValue("curfcy");
        if(curfcy)
        {
            var lastacc = this.getLastAccfcy(dataSet),
                thisacc = lastacc ? lastacc + curfcy : curfcy;
            dataSet.setValue("accfcy",thisacc);
        }
    },
    /*snsoft.fin.inv.ProfitListener.getLastAccfcy*/
    getLastAccfcy:function(dataSet)
    {
        var col = dataSet.columnAt("ym"),
            curym = dataSet.getValue(col),
            year = Number.obj2int(curym.substring(0,4),0),
            month = Number.obj2int(curym.substring(5,7),0) - 1,
            date = new Date(year,month - 1,1),
            lastym = date.format(2);
        lastym = lastym.substring(0,lastym.lastIndexOf("-"));
        for(var row=0;row < dataSet.getRowCount();row++)
        {
            if(lastym == dataSet.getValue(col,row))
            {
                return dataSet.getValue("accfcy",row);
            }
        }
        return;
    },
    /*snsoft.fin.inv.ProfitListener.setCurfcy*/
    setCurfcy:function(dataSet)
    {
        var curacc = dataSet.getValue("accfcy");
        if(curacc)
        {
            var lastacc = this.getLastAccfcy(dataSet),
                thiscur = lastacc ? curacc - lastacc : curacc;
            dataSet.setValue("curfcy",thiscur);
        }
    }
});
/*snsoft/fin/inv/YMAccess.java*/
snsoft.fin.inv.YMAccess=Xjs.extend(Xjs.table.ColumnValueAccess,{
  _js$className_:"snsoft.fin.inv.YMAccess",
    /*snsoft.fin.inv.YMAccess.getValue*/
    getValue:function(dataSet,column,row,e)
    {
        var ym = dataSet.getValue(column.name,row);
        return ym;
    },
    /*snsoft.fin.inv.YMAccess.setValue*/
    setValue:function(dataSet,column,value,e)
    {
        if(value == null)
        {
            dataSet.setValue(column.name,null);
        }
        var ymd = value,
            ym = ymd.substring(0,ymd.lastIndexOf("-"));
        dataSet.setValue(column.name,ym);
    }
});
