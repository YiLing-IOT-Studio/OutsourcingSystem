//定义点击节点事件
function zTreeOnClick(event, treeId, treeNode){
    //获得被点击的节点的id，name信息
    var name=treeNode.name;
    alert(name);
    var tid=treeNode.id;
    alert(tid);
}
//设置
var setting = {
    keep:{
        parent:true
    },
    data : {
        simpleData : {
            enable : true,
            idKey: "id",
            pIdKey: "pId"
        }
    },
    callback: {
        onClick : zTreeOnClick
    }

};
$("#li_gj_item").click(function(){
    initTree();
});
function initTree(){
    $.ajax({
        type:'POST',
        url : "/getOrgZTree",
        dataType:'json',
        data : {
        },
        success: function(data){
            var i;
            for(i in data){
                if(data[i].parent=true){
                    data[i].isParent==true;
                }
            }
            zTreeInit(data);
        },
        error:function(){
            alert("请求失败！");
        }
    });

}
/* 初始化树 */
function zTreeInit(json) {
    $.fn.zTree.init($("#treeDemo"), setting, json);
}



