/**
 * Created by yyq on 2018/3/6.
 */
$(function() {
    $("input[type='radio']").click(function () {
        $("input[type='radio'][name='" + $(this).attr('name') + "']]").parent().removeClass("checked");
        $(this).parent().addClass("checked");
    });
    $("input[type='checkbox']").click(function(){
        $(this).parent().addClass("checked");
    })
});