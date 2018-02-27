(function() {
	'use strict';
	 angular.module('springboot').service('FormService', [ '$http', function($http) {

		this.getById = function(id) {
	        return $http({
	            method : 'GET',
	            url : '/api/field/form/' + id
	        });
	    },
	    this.add = function(form) {
	        return $http.post(
	        		'/api/field/form',
	        		form
	        );
	    }
	 }]);
})();