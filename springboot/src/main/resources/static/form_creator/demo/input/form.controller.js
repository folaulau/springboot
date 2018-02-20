(function() {
	'use strict';

	angular.module('springboot').controller('DemoInputController', DemoInputController);

	DemoInputController.$inject = [ '$window','$scope', '$rootScope', '$http', '$log', '$state', 'InputService','$uibModal'];

	function DemoInputController($window, $scope, $rootScope, $http, $log, $state, InputService, $uibModal) {
		$log.log("demo input controller");
		var form = this;
		form.data = {};
		form.showDetailData = false;
		
		init();
		
		function init(){
			$log.log("parent data");
			$log.log($scope.$parent.data);
			
			prefill();
		}
		
		function prefill(){
			form.data.fieldQuestion = "What is ?";
			form.data.fieldHelperDescription = "test helper";
			form.data.fieldAttribute = "testName";
			form.data.fieldRequired = "no";
			form.data.fieldType = "input";
			form.data.fieldRequiredErrorMessage = "test required error msg";
		}
	}
})();
