(function() {
	'use strict';

	angular.module('client').controller('UserController', UserController);

	UserController.$inject = [ '$window', '$scope', '$http', '$log', '$state', '$stateParams', 'User', 'TokenStorage'];

	function UserController($window, $scope, $http, $log, $state, $stateParams, User, TokenStorage) {
		$log.log("User Controller");
		var user = this;
		
		init();
		
		function init(){
			let authToken = TokenStorage.retrieve();
			$log.log("auth token: "+authToken);
			
			User.getAll(
				function(result){
					$log.log("success");
					$log.log(result);
				},
				function(error){
					$log.log("error");
					$log.log(error);
				}
			);
		}
		
	}
})();
