window.onload = function (){
          try{
              //��̬����һ��canvasԪ ������ȡ��2Dcontext����������쳣���ʾ��֧��
              document.createElement("canvas").getContext("2d");
              document.getElementById("support").innerHTML = "";
          }catch(e){
              document.getElementById("support").innerHTML = "";
          }
      };
 
      //��δ� ��Ҫ�ǻ�ȡ����ͷ����Ƶ������ʾ��Video ǩ��
      window.addEventListener("DOMContentLoaded", function () {
          var video = document.getElementById("video");
          var videoObj = { "video": true };
          var errBack = function (error){
                  console.log("Video capture error: " + error.message, error.code);
              };
          //  ֧�������  �ȸ�,���,360,ŷ��
          //navigator.getUserMedia���д����Opera�к�����navigator.getUserMedianow
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
          
          
          //��������հ�ť���¼���
          document.getElementById("snap").addEventListener("click",function(){
                  CatchCode();
          });
      }, false);
      //��ʱ��
      //var interval = setInterval(CatchCode, "300");
      //����� ˢ���� ͼ���
      