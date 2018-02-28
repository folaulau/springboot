(function() {
	'use strict';

	angular.module('client').controller('PublicController', PublicController);

	PublicController.$inject = [ '$window', '$scope', '$http', '$log', '$state', '$stateParams'];

	function PublicController($window, $scope, $http, $log, $state, $stateParams) {
		$log.log("Public Controller");
		
	}
})();
