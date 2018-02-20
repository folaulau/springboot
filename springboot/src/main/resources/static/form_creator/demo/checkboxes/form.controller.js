(function() {
	'use strict';

	angular.module('springboot').controller('DemoCheckboxController', DemoCheckboxController);

	DemoCheckboxController.$inject = [ '$window','$scope', '$rootScope', '$http', '$uibModal', '$log', '$state', 'CheckboxService'];

	function DemoCheckboxController($window, $scope, $rootScope, $http, $uibModal, $log, $state, CheckboxService) {
		$log.log("Demo Checkbox controller");
		var form = this;
		form.data = {};
		form.data.values = [];
		form.checkboxes = [];
		form.transient = {};
		form.transient.counts = [];
		form.other = false;
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
			form.data.fieldType= "checkbox";
			form.data.other = "no";
			form.data.fieldRequiredErrorMessage = "test required error msg";
			form.data.values.push("value1");
			form.data.values.push("value2");
			form.data.values.push("other");
			
			for(let i=0;i<form.data.values.length;i++){
				form.checkboxes.push({value: form.data.values[i], title: form.data.values[i], checked: false});
			}
		}
		
		form.checkValue = function(){
			$log.log("check value");
			for(let i=0;i<form.checkboxes.length;i++){
				let checkbox = form.checkboxes[i];
				$log.log(checkbox);
				if(checkbox.checked==true && checkbox.value=="other"){
					$log.log("display other");
					form.other = true;
				}
			}
		}
	}
})();
