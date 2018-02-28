(function() {
	'use strict';

	angular.module('client').controller('NavbarController', NavbarController);

	NavbarController.$inject = [ '$window','$scope', '$http', '$log', '$state'];

	function NavbarController($window, $scope, $http, $log, $state) {
		$log.log("navbar controller");
		var navbar = this;
		
		
		navbar.logout = function(){
			$log.log("logout");
			$state.go("logout");
		}
	}
})();