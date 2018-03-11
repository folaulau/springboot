(function() {
	'use strict';
	angular.module('client')
	.factory('User', User);

	User.$inject = [ '$resource', '$log' ,'TokenStorage' ];
	

	function User($resource, $log, TokenStorage) {
		var host = 'http://localhost:8080';
		var resourceUrl = host+'/api/users/';

		return $resource(resourceUrl, {}, {
			'query' : {
				method : 'GET',
				isArray : true
			},
			'getAdmin' : {
				method : 'GET',
				url: host+'/api/users/admin',
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'getUser' : {
				method : 'GET',
				url: host+'/api/users/',
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'getPublic' : {
				method : 'GET',
				url: host+'/api/public',
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'login' : {
				method : 'POST',
				url: host+'/api/login',
				transformRequest : function(data) {
					return angular.toJson(data);
				}
			}
		});
	}
})();