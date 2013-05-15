      $(document).ready(function(){


                     $("#painel_link").hide();
                     $("#painel_imagem").hide();

               $("#negrito").click(function(){

                  $("#painel_link").hide();
                     $("#painel_imagem").hide();

                  var message = document.getElementById("message");
                  var startPos = message.selectionStart;
                  var endPos = message.selectionEnd;

                if(startPos==endPos){
                    message.value = message.value.substring(0, startPos) + "<strong></strong>" + message.value.substring(startPos, message.lenght);

                    message.selectionStart = startPos+8;
                    message.selectionEnd = startPos+8;

                }else{
                   message.value = message.value.substring(0, startPos) + "<strong>" + message.value.substring(startPos, endPos) + "</strong>" + message.value.substring(endPos, message.lenght);
                }

                message.focus();

                contar();

               });

                $("#link").click(function(){

                     $("#painel_imagem").hide("fast");
                     $("#painel_link").show("fast");

               });

               $("#link_ok").click(function(){

                  $("#painel_link").hide("fast");

                  var url = document.getElementById("url");
                  var message = document.getElementById("message");
                  var startPos = message.selectionStart;
                  var endPos = message.selectionEnd;

                if(startPos==endPos){
                    message.value = message.value.substring(0, startPos) + "<a href=\"" + url.value + "\" target=\"black\"></a>" + message.value.substring(startPos, message.lenght);
                    message.selectionStart = startPos + (26 + url.value.length);
                    message.selectionEnd = startPos + (26 + url.value.length);
                }else{
                   message.value = message.value.substring(0, startPos) + "<a href=\"" + url.value + "\" target=\"black\">" + message.value.substring(startPos, endPos) + "</a>" + message.value.substring(endPos, message.lenght);
                }

                url.value = "http://";
                message.focus();
                contar();

               });

               $("#painel_link").hover(function(){


               },function(){

                 $("#painel_link").hide("fast");

               });

               $("#imagem").click(function(){

                   $("#painel_link").hide("fast");
                   $("#painel_imagem").show("fast");

               });

               $("#imagem_ok").click(function(){

                    $("#painel_imagem").hide("fast");

                  var sem = document.getElementById("sem").checked;
                  var esquerda = document.getElementById("esquerda").checked;
                  var direita = document.getElementById("direita").checked;

                  var url = document.getElementById("url_imagem");
                  var message = document.getElementById("message");
                  var startPos = message.selectionStart;
                  var alinhamento = "";

                  if(sem){
                     alinhamento = " class=\"sem\"";
                  }else{
                    if(esquerda){
                     alinhamento = " class=\"esq\"";
                    }else{
                      alinhamento = " class=\"dir\"";
                    }
                  }

                    message.value = message.value.substring(0, startPos) + "<img src=\"" + url.value + "\"" + alinhamento + "/>" + message.value.substring(startPos, message.lenght);
                    message.selectionStart = startPos + (25 + url.value.length);
                    message.selectionEnd = startPos + (25 + url.value.length);

                url.value = "http://";
                message.focus();
                contar();

               });

               $("#painel_imagem").hover(function(){

               },
               function(){

                   $("#painel_imagem").hide("fast");

               });

               $("#message").click(function(){
                  $("#painel_link").hide("fast");
                  $("#painel_imagem").hide("fast");
               });


              $("#postar").validate({

                 rules:{
                     titulo:{
                        required: true, maxlength: 50
                     },
                     message:{
                        required: true, maxlength: 4000
                     }
                 },

                 messages:{
                     titulo:{
                         required: "Campo obrigatorio",
                         maxlength: "Tamanho máximo de 50 caracteres"
                     },
                     message:{
                         required: "Campo obrigatorio",
                         maxlength: "Tamanho máximo de 4000 caracteres"
                     }
                 }
              });
            });
