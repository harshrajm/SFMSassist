

var app = angular.module("myApp",['angular-loading-bar', 'ngAnimate']);


app.controller("Ctrl",Ctrl);

function Ctrl(IssueDetailserv){



this.xyz="from controller";

this.totalLoadedArray = [];

this.pageCnt=1;

this.yrTkt = false;

this.loadMoreBtnVisible=true;

this.buttonTxt="Load More";

this.filterDataLoaded = false;



this.applyFilter = function(){

var self = this;
/*self.query = "";*/
    if(this.uatOrProd == undefined && this.pendOrClosed == undefined && this.yrTkt == false){
    alert("No filter selected!");

    }else{
     //alert("in else");
        IssueDetailserv.applyFilterAndLoad(this.uatOrProd,this.pendOrClosed,this.yrTkt)
            .then(function(data){


                self.filterDataLoaded = true;
            	self.result.length = 0;
            	self.result = data;

            })
    }




}


this.loadMore = function(){
var self = this;
self.buttonTxt="Loding....";


IssueDetailserv.getMoreIssues(this.pageCnt)
.then(function(data){
	console.log(data);





	if(data.length<10){
	self.loadMoreBtnVisible = false;
	}
	self.result =self.result.concat(data);

	self.pageCnt = self.pageCnt + 1;

})
self.buttonTxt="Load More";


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


     self.applyFilterAndLoad =function(uatOrProd,pendOrClosed,yrTkt){
                    if(uatOrProd == undefined){
                        uatOrProd = 0;
                    }
                    if(pendOrClosed == undefined){
                        pendOrClosed = 0;
                    }


                    console.log("in applyFilterAndLoad serv");
                    var promise1 = $http.get('/getIssuesAfterFilter/filter?uatOrProd='+uatOrProd+'&pendOrClosed='+pendOrClosed+"&yrTkt="+yrTkt);
                    var promise2 = promise1.then(function(response){
                    		return response.data;
                    });
                    return promise2;

     }





});



