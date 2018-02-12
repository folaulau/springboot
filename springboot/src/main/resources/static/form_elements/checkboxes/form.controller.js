(function() {
	'use strict';

	angular.module('springboot').controller('CheckboxController', CheckboxController);

	CheckboxController.$inject = [ '$window','$scope', '$http', '$log', '$state', 'FormService'];

	function CheckboxController($window, $scope, $http, $log, $state, FormService) {
		$log.log("Checkbox controller");
		var form = this;
		form.data = {};
		form.currentUser = {};
		
		init();
		
		function init(){
		}
	}
})();
