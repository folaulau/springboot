(function() {
	'use strict';

	angular.module('springboot').controller('DemoCheckboxController', DemoCheckboxController);

	DemoCheckboxController.$inject = [ '$window','$scope', '$rootScope', '$http', '$uibModal', '$log', '$state', 'CheckboxService'];

	function DemoCheckboxController($window, $scope, $rootScope, $http, $uibModal, $log, $state, CheckboxService) {
		$log.log("Demo Checkbox controller");
		var form = this;
		form.data = {};
		form.element = {};
		form.data.checkboxes = [];
		form.other = false;
		form.otherValue = "";
		
		form.nextBtn = {};
		form.backBtn = {};
		form.submitBtn = {};
		form.nextBtn.disabled = false;
		form.nextBtn.displayed = true;
		form.backBtn.disabled = false;
		form.backBtn.displayed = true;
		form.submitBtn.disabled = false;
		form.submitBtn.displayed = false;
		
		init();
		
		function init(){
			$log.log("step: "+$scope.$parent.step);
			$log.log($scope.$parent.elements[$scope.$parent.step]);
			form.element = $scope.$parent.elements[$scope.$parent.step];
			
			for(let i=0;i<form.element.values.length;i++){
				form.data.checkboxes.push({value: form.element.values[i], title: form.element.values[i], checked: false});
			}
			
			setup();
		}
		
		function setup(){
			$log.log("setup, step: "+$scope.$parent.step+", size: "+$scope.$parent.elementSize);
			if($scope.$parent.step==0){
				form.backBtn.disabled = true;
			}
			if(($scope.$parent.step+1)==$scope.$parent.elementSize){
				form.nextBtn.disabled = true;
				form.nextBtn.displayed = false;
				form.submitBtn.displayed = true;
			}
		}
		
		form.checkValue = function(){
			$log.log("check value");
			for(let i=0;i<form.data.checkboxes.length;i++){
				let checkbox = form.data.checkboxes[i];
				if(checkbox.checked==true && checkbox.value=="other"){
					form.other = true;
					break;
				}
			}
		}
		
		form.next = function(){
			$log.log("next");
			let values = [];
			for(let i=0;i<form.data.checkboxes.length;i++){
				let checkbox = form.data.checkboxes[i];
				if(checkbox.checked==true){
					if(checkbox.value=="other"){
						form.other = true;
						values.push(form.data.otherValue);
					}else{
						values.push(checkbox.value);
					}
				}
			}
			$log.log("key: "+form.element.fieldAttribute+", values: "+values);
			$scope.$parent.addToData(form.element.fieldAttribute,values);
			$scope.$parent.next();
		}
		
		form.back = function(){
			$log.log("back");
			$scope.$parent.back();
		}
		
		form.submit = function(){
			$log.log("submit");
			$scope.$parent.submit();
		}
	}
})();
