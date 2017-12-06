(function() {
	'use strict';

	angular.module('springboot').controller('FormController', FormController);

	FormController.$inject = [ '$window','$scope', '$http', '$log', '$state'];

	function FormController($window, $scope, $http, $log, $state) {
		$log.log("form controller");
		var form = this;
		
	}
})();
