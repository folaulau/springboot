(function() {
    'use strict';
    angular.module('springboot', ['ui.router','ngAnimate', 'ui.bootstrap', 'ngResource', 'ngMessages']);
    
    angular.module('springboot').run(function($rootScope, $transitions, $timeout, $log, $location, $window) {
	    	$log.log("form elements");
    	
    		// test
	    	$transitions.onSuccess({},function(transition){
	    		$log.log("transition success");
	    		$log.log("to "+transition.to().name);
	    		$log.log("from "+transition.from().name);
	    	});
    });
    
})();