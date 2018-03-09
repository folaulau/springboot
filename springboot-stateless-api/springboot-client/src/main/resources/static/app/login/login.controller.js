(function() {
	'use strict';

	angular.module('client').controller('LoginController', LoginController);

	LoginController.$inject = [ '$window', '$scope', '$http', '$log', '$state', '$stateParams', 'User'];

	function LoginController($window, $scope, $http, $log, $state, $stateParams, User) {
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
			//formData.append("username",login.data.username);
			//formData.append("password",login.data.password);
			
			$log.log(formData);
			
			$http({
		        method : "POST",
		        url : "http://localhost:8080/api/login",
		        	data : JSON.stringify(formData)
		    }).then(function success(response) {
		    		$log.log("success");
				$log.log(response);
				$log.log(response.headers);
				$log.log(response.config);
		    }, function error(response) {
		    		$log.log("error");
				$log.log(response);
		    });
		}
		
	}
})();
