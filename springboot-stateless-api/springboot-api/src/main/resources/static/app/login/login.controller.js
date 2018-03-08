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
			var credentials = $.param({ username: login.data.username, password: login.data.password });
			$log.log(credentials);
			$http.post('/api/login', credentials)
			.then(function (result, status, headers) {
				let authToken = headers('X-AUTH-TOKEN');
				$log.log("auth token: "+authToken);
				TokenStorage.store(authToken);
			},function(error){
				$log.log("error");
				$log.log(error);
			});
			
//			User.login(login.data, 
//				function(result){
//					$log.log("result");
//					$log.log(result);
//				},
//				function(error){
//					$log.log("error");
//					$log.log(error);
//				}
//			);
		}
		
	}
})();
