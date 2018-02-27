(function() {
	'use strict';
	 angular.module('springboot').service('TextareaService', [ '$http', function($http) {
			
	    this.getById = function(id) {
	        return $http({
	            method : 'GET',
	            url : '/api/field/textarea/' + id
	        });
	    },
	    this.addField = function(textareaField) {
	        return $http.post(
	        		'/api/field/textarea',
	        		textareaField
	        );
	    }
	 }]);
})();