(function() {
	'use strict';
	 angular.module('springboot').service('RadioBtnWithTextareaService', [ '$http', function($http) {
			
	    this.get = function(id) {
	        return $http({
	            method : 'GET',
	            url : '/api/field/radio' + id
	        });
	    },
	    this.add = function(field) {
	        return $http.post(
	        		'/api/field/radio-with-textarea',
	        		field
	        );
	    }
	 }]);
})();