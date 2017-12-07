(function() {
	'use strict';
	 angular.module('springboot').service('FormService', [ '$http', function($http) {
			
	    this.getUser = function(userId) {
	        return $http({
	            method : 'GET',
	            url : 'api/users/' + userId
	        });
	    },
	    this.addUser = function(params, user) {
	        return $http.post(
	        		'api/users?'+params,
	        		user
	        );
	    },
	    this.uploadFile = function(params, user) {
	        return $http.post(
	        		'api/users?'+params,
	        		user
	        );
	    }
	 }]);
})();