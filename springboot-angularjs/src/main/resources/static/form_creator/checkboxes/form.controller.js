(function() {
	'use strict';

	angular.module('springboot').controller('CheckboxController', CheckboxController);

	CheckboxController.$inject = [ '$window','$scope', '$rootScope', '$http', '$uibModal', '$log', '$state', 'CheckboxService'];

	function CheckboxController($window, $scope, $rootScope, $http, $uibModal, $log, $state, CheckboxService) {
		$log.log("Checkbox controller");
		var form = this;
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
			form.data.fieldAttribute = "testCheckbox";
			form.data.fieldRequired = "no";
			form.data.fieldType= "checkbox";
			form.data.other = "no";
			form.data.fieldRequiredErrorMessage = "test required error msg";
			form.data.values.push("value1");
			form.data.values.push("value2");
			form.data.values.push("other");
		}
		
		form.clear = function(){
			$log.log("clear form");
			form.data.fieldQuestion = "";
			form.data.fieldHelperDescription = "";
			form.data.fieldAttribute = "";
			form.data.fieldType = "checkbox";
			form.data.fieldRequired = "no";
			form.data.fieldRequiredErrorMessage = "test required error msg";
			form.data.values = [];
			form.transient.counts = [];
		}
		
		form.addValue = function(){
			$log.log("add value");
			form.transient.counts.push(form.transient.counts.length);
		}
		form.showData = function(){
			form.showDetailData = true;
		}
		
		form.hideData = function(){
			form.showDetailData = false;
		}
		
		
		form.takeOut = function(index){
			$log.log("take out "+index);
			form.data.values.splice(index,1);
			form.transient.counts.splice(index,1);
		}
		
		form.submit = function(){
//			CheckboxService.addField(form.data).then(function(data){
//				$log.log("good");
//				$log.log(data);
//			}).catch(function(error){
//				$log.log("error");
//				$log.log(error);
//			});
			$log.log("submit checkboxes");
			$rootScope.$emit('addField', angular.toJson(form.data,true));
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
					demo.checkboxes = [];
					demo.other = false;
					setup();
					
					function setup(){
						$log.log("demo setup");
						for(let i=0;i<demo.formData.values.length;i++){
							$log.log(demo.formData.values[i]);
							demo.checkboxes.push({value: demo.formData.values[i], title: demo.formData.values[i], checked: false});
						}
					}
					
					demo.checkValue = function(){
						$log.log("check value");
						for(let i=0;i<demo.checkboxes.length;i++){
							let checkbox = demo.checkboxes[i];
							if(checkbox.checked==true && checkbox.value=="other"){
								demo.other = true;
							}
						}
					}
					
					demo.ok = function(){
						$uibModalInstance.dismiss('cancel');
					}
					
					demo.save = function(){
						
						var finalValues = [];
						
						for(let i=0;i<demo.checkboxes.length;i++){
							let checkbox = demo.checkboxes[i];
							if(checkbox.checked==true && checkbox.value=="other"){
								$log.log("other value: "+demo.formData.otherValue);
								finalValues.push(demo.formData.otherValue);
							}else if(checkbox.checked==true){
								$log.log("checked value: "+checkbox.value);
								finalValues.push(checkbox.value);
							}else{
								$log.log("unchecked value: "+checkbox.value);
							}
						}
						
						if(demo.formData.fieldRequired=='yes'){
							$log.log("field: "+demo.form.attr);
							if(finalValues.length==0){
								$log.log("field is null");
								demo.error = true;
								return;
							}else{
								demo.error = false;
							}
						}
						
						demo.form = finalValues;
						
						
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
