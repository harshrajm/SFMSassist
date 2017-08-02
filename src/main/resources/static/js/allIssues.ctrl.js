

var app = angular.module("myApp",[]);


app.controller("Ctrl",Ctrl);

function Ctrl(IssueDetailserv){

this.xyz="from controller";

this.totalLoadedArray = [];

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


/*this.checkBxClk = function(){
    var self = this;
    var filterOp = [];
    //alert("Hello! I am an alert box!!");
    self.totalLoadedArray = self.result;
    self.result.length  = 0;



    //self.result = [{"issueId":42,"bankId":57,"issueCategory":4,"issueCurOwner":1040218,"issueDesc":"Refer EMS ticket number SD2017142532 , as informed by Nethaji - camt 054 message was successfully reprocessed from failed incoming queue of SFMS MI but below mentioned RTGS outward is still reflecting in Delivered status of SFMS MI.\r\nRequest you to check and update on priority.","issueLastUpdate":"Jul 21, 2017 4:46:42 PM","issueLoggedBy":1040218,"issueLoggedOn":"Jul 21, 2017 4:46:42 PM","issueResolvedBy":1040218,"issueResolvedOn":"Jul 21, 2017 4:46:42 PM","issueSolution":"reprocess","issueStatus":2,"issueSubCategory":2,"issueTitle":"camt054 message in failed incoming queue","issueType":2,"issueVersion":0,"sfmsVer":5,"ticketId":"SD2017142556","bankCodeStr":"HDFC","issueCatStr":"RCVR CBS","issueSubCatStr":"FBAPI105","issueTypeStr":"Production","isAssigned":false,"isYourTicket":true,"isClosed":true},{"issueId":41,"bankId":160,"issueCategory":4,"issueCurOwner":1040051,"issueDesc":"While generating inward report from SFMS, user is getting attached error.","issueLastUpdate":"Jul 26, 2017 10:28:45 AM","issueLoggedBy":1040218,"issueLoggedOn":"Jul 21, 2017 4:42:29 PM","issueStatus":1,"issueSubCategory":22,"issueTitle":"ERROR IN GENERATE INWARD REPORT","issueType":1,"issueVersion":1,"sfmsVer":2,"ticketId":"SD2017141486","bankCodeStr":"KKBK","issueCatStr":"RCVR CBS","issueSubCatStr":"FBAPI133","issueTypeStr":"UAT","isAssigned":false,"isYourTicket":false,"isClosed":false},{"issueId":32,"bankId":134,"issueCategory":4,"issueCurOwner":1040219,"issueDesc":"We are in the process of implementing STP from our CBS for LC and BG messages\r\nOur LC messages were ok and we were able to resolve issues that occured refering with bankapiconfig file\r\n\r\nhowever the BG messages are getting failed with BAPI00014\r\n","issueLastUpdate":"Jul 21, 2017 4:03:37 PM","issueLoggedBy":1040218,"issueLoggedOn":"Jul 21, 2017 4:03:37 PM","issueStatus":1,"issueSubCategory":21,"issueTitle":"BG MESSAGE GETTING FAILED IN SFMS UAT","issueType":1,"issueVersion":0,"sfmsVer":3,"ticketId":"SD2017143582","bankCodeStr":"FDRL","issueCatStr":"RCVR CBS","issueSubCatStr":"FBAPI100","issueTypeStr":"UAT","isAssigned":false,"isYourTicket":false,"isClosed":false},{"issueId":31,"bankId":131,"issueCategory":5,"issueCurOwner":1131314,"issueDesc":"we are ready for the Production movement of  SFMS-NEFT Merger setup today 21-Jul-2017 post RTGS EOD . Request you to technically assist  us for the successful  completion of the scheduled activity.","issueLastUpdate":"Jul 26, 2017 2:52:55 PM","issueLoggedBy":1040219,"issueLoggedOn":"Jul 21, 2017 3:25:34 PM","issueResolvedBy":1040218,"issueResolvedOn":"Jul 26, 2017 2:52:55 PM","issueSolution":"closing !!!1","issueStatus":2,"issueSubCategory":27,"issueTitle":"Go live SFMS-NEFT Merger readiness","issueType":2,"issueUpdatedBy":1040218,"issueVersion":2,"sfmsVer":4,"ticketId":"SD2017143507","bankCodeStr":"DLXB","issueCatStr":"Sender","issueSubCatStr":"Sender error 3","issueTypeStr":"Production","isAssigned":false,"isYourTicket":false,"isClosed":true},{"issueId":30,"bankId":57,"issueCategory":5,"issueCurOwner":1126236,"issueDesc":"As we have already raised a ticket regarding window10 64 bit testing.we were facing issue with safenet so as per your suggestion we have cordinated safe-net team for the same and the issue got resolved but now we are facing some permissions issue when we login through admin user we are able to login but from user profile we are unable to login is their any specific permission we have to provide it to user profile or any folder  we are having IE11","issueLastUpdate":"Jul 26, 2017 11:34:09 AM","issueLoggedBy":1040218,"issueLoggedOn":"Jul 21, 2017 3:04:21 PM","issueStatus":1,"issueSubCategory":25,"issueTitle":"Facing issue while loginn into application on windows 10","issueType":1,"issueUpdatedBy":1040218,"issueVersion":1,"sfmsVer":2,"ticketId":"SD2017143440","bankCodeStr":"HDFC","issueCatStr":"Sender","issueSubCatStr":"Sender error 2","issueTypeStr":"UAT","isAssigned":false,"isYourTicket":false,"isClosed":false}];
}*/



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



