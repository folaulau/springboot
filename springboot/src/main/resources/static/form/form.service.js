(function() {
	'use strict';
	angular.module('ttaps')
	.factory('FormService', FormService);

	FormService.$inject = [ '$resource', '$log' ];
	

	function FormService($resource, $log) {
		var resourceUrl = '/api/forms/';

		return $resource(resourceUrl, {}, {
			'query' : {
				method : 'GET',
				isArray : true
			},
			'get' : {
				method : 'GET',
				url: "/api/jobs/:id",
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'getCurrent' : {
				method : 'GET',
				url: "/api/users/current",
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'getCurrentAgentInSession' : {
				method : 'GET',
				url: "/api/agent/current",
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
			'getCategories' : {
				method : 'GET',
				isArray: true,
				url: "/api/jobs/category",
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'getJobs' : {
				method : 'GET',
				isArray: true,
				url: "/api/jobs/titles/:category",
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
			'resetPwd' : {
				method : 'PUT',
				url: 'api/agent/resetpwd',
				transformRequest : function(data) {
					return angular.toJson(data);
				}
			},
			'resendTempPwd' : {
				method : 'PUT',
				url: 'api/agent/resendtemppwd',
				transformRequest : function(data) {
					return angular.toJson(data);
				}
			},
			'resetCurrentAgentPwd' : {
				method : 'PUT',
				url: 'api/resetpwd/current',
				transformRequest : function(data) {
					return angular.toJson(data);
				}
			},
			'create' : {
				method : 'POST',
				transformRequest : function(data) {
					return angular.toJson(data);
				}
			},
			'deactivate' : {
				method : 'DELETE',
				url: 'api/users/deactivate/:id',
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			},
			'activate' : {
				method : 'PUT',
				url: 'api/users/activate',
				transformResponse : function(data) {
					if (data) {
						data = angular.fromJson(data);
					}
					return data;
				}
			}
		});
	}
})();