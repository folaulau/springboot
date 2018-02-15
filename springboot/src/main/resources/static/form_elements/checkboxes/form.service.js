(function() {
	'use strict';
	 angular.module('springboot').service('CheckboxService', [ '$http', function($http) {
			
		 this.getById = function(id) {
	        return $http({
	            method : 'GET',
	            url : '/api/field/checkbox/' + id
	        });
	    },
	    this.addField = function(field) {
	        return $http.post(
	        		'/api/field/checkbox',
	        		field
	        );
	    }
	 }]);
})();