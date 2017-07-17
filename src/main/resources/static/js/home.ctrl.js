

var app = angular.module("myApp",[]);

app.controller("Ctrl",Ctrl);

function Ctrl(Contactserv){

var self = this;

this.onClick = function(){

Contactserv.getContacts()
.then(function(data){
	self.contacts = data;
})
}



this.xyz="sdfsdfsdf";





}



app.service("Contactserv",function($http){

	var self = this;

	self.getContacts = function(){

		var promise1 = $http.get('http://localhost:3000/Contacts');
		var promise2 = promise1.then(function(response){
			return response.data;
		});
		return promise2;

	}
});
