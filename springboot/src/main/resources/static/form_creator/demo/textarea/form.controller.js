(function() {
	'use strict';

	angular.module('springboot').controller('DemoTextAreaController', DemoTextAreaController);

	DemoTextAreaController.$inject = [ '$window','$scope', '$rootScope', '$http', '$log', '$state', 'TextareaService','$uibModal', 'FormService'];

	function DemoTextAreaController($window, $scope, $rootScope, $http, $log, $state, TextareaService, $uibModal, FormService) {
		$log.log("demo textarea controller");
		var form = this;
		form.data = {};
		init();
		
		function init(){
			prefill();
		}
		
		function prefill(){
			form.data.fieldQuestion = "What is ?";
			form.data.fieldHelperDescription = "test helper";
			form.data.fieldAttribute = "testName";
			form.data.fieldRequired = "no";
			form.data.fieldType = "textarea";
			form.data.fieldRequiredErrorMessage = "test required error msg";
		}
	}
})();
