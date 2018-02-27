(function() {
    'use strict';
    angular.module('springboot', ['ui.router', 'ngResource', 'ngMessages']);
    
    angular.module('springboot').run(function($rootScope, $transitions, $timeout, $log, $location, $window) {
    	// test
    	$transitions.onSuccess({},function(transition){
    		$log.log("transition success");
    		$log.log("to "+transition.to().name);
    		$log.log("from "+transition.from().name);
    	});
    });
    
})();