Xjs.loadedXjs.push("snsoft/fin/bas");
Xjs.namespace("snsoft.fin.bas.comm");
snsoft.fin.bas.comm.FinOpenMuiListener=function(params){
Xjs.apply(this,params);
};
Xjs.extend(snsoft.fin.bas.comm.FinOpenMuiListener,Xjs.table.DefaultListener,{
oncmd_openUI:function(table)
{
if(!table.dataSet.isOpen())
return;
var muiid=this.info.muiid||Xjs.ui.UIUtil.getUiid(this.info.funcid),pm={};
Xjs.apply(pm,this.info.pm);
if(this.info.initMapper)
{
for(var name in this.info.initMapper)
{
var colname=this.info.initMapper[name];
pm["InitValue."+name]=table.dataSet.getValue(colname);
}
}
Xjs.ui.UIUtil.wopenUI(this.info.title,muiid,pm);
},
oncmd_newUI:function(table)
{
var muiid=this.info.muiid||Xjs.ui.UIUtil.getUiid(this.info.funcid),pm={};
Xjs.apply(pm,this.info.pm);
if(this.info.initMapper)
{
for(var name in this.info.initMapper)
{
pm["InitValue."+name]="<NULL>";
}
}
Xjs.ui.UIUtil.wopenUI(this.info.title,muiid,pm);
}
});
