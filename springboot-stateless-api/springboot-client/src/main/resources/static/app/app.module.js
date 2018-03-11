(function() {
	'use strict';
	angular.module('client', [ 'ui.router', 'ngResource' ]);

	angular.module('client').config(function($httpProvider) {
		console.log("configuring $httpProvider....");
		$httpProvider.interceptors.push('TokenAuthInterceptor');
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
	});
	
	angular.module('client').run(function($http, $rootScope, $timeout, $log, $window) {
		$log.log("client run method");
	});

})();