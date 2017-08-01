

var app = angular.module("myApp",[]);

app.controller("Ctrl",Ctrl);

function Ctrl(IssueDetailserv){

this.xyz="from controller";

this.pageCnt=1;

this.loadMoreBtnVisible=true;

this.buttonTxt="Load More";

this.loadMore = function(){
var self = this;
self.buttonTxt="Loding....";
/*alert("Hello! I am an alert box!!");
alert(this.pageCnt);
alert(self.pageCnt);*/

IssueDetailserv.getMoreIssues(this.pageCnt)
.then(function(data){
	console.log(data);

	if(data.length<5){
	alert("data length < 5");
	self.loadMoreBtnVisible = false;
	}
	self.result =self.result.concat(data);

	self.pageCnt = self.pageCnt + 1;

})
self.buttonTxt="Load More";
//self.pageCnt = this.pageCnt+1;

}



}


app.service("IssueDetailserv",function($http){

	var self = this;

	self.getMoreIssues = function(pageCnt){

            		var promise1 = $http.get('/allIssuesRestCall/'+pageCnt);
            		var promise2 = promise1.then(function(response){
            			return response.data;
            		});
            		return promise2;

            	}

	/*self.getCateg = function(){

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

    	}*/




});



