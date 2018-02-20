(function() {
	'use strict';

	angular.module('springboot').controller('DemoController', DemoController);

	DemoController.$inject = [ '$window','$scope', '$rootScope', '$http', '$log', '$state', 'InputService','$uibModal'];

	function DemoController($window, $scope, $rootScope, $http, $log, $state, InputService, $uibModal) {
		$log.log("demo controller");
		var form = this;
		form.data = {};
		form.showDetailData = false;
		$scope.goNext = false;
		init();
		//$state.transitionTo('form-demo.input');
		//$state.go("form-demo.input");
		
		function init(){
			$log.log($rootScope.formData);
			if($rootScope.formData==null){
				$state.go("form-input");
				return;
			}else{
				form.data = $rootScope.formData;
				$scope.data = $rootScope.formData;
			}
			let firstField = form.data.fields[0];
			$log.log("first go to "+firstField.fieldType);
			$state.go("form-demo."+firstField.fieldType);
		}
		
		form.back = function(){
			$log.log("back");
			if($state.$current.name=="form-demo.input"){
				$state.go("form-demo.checkbox");
	    		}else if($state.$current.name=="form-demo.textarea"){
	    			$state.go("form-demo.input");
	    		}else if($state.$current.name=="form-demo.radiobtn"){
	    			$state.go("form-demo.textarea");
	    		}else if($state.$current.name=="form-demo.checkbox"){
	    			$state.go("form-demo.radiobtn");
	    		}
		}
		
		form.next = function(){
			$log.log("next");
			if($state.$current.name=="form-demo.input"){
				$state.go("form-demo.textarea");
	    		}else if($state.$current.name=="form-demo.textarea"){
	    			$state.go("form-demo.radiobtn");
	    		}else if($state.$current.name=="form-demo.radiobtn"){
	    			$state.go("form-demo.checkbox");
	    		}else if($state.$current.name=="form-demo.checkbox"){
	    			$state.go("form-demo.input");
	    		}
		}
		
		form.showData = function(){
			form.showDetailData = true;
		}
		
		form.hideData = function(){
			form.showDetailData = false;
		}
	}
})();
