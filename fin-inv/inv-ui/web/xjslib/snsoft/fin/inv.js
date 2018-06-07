Xjs.loadedXjs.push("snsoft/fin/inv");
Xjs.namespace("snsoft.fin.inv");
snsoft.fin.inv.ProfitListener=Xjs.extend(Xjs.table.DefaultListener,{
lock:0,
dataSetFieldPosted:function(dataSet,event)
{
if("curfcy"==event.columnName)
{
var call={func:this.setAccfcy,scorp:this,args:[dataSet]};
this.lockInvoke(call);
this.setAccfcy(dataSet);
}else if("accfcy"==event.columnName)
{
var call={func:this.setCurfcy,scorp:this,args:[dataSet]};
this.lockInvoke(call);
}
},
lockInvoke:function(call)
{
if(this.lock>0)
return;
try
{
this.lock++;
call.func.apply(call.scorp,call.args);
}finally
{
this.lock--;
}
},
setAccfcy:function(dataSet)
{
var curfcy=dataSet.getValue("curfcy");
if(curfcy)
{
var lastacc=this.getLastAccfcy(dataSet),thisacc=lastacc?lastacc+curfcy:curfcy;
dataSet.setValue("accfcy",thisacc);
}
},
getLastAccfcy:function(dataSet)
{
var col=dataSet.columnAt("ym"),curym=dataSet.getValue(col),year=Number.obj2int(curym.substring(0,4),0),month=Number.obj2int(curym.substring(5,7),0)-1,date=new Date(year,month-1,1),lastym=date.format(2);
lastym=lastym.substring(0,lastym.lastIndexOf("-"));
for(var row=0;row<dataSet.getRowCount();row++)
{
if(lastym==dataSet.getValue(col,row))
return dataSet.getValue("accfcy",row);
}
return;
},
setCurfcy:function(dataSet)
{
var curacc=dataSet.getValue("accfcy");
if(curacc)
{
var lastacc=this.getLastAccfcy(dataSet),thiscur=lastacc?curacc-lastacc:curacc;
dataSet.setValue("curfcy",thiscur);
}
}
});
snsoft.fin.inv.YMAccess=Xjs.extend(Xjs.table.ColumnValueAccess,{
getValue:function(dataSet,column,row,e)
{
var ym=dataSet.getValue(column.name,row);
return ym;
},
setValue:function(dataSet,column,value,e)
{
if(value==null)
dataSet.setValue(column.name,null);
var ymd=value,ym=ymd.substring(0,ymd.lastIndexOf("-"));
dataSet.setValue(column.name,ym);
}
});
