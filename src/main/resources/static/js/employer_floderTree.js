//定义点击节点事件
function zTreeOnClick(event, treeId, treeNode){
    //获得被点击的节点的id，name信息
    var tid=treeNode.id;
    $.ajax({
        type:"post",
        url:"/",
        dataType:"json",
        data:{
            'tid':tid
        },
        success:function(){

        },
        error:function(){
            alert("请求失败");
        }
    })

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
            if(data.length==0){
                $("#treeDemo").html("<li><p class='alert alert-warning'>暂无工作文件夹，需发布外包后查看</p></li>");
            }
            var i;
            for(i in data){
                if(data[i].parent==true){
                    data[i].isParent=true;
                }
            }
            zTreeInit(data);
        },
        error:function(){
            alert("文件树请求失败！");
        }
    });

}
/* 初始化树 */
function zTreeInit(json) {
    $.fn.zTree.init($("#treeDemo"), setting, json);
}



