(function() {
    'use strict';
    angular.module('springboot').controller('careerController', function($scope, $log) {
		$log.log("career controller");
		
		var career = this;
		
		career.data = {};
		
		init();
		
		function init(){
			career.data.job = $scope.$parent.formData.job
		}

		career.back = function(){
			$scope.$parent.back();
		}
		
		career.next = function(){
			$scope.$parent.formData.job = career.data.job;
			$scope.$parent.next();
		}
    });
    
})();