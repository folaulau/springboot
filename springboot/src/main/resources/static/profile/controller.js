(function() {
    'use strict';
    angular.module('springboot').controller('profileController', function($scope, $log) {
		$log.log("profile controller");

		var profile = this;
		
		profile.data = {};
		
		init();
		
		function init(){
			profile.data.name = $scope.$parent.formData.name;
			profile.data.email = $scope.$parent.formData.email;
		}
		
		profile.back = function(){
			$scope.$parent.back();
		}
		
		profile.next = function(){
			$scope.$parent.formData.name = profile.data.name;
			$scope.$parent.formData.email = profile.data.email;
			$scope.$parent.next();
			//$state.go("");
		}
		
		
		
    });
    
})();