(function() {
    'use strict';
    angular.module('springboot').controller('readingController', function($scope, $log) {
		$log.log("reading controller");

		var reading = this;
		
		reading.data = {};
		
		init();
		
		function init(){
			reading.data.book = $scope.$parent.formData.book;
		}

		reading.back = function(){
			$scope.$parent.back();
		}
		
		reading.next = function(){
			$scope.$parent.formData.book = reading.data.book;
			$scope.$parent.next();
		}	
    });
    
})();