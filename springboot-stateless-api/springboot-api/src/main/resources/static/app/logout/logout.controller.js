(function() {
	'use strict';

	angular.module('client').controller('LogoutController', LogoutController);

	LogoutController.$inject = [ '$window', '$scope', '$http', '$log', '$state', '$stateParams'];

	function LogoutController($window, $scope, $http, $log, $state, $stateParams) {
		$log.log("Logout Controller");
		
	}
})();
