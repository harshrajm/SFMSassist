var app = angular.module("myApp",[]);

app.controller("Ctrl",Ctrl);

function Ctrl(){

this.xyz="from controller";


var x = this;

this.compPwd = function(user){

if(user.password == user.cPassword){
x.pwdIsNotSame = false;
}else{
x.pwdIsNotSame = true;
}

}


}