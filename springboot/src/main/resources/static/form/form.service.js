(function() {
	'use strict';
	 angular.module('springboot').service('FormService', [ '$http', '$resource', '$log', function($http, $resource, $log) {
			
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
	    this.uploadFile = function(data) {
	        return $http.post(
	        		'api/attachments',
	        		data,
	        		{
	                    transformRequest: angular.identity,
	                    headers: {'Content-Type': undefined}
	             }
	        );
	    },
	    this.uploadFiles = function(data) {
	    		$log.log(data);
	        return $http.post(
	        		'api/attachments/multiple',
	        		data,
	        		{
	                    transformRequest: angular.identity,
	                    headers: {'Content-Type': undefined}
	             }
	        );
	    },
	    this.uploadAttachment = function(data) {
    		$log.log(data);
	        return $http.post(
        		'api/attachments/save',
        		data,
        		{
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
        		}
	        );
	    },
	    this.uploadAttachments = function(data) {
    		$log.log(data);
	        return $http.post(
        		'api/attachments/save-list',
        		data,
        		{
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
        		}
	        );
	    },
	    this.saveCandidate = function(data) {
    		$log.log(data);
	        return $http.post(
        		'api/attachments/candidate',
        		data,
        		{
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
        		}
	        );
	    }
	 }]);
})();