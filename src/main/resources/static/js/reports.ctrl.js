

var app = angular.module("myApp",['angular-loading-bar', 'ngAnimate']);


app.controller("Ctrl",Ctrl);

function Ctrl(){

this.xyz = "from controller";

/*this.loadIssues = function(){
alert("loadIssues");
var self = this;

Reportserv.applyFilterAndLoad($filter('date')(this.fDate, "dd-MM-yyyy"),$filter('date')(this.tDate, "dd-MM-yyyy"))
            .then(function(data){

            self.result = data;

            })


}*/

}


/*app.service("Reportserv",function($http){

	var self = this;

     self.applyFilterAndLoad =function(fDate,tDate){

                    var promise1 = $http.get('/getIssuesBetwnDate?fDate='+fDate+'&tDate='+tDate);
                    var promise2 = promise1.then(function(response){
                    		return response.data;
                    });
                    return promise2;

     }





});*/



