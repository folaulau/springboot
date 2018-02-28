(function() {
	'use strict';

	angular.module('client').controller('LoginController', LoginController);

	LoginController.$inject = [ '$window', '$scope', '$http', '$log', '$state', '$stateParams'];

	function LoginController($window, $scope, $http, $log, $state, $stateParams) {
		$log.log("Login Controller");
		
	}
})();
