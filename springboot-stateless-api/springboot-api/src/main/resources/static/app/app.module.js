(function() {
	'use strict';
	angular.module('client', [ 'ui.router', 'ngResource' ]);

	angular.module('client').config(function($httpProvider) {
		console.log("configuring $httpProvider....");
		$httpProvider.interceptors.push('TokenAuthInterceptor');
		$httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
	});
	
	angular.module('client').run(function($rootScope, $timeout, $log, $window) {
		$log.log("client run method");
	});

})();