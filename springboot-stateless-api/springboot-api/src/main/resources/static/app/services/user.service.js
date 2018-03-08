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
			'get' : {
				method : 'GET',
				url: host+"/api/users/:id",
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'getCurrent' : {
				method : 'GET',
				url: "/api/users/current/ssn",
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'getCurrentUserData' : {
				method : 'GET',
				url: "/api/users/current/:id",
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'getAll' : {
				method : 'GET',
				isArray: true,
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'updateCurrent' : {
				method : 'PUT',
				url: 'api/agent/current',
				transformRequest : function(data) {
					return angular.toJson(data);
				}
			},
			'update' : {
				method : 'PUT',
				transformRequest : function(data) {
					return angular.toJson(data);
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