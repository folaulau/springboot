(function() {
	'use strict';

	angular.module('springboot').controller('NavbarController', NavbarController);

	NavbarController.$inject = [ '$window','$scope', '$http', '$log', '$state'];

	function NavbarController($window, $scope, $http, $log, $state) {
		$log.log("navbar controller");
		var navbar = this;
		
	}
})();