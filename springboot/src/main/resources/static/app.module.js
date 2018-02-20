(function() {
    'use strict';
    angular.module('springboot', ['ui.router', 'ngMessages']);
    
    angular.module('springboot').run(function($rootScope, $transitions, $timeout, $log, $location, $window) {
    	// test
    	$transitions.onSuccess({},function(transition){
    		$log.log("transition success");
    		$log.log("to "+transition.to().name);
    		$log.log("from "+transition.from().name);
    	});
    }).controller('formController', function($scope) {
        
        // we will store all of our form data in this object
        $scope.formData = {};
        
        // function to process the form
        $scope.processForm = function() {
            alert('awesome!');  
        };
        
    });
    
})();