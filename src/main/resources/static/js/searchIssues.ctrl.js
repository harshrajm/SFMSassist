

var app = angular.module("myApp",['angular-loading-bar', 'ngAnimate']);


app.controller("Ctrl",Ctrl);

function Ctrl(SearchIssueServ){
this.showJumbo = true;

this.makeListVisible =false;

this.loadData = function(){
var self = this;

if(this.query.length == 1 || this.query===""){
self.result.length = 0;
}else{

if(this.query !== undefined ){


SearchIssueServ.searchIssues(this.query)
.then(function(data){
	console.log(data);
    self.showJumbo = false;
	self.makeListVisible =true;
self.result = data;


})



}

}









}






}


app.service("SearchIssueServ",function($http){

	var self = this;

	self.searchIssues = function(query){

            		var promise1 = $http.get('/searchIssuesRestCall/'+query);
            		var promise2 = promise1.then(function(response){
            			return response.data;
            		});
            		return promise2;

            	}


});



