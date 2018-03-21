(function() {
	'use strict';

	angular.module('springboot').controller('HomeController', HomeController);

	HomeController.$inject = [ '$window','$scope', '$http', '$log', '$state'];

	function HomeController($window, $scope, $http, $log, $state) {
		$log.log("home controller");
		var home = this;
		
	}
})();
