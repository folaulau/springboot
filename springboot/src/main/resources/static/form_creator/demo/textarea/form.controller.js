(function() {
	'use strict';

	angular.module('springboot').controller('TextAreaController', TextAreaController);

	TextAreaController.$inject = [ '$window','$scope', '$rootScope', '$http', '$log', '$state', 'TextareaService','$uibModal', 'FormService'];

	function TextAreaController($window, $scope, $rootScope, $http, $log, $state, TextareaService, $uibModal, FormService) {
		$log.log("textarea controller");
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
