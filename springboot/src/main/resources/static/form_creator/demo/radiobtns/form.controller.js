(function() {
	'use strict';

	angular.module('springboot').controller('DemoRadioBtnController', DemoRadioBtnController);

	DemoRadioBtnController.$inject = [ '$window','$scope', '$rootScope', '$http', '$uibModal', '$log', '$state', 'RadioBtnService'];

	function DemoRadioBtnController($window, $scope, $rootScope, $http, $uibModal, $log, $state, RadioBtnService) {
		$log.log("demo radiobtn controller");
		var form = this;
		form.field = "something";
		form.data = {};
		form.data.values = [];
		form.transient = {};
		form.transient.counts = [];
		form.showDetailData = false;
		init();
		
		function init(){
			prefill();
			
			form.transient.counts.push(1);
			form.transient.counts.push(2);
			form.transient.counts.push(3);
		}
		
		function prefill(){
			form.data.fieldQuestion = "What is ?";
			form.data.fieldHelperDescription = "test helper";
			form.data.fieldAttribute = "testName";
			form.data.fieldRequired = "no";
			form.data.fieldType = "radiobtn";
			form.data.other = "no";
			form.data.fieldRequiredErrorMessage = "test required error msg";
			form.data.values.push("value1");
			form.data.values.push("value2");
			form.data.values.push("other");
		}
	}
})();
