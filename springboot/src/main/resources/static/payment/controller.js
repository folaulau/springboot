(function() {
    'use strict';
    angular.module('springboot').controller('paymentController', function($scope, $log) {
		$log.log("payment controller");
		
		var payment = this;
		
		payment.data = {};
		
		init();
		
		function init(){
			payment.data.paymentType = $scope.$parent.formData.paymentType;
		}

		payment.back = function(){
			$scope.$parent.back();
		}
		
		payment.next = function(){
			$scope.$parent.formData.paymentType = payment.data.paymentType;
			$scope.$parent.next();
		}
    
    });
    
})();