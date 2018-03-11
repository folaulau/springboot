(function() {
	'use strict';

	angular.module('client').controller('RestApiController', RestApiController);

	RestApiController.$inject = [ '$window', '$scope', '$http', '$log', '$state', '$stateParams','TokenStorage','User'];

	function RestApiController($window, $scope, $http, $log, $state, $stateParams, TokenStorage, User) {
		$log.log("RestApi Controller");
		var rest = this;
		rest.loginData = {};
		rest.response = {};
		rest.userAuthenticated = false;
		rest.userData = {};
		
		init();
		
		function init(){
			prefill();
			checkToken();
		}
		
		function prefill(){
			rest.loginData.username = "user@gmail.com";
			rest.loginData.password = "password";
		}
		
		function checkToken(){
			let authToken = TokenStorage.retrieve();
			$log.log("authToken: "+authToken);
			if(authToken!=null){
				rest.userAuthenticated = true;
				var decoded = jwt_decode(authToken);
				$log.log(decoded);
				rest.userData = decoded;
			}
		}
		
		rest.callPublicService = function(){
			$log.log("callPublicService");
			$http.get("http://localhost:8080/api/public")
		    .then(function(response) {
		    		$log.log("success");
		    		$log.log(response);
		    		rest.response = response.data;
		    }).catch(function(error){
		    		$log.log("error");
		    		$log.log(error);
		    		rest.response = error;
		    });
		}
		
		rest.callPropectedService = function(){
			$log.log("callPropectedService");
			User.getUser(
					function(data){
						$log.log("success");
			    			$log.log(data);
			    			rest.response = data;
					},
					function(error){
						$log.log("error");
			    			$log.log(error);
			    			rest.response = error;
					}
			);
		}
		
		rest.callAdminService = function(){
			$log.log("callAdminService");
			User.getAdmin(
					function(data){
						$log.log("success");
			    			$log.log(data);
			    			rest.response = data;
					},
					function(error){
						$log.log("error");
			    			$log.log(error);
			    			rest.response = error;
					}
			);
		}
		
		rest.login = function(){
			$log.log("log in");
			
			let formData = {};
			formData['username'] = rest.loginData.username;
			formData['password'] = rest.loginData.password;
			
			$log.log(formData);
			
			$http({
		        method : "POST",
		        url : "http://localhost:8080/api/login",
		        	data : formData
		    }).then(function success(response) {
		    		$log.log("success");
				$log.log(response);
				$log.log("status: "+response.data['status']);
				if(response.data['status']=="authenticated"){
					let authToken = response.data['X-AUTH-TOKEN'];
					$log.log("authToken");
					$log.log(authToken);
					
					TokenStorage.store(authToken);
					checkToken();
				}
		    }, function error(response) {
		    		$log.log("error");
				$log.log(response);
		    });
		}
		
		rest.logout = function(){
			TokenStorage.clear();
			$window.location.reload();
		}
		
	}
})();
