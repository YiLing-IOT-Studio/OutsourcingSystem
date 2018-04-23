/**
 * Created by 杨玉卿 on 2018/4/10.
 */
//设置个人资料初始状态
    $("#profile").css('display','block');
    $("#second_profile").css('display','none');
    function initialData2(){
        $("#true_name").val($("#true_name1").text());
        $("#gender").val($("#gender1").text());
        $("#company").val($("#company1").text());
        $("#job").val($("#job1").text());
        $("#introduce").val($("#introduce1").text());
    }

//个人资料填充
function profile(data){
    $("#true_name1").text(data[0].name);
    if(data[0].gender=='gentleman'){
        data[0].gender='男';
    }
    else{data[0].gender='女'}
    $("#gender1").text(data[0].gender);
    $("#telephone1").text(data[0].phone);
    $("#company1").text(data[0].company);
    $("#job1").text(data[0].job);
    $("#introduce1").text(data[0].introduce);
    if(data[0].obey=="on"){
        data[0].obey='遵守协议';
    }
    else{data[0].obey='未签订';}
    $("#promise").text(data[0].obey);


}
//初始时加载一次资料信息
function initialData1(){
    $.ajax({
        type:"post",
        url:"/staff/getUserInfo",
        dataType:"json",
        async:false,
        data:{},
        success:function(data){
            profile(data);
        },
        error:function(){
            alert("请求失败");
        }
    });
}
initialData1();
initialData2();
$("#li_info_item").click(function(){
    /*关闭video*/
    var video=document.getElementById('video');
    video.pause();
    $("#video").css("visibility","hidden");
//设置个人资料初始状态
    $("#profile").css('display','block');
    $("#second_profile").css('display','none');

});
//编辑状态
    $("#edit").click(function(){
        $("#second_profile").css('display','block');

        $("#profile").css('display','none');

    });
    //保存时传一次更新后的个人资料
    $("#save").click(function(event){
        if($("#nick_name").val()==""||$("#true_name").val()==""||$("#telephone").val()==""||$("#introduce").val()==""){
            alert("您的资料还未填写完整，请填写完整后提交!");
            event.preventDefault();
        }
        else{
            var profile=new FormData(update_profile);
            $.ajax({
                type:'post',
                url:'/updateUserInfo',
                dataType:"json",
                data:profile,
                processData: false,
                contentType: false,
                success:function(data){
                    //显示保存后的个人信息
                    profile(data);
                },
                error:function(){
                    alert("请求失败");
                }
            })
        }
    });

