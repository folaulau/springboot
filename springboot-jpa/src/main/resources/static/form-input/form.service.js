(function() {
	'use strict';
	 angular.module('springboot').service('FormInputService', [ '$http', '$resource', '$log', function($http, $resource, $log) {
		//=====================$resource setup===============
		
	    this.getUser = function(userId) {
	        return $http({
	            method : 'GET',
	            url : 'api/users/' + userId
	        });
	    },
	    this.addUserModel = function(data) {
	        return $http.post(
	        		'api/users/model',
	        		data,
	        		{
	        			transformRequest: angular.identity,
	        			headers: {'Content-Type': undefined}
	             }
	        );
	    }
	    
	 }]);
})();