window.onload = function (){
          try{
              //动态创建一个canvas元 ，并获取他2Dcontext。如果出现异常则表示不支持
              document.createElement("canvas").getContext("2d");
              document.getElementById("support").innerHTML = "";
          }catch(e){
              document.getElementById("support").innerHTML = "";
          }
      };
 
      //这段代 主要是获取摄像头的视频流并显示在Video 签中
      window.addEventListener("DOMContentLoaded", function () {
          var video = document.getElementById("video");
          var videoObj = { "video": true };
          var errBack = function (error){
                  console.log("Video capture error: " + error.message, error.code);
              };
          //  支持浏览器  谷歌,火狐,360,欧朋
          //navigator.getUserMedia这个写法在Opera中好像是navigator.getUserMedianow
          if (navigator.getUserMedia) {
              navigator.getUserMedia(videoObj, function (stream) {
                  video.src = stream;
                  video.play();
              }, errBack);
          } else if (navigator.webkitGetUserMedia) {
              navigator.webkitGetUserMedia(videoObj, function (stream) {
                  video.src = window.URL.createObjectURL(stream);
                  video.play();
              }, errBack);
          } else if (navigator.mozGetUserMedia){
              navigator.mozGetUserMedia(videoObj, function (stream) {
                      video.src = window.URL.createObjectURL(stream);
                      video.play();
              }, errBack);
          }
          
          
          //这个是拍照按钮的事件，
          document.getElementById("snap").addEventListener("click",function(){
                  CatchCode();
          });
      }, false);
      //定时器
      //var interval = setInterval(CatchCode, "300");
      //这个是 刷新上 图像的
      