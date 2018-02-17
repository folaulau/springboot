(function() {
	'use strict';
	 angular.module('springboot').service('InputService', [ '$http', function($http) {
			
	    this.getById = function(id) {
	        return $http({
	            method : 'GET',
	            url : '/api/field/input/' + id
	        });
	    },
	    this.addInputField = function(inputField) {
	        return $http.post(
	        		'/api/field/input',
	        		inputField
	        );
	    }
	 }]);
})();