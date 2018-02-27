(function() {
	'use strict';

	angular.module('springboot').controller('RadioBtnController', RadioBtnController);

	RadioBtnController.$inject = [ '$window','$scope', '$rootScope', '$http', '$uibModal', '$log', '$state', 'RadioBtnService'];

	function RadioBtnController($window, $scope, $rootScope, $http, $uibModal, $log, $state, RadioBtnService) {
		$log.log("radio btn controller");
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
			form.data.fieldAttribute = "testRadioBtn";
			form.data.fieldRequired = "no";
			form.data.fieldType = "radiobtn";
			form.data.other = "no";
			form.data.fieldRequiredErrorMessage = "test required error msg";
			form.data.values.push("value1");
			form.data.values.push("value2");
			form.data.values.push("other");
		}
		
		form.clear = function(){
			form.data.fieldQuestion = "";
			form.data.fieldHelperDescription = "";
			form.data.fieldAttribute = "";
			form.data.fieldRequired = "no";
			form.data.fieldType = "textarea";
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
//			RadioBtnService.add(form.data).then(function(data){
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
					demo.transient = {};
					demo.transient.counts = form.transient.counts;
					demo.errorMsg = "";
					demo.error = false;
					demo.form = {};
					demo.field = {};
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
						if(demo.field.attr=="other"){
							object['radioField'] = demo.formData.otherValue;
						}else{
							object['radioField'] = demo.field.attr;
						}
						
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
