/**
 * Created by 杨玉卿 on 2018/4/10.
 */
/*关闭video*/
var video=document.getElementById('video');
    video.pause();
   $("#video").css("visibility","hidden");
//设置个人资料初始状态
    $("#profile").css('display','block');
    $("#second_profile").css('display','none');
//个人资料填充
function profile(data){
    $("#nick_name1").text(data['nick_name']);
    $("#true_name1").text(data['true_name']);
    if(data['gender']=='gentleman'){
        data['gender']='男';
    }
    else{data['gender']='女'}
    $("#gender1").text(data['gender']);
    $("#telephone1").text(data['telephone1']);
    $("#introduce1").text(data['introduction']);
    if(data['promise']==true){
        data['promise']='已签订';
    }
    else{data['promise']='未签订';}
    $("#promise").text(data['promise']);
    if(data['contract']==true){
        data['contract']='已签订';
    }
    else{data['contract']='未签订';}
    $("#contract").text(data['contract']);

}
//初始时加载一次资料信息
$.ajax({
    type:"get",
    url:"/",
    dataType:"json",
    data:{},
    success:function(data){
       profile(data);
    },
    error:function(){
        alert("error");
    }
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
                url:'/',
                dataType:"json",
                data:profile,
                processData: false,
                contentType: false,
                success:function(data){
                    //显示保存后的个人信息
                    profile(data);
                },
                error:function(){
                    alert("error");
                }
            })
        }
    });
