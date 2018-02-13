(function() {
	'use strict';

	angular.module('springboot').controller('CheckboxController', CheckboxController);

	CheckboxController.$inject = [ '$window','$scope', '$http', '$uibModal', '$log', '$state', 'FormService'];

	function CheckboxController($window, $scope, $http, $uibModal, $log, $state, FormService) {
		$log.log("Checkbox controller");
		var form = this;
		form.data = {};
		form.data.values = [];
		form.transient = {};
		form.transient.counts = [];
		init();
		
		function init(){
			prefill();
			
			form.transient.counts.push(1);
			form.transient.counts.push(2);
			form.transient.counts.push(3);
		}
		
		function prefill(){
			form.data.fieldName = "test name";
			form.data.fieldHelperDescription = "test helper";
			form.data.fieldAttribute = "testName";
			form.data.fieldRequired = "yes";
			form.data.other = "no";
			form.data.fieldRequiredErrorMessage = "test required error msg";
			form.data.values.push("value1");
			form.data.values.push("value2");
			form.data.values.push("value3");
		}
		
		form.addValue = function(){
			$log.log("add value");
			form.transient.counts.push(form.transient.counts.length);
		}
		
		form.takeOut = function(index){
			$log.log("take out "+index);
			form.data.values.splice(index,1);
			form.transient.counts.splice(index,1);
		}
		
		form.submit = function(){
			RadioBtnService.add(form.data).then(function(data){
				$log.log("good");
				$log.log(data);
			}).catch(function(error){
				$log.log("error");
				$log.log(error);
			});
		}
		
		form.demo = function(){
			var modalInstance = $uibModal.open({
				animation : true,
				templateUrl : 'demo.html',
				controller : function($scope, $uibModalInstance, $log, $window) {
					var demo = $scope;
					demo.formData = form.data;
					demo.transient = {};
					demo.transient.counts = form.transient.counts;
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
						if(demo.formData.fieldRequired=='yes'){
							$log.log("field: "+demo.form.attr);
							if(demo.form.attr==null){
								$log.log("field is null");
								demo.error = true;
							}else{
								demo.error = false;
							}
						}
						
						
						var object = {};
						object['radioField'] = demo.form.attr;
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
