(function() {
	'use strict';
	 angular.module('springboot').service('CheckboxWithInputService', [ '$http', function($http) {
		
	    this.addFieldWithInput = function(field) {
	        return $http.post(
	        		'/api/field/checkbox-with-input',
	        		field
	        );
	    }
	 }]);
})();