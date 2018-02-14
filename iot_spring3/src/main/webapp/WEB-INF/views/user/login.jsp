<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>


<script>
   var winF,popW;
   $(document).ready(function(){
      winF = new dhtmlXWindows();
      winF.attachViewportTo("winVP");
      popW = winF.createWindow("win1",20,30,320,300);
      //popW.hide(); 
      popW.button("close").hide();
      popW.button("minmax").hide();
      popW.button("park").hide();
      popW.setText("Login"); 

      winF.window("win1").centerOnScreen();//창을 중신에 두려고
      winF.window("win1").denyMove();//창을 못움지기게
      winF.window("win1").denyResize();//사이즈 조절을 못하게하려고
      var formObj = [
                 {type:"settings", offsetTop:12,name:"connectionInfo",labelAlign:"left"},
               {type:"input",name:"uiId", label:"아이디 : ",required:true},
               {type:"password",name:"uiPwd", label:"비밀번호 : ",required:true},
               {type: "block", blockOffset: 0, list: [
                  {type: "button", name:"loginBtn",value: "로그인"},
                  {type: "newcolumn"},
                  {type: "button", name:"cancelBtn",value: "취소"},
                  {type: "newcolumn"},
                  {type: "button", name:"joinBtn",value: "회원가입"}
               ]}
         ];
      var form = popW.attachForm(formObj,true);
      
      form.attachEvent("onButtonClick",function(id){//폼자체에 이벤트를 준다 그이름이 온클릭이다 id==>이름 ex>joinBtn
    	
         if(id=="loginBtn"){
            if(form.validate()){//required:true이게 트루로 되어있으면 서버 요청을 안한다 값이 들어가있음 그값을 보내고 아니면 그냥 트루 라서 안간다
               form.send("${root}/user/login", "post",callback);
            }
         }else if(id=="cancelBtn"){
            form.clear();
         }else if(id=="joinBtn"){
            location.href="${pPath}/user/join";
         }
      });
   });
   
   function callback(loader, res){
      if(loader.xmlDoc.status == 200){
         var res = JSON.parse(res);
         alert(res.msg);
         if(res.biz){
            location.href="${root}/path/db/main";
         }
      }else{  
         console.log(res);
      }
   }
   
</script>
<body>
   <div id="winVP"></div>
</body>

</html>