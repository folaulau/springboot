(function() {
	'use strict';

	angular.module('springboot').controller('DemoTextAreaController', DemoTextAreaController);

	DemoTextAreaController.$inject = [ '$window','$scope', '$rootScope', '$http', '$log', '$state', 'TextareaService','$uibModal', 'FormService'];

	function DemoTextAreaController($window, $scope, $rootScope, $http, $log, $state, TextareaService, $uibModal, FormService) {
		$log.log("demo textarea controller");
		var form = this;
		form.data = {};
		form.element = {};
		
		form.nextBtn = {};
		form.backBtn = {};
		form.submitBtn = {};
		form.nextBtn.disabled = false;
		form.nextBtn.displayed = true;
		form.backBtn.disabled = false;
		form.backBtn.displayed = true;
		form.submitBtn.disabled = true;
		form.submitBtn.displayed = false;
		
		init();
		
		function init(){
			$log.log("step: "+$scope.$parent.step);
			$log.log($scope.$parent.elements[$scope.$parent.step]);
			form.element = $scope.$parent.elements[$scope.$parent.step];
			
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
		
		form.next = function(){
			$log.log("next");
			$log.log("key: "+form.element.fieldAttribute+", value: "+form.data[form.element.fieldAttribute]);
			$scope.$parent.addToData(form.element.fieldAttribute,form.data[form.element.fieldAttribute]);
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
