

var app = angular.module("myApp",['angular-loading-bar', 'ngAnimate']);

app.controller("Ctrl",Ctrl);

function Ctrl(Detailserv){


this.loaded = false;
this.loadedSubCat = false;
this.showSol =false;

this.xyz="from controller";
var self = this;

this.checkBxClk = function(){

if(this.showSol == true ){
    self.showSol = false;
}else{
    self.showSol = true;
}
}



this.clicked = function(){
console.log("qweqwe");
self.loaded = true;
Detailserv.getCateg()
.then(function(data){
	self.categList = data;
})

}

this.clicked2 = function(categ){
/*alert(categ);*/

Detailserv.getSubCateg(categ)
.then(function(data){
    console.log(data);
    self.loadedSubCat = true;
	self.subCategList = data;
})


}



}



app.service("Detailserv",function($http){

	var self = this;

	self.getCateg = function(){

		var promise1 = $http.get('/getAllIssueCategory');
		var promise2 = promise1.then(function(response){
			return response.data;
		});
		return promise2;

	}


	self.getSubCateg = function(catId){

    		var promise1 = $http.get('/getSubCat/'+catId);
    		var promise2 = promise1.then(function(response){
    			return response.data;
    		});
    		return promise2;

    	}

});



