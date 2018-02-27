(function() {
	'use strict';

	angular.module('springboot').controller('DemoController', DemoController);

	DemoController.$inject = [ '$window','$scope', '$rootScope', '$http', '$log', '$state', 'InputService','$uibModal'];

	function DemoController($window, $scope, $rootScope, $http, $log, $state, InputService, $uibModal) {
		$log.log("demo controller");
		var form = $scope;
		form.data = {};
		form.elements = {};
		form.step = 0;
		form.elementSize = 0;
		init();
		
		function init(){
			$log.log($rootScope.formData);
			if($rootScope.formData==null){
				$state.go("form-input");
				return;
			}else{
				form.elements = $rootScope.formData.fields;
				form.elementSize = form.elements.length;
			}
			start();
		}
		
		function start(){
			let field = form.elements[form.step];
			$log.log("start, go to "+field.fieldType);
			$state.go("form-demo."+field.fieldType);
		}
		
		form.addToData = function(key,value){
			$log.log("add to data");
			$log.log("key: "+key+", value: "+value);
			form.data[key] = value;
			$log.log(form.data);
		}
		
		form.back = function(){
			form.step--;
			let field = form.elements[form.step];
			$log.log("back, go to "+field.fieldType);
			$state.go("form-demo."+field.fieldType,{ reload: true, inherit: true, notify: true });
		}
		
		form.next = function(){
			form.step++;
			let field = form.elements[form.step];
			$log.log("next, go to "+field.fieldType);
			$log.log("form-demo."+field.fieldType+"=="+$state.current.name);
			if("form-demo."+field.fieldType==$state.current.name){
				$log.log("refresh");
				$state.go("form-demo."+field.fieldType, {}, { reload: true });
			}else{
				$state.go("form-demo."+field.fieldType);
			}
			
		}
	}
})();
