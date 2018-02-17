(function() {
	'use strict';

	angular.module('springboot').controller('InputController', InputController);

	InputController.$inject = [ '$window','$scope', '$rootScope', '$http', '$log', '$state', 'InputService','$uibModal'];

	function InputController($window, $scope, $rootScope, $http, $log, $state, InputService, $uibModal) {
		$log.log("creator input controller");
		var form = this;
		form.data = {};
		
		init();
		
		function init(){
			prefill();
		}
		
		function prefill(){
			form.data.fieldName = "test name";
			form.data.fieldHelperDescription = "test helper";
			form.data.fieldAttribute = "testName";
			form.data.fieldRequired = "yes";
			form.data.fieldType = "input";
			form.data.fieldRequiredErrorMessage = "test required error msg";
		}
		
		form.submit = function(){
//			InputService.addInputField(form.data).then(function(data){
//				$log.log("good");
//				$log.log(data);
//			}).catch(function(error){
//				$log.log("error");
//				$log.log(error);
//			});
			$log.log("submit input");
			$rootScope.$emit('addField', angular.toJson(form.data,true));
		}
		
		form.demo = function(){
			var modalInstance = $uibModal.open({
				animation : true,
				templateUrl : 'demo.html',
				controller : function($scope, $uibModalInstance, $log, $window) {
					var demo = $scope;
					demo.formData = form.data;
					demo.field = "";
					demo.errorMsg = "";
					demo.error = false;
					demo.form = {};
					setup();
					
					function setup(){
						$log.log("demo setup");
					}
					
					demo.ok = function(){
						$uibModalInstance.dismiss('cancel');
					}
					
					demo.save = function(){
						if(demo.field==null || demo.field.length<=0){
							$log.log("field is null");
							demo.error = true;
						}else{
							demo.error = false;
						}
						
						var object = {};
						object['inputField'] = demo.field;
						demo.form = object;
						
					}
				},
				size : 'lg'
			});
			
			modalInstance.result.then(function () {
				$log.log("modal closed");
	        }, function () {
	          $log.info('Modal dismissed at: ' + new Date());
	        });
		}
	}
})();
