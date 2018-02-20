(function() {
	'use strict';

	angular.module('springboot').controller('DemoController', DemoController);

	DemoController.$inject = [ '$window','$scope', '$rootScope', '$http', '$log', '$state', 'InputService','$uibModal'];

	function DemoController($window, $scope, $rootScope, $http, $log, $state, InputService, $uibModal) {
		$log.log("demo controller");
		var form = this;
		form.data = {};
		form.showDetailData = false;
		
		init();
		//$state.transitionTo('form-demo.input');
		//$state.go("form-demo.input");
		
		function init(){
			
		}
		
		form.back = function(){
			$log.log("back");
		}
		
		form.next = function(){
			$log.log("next");
			$state.go("form-demo.checkbox");
		}
		
		form.showData = function(){
			form.showDetailData = true;
		}
		
		form.hideData = function(){
			form.showDetailData = false;
		}
	}
})();
