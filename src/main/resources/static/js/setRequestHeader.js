/**
 * Created by 杨玉卿 on 2018/5/6.
 */
//设置AJAX的全局默认选项
$.ajaxSetup( {
    beforeSend: function(xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN',$("meta[name='_csrf']").attr("content"));
    },
    global:true
} );