(function() {
	'use strict';

	angular.module('springboot').controller('TextAreaController', TextAreaController);

	TextAreaController.$inject = [ '$window','$scope', '$rootScope', '$http', '$log', '$state', 'TextareaService','$uibModal', 'FormService'];

	function TextAreaController($window, $scope, $rootScope, $http, $log, $state, TextareaService, $uibModal, FormService) {
		$log.log("textarea controller");
		var form = this;
		form.data = {};
		form.showDetailData = false;
		init();
		
		function init(){
			prefill();
		}
		
		function prefill(){
			form.data.fieldName = "test name";
			form.data.fieldHelperDescription = "test helper";
			form.data.fieldAttribute = "testName";
			form.data.fieldRequired = "yes";
			form.data.fieldType = "textarea";
			form.data.fieldRequiredErrorMessage = "test required error msg";
		}
		
		form.submit = function(){
			$log.log("submit textarea");
			$rootScope.$emit('addField', angular.toJson(form.data,true));
		}
		
		form.showData = function(){
			form.showDetailData = true;
		}
		
		form.hideData = function(){
			form.showDetailData = false;
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
