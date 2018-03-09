(function() {
	'use strict';

	angular.module('client').controller('LoginController', LoginController);

	LoginController.$inject = [ '$window', '$scope', '$http', '$log', '$state', '$stateParams', 'User', 'TokenStorage'];

	function LoginController($window, $scope, $http, $log, $state, $stateParams, User, TokenStorage) {
		$log.log("Login Controller");
		var login = this;
		login.data = {};
		
		init();
		
		function init(){
			prefill()
		}
		
		function prefill(){
			login.data.username = "folaukaveinga@gmail.com";
			login.data.password = "test12";
		}
		
		login.submit = function(){
			$log.log("login...");
			let formData = {};
			formData['username'] = login.data.username;
			formData['password'] = login.data.password;
			
			$log.log(formData);
			
//			$http.post('http://localhost:8080/api/login', formData)
//			.success(function (result, status, headers) {
//				$log.log("success");
//				$log.log(result);
//				let authToken = headers('X-AUTH-TOKEN');
//				$log.log("authToken");
//				$log.log(authToken);
//				TokenStorage.store(authToken);
//			}); 
			$http({
		        method : "POST",
		        url : "http://localhost:8080/api/login",
		        	data : formData
		    }).then(function success(response) {
		    		$log.log("success");
				$log.log(response);
				$log.log("status");
				$log.log(response.status);
				let authToken = response.headers('x-auth-token');
				$log.log("authToken");
				$log.log(authToken);
				TokenStorage.store(authToken);
				
				$log.log("headers");
				$log.log(response.headers);
		    }, function error(response) {
		    		$log.log("error");
				$log.log(response);
		    });
		}
		
	}
})();
