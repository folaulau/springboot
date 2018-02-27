(function() {
	'use strict';
	 angular.module('springboot').service('Validator', ['$log', function($log) {
		const passwordLength = 10;
		const numberContainRegex = /\d/;
		const uppercaseContainRegex = /[A-Z]/;
		const specialCharacterRegex = /[$-/:-?{-~!"^_`\[\]]/;
	 
		//================= private functions ====================
		function hasNumber(variable) {
			return numberContainRegex.test(variable);
		}
		
		function hasUppercase(variable) {
			return uppercaseContainRegex.test(variable);
		}
		
		function hasSpecialCharacter(variable) {
			return specialCharacterRegex.test(variable);
		}
		
		
		//================= public functions ====================
	    this.validateLength = function(variable) {
	    		return variable!=null && variable.trim().length >= passwordLength;
	    },
	    
	    this.validateContainNumber = function(variable) {
	    		return hasNumber(variable);
	    },
	    
	    this.validateUppercaseLetter = function(variable) {
	    		return hasUppercase(variable);
	    },
	    
	    this.arePasswordsTheSame = function(password, confirmPassword) {
	    		return password == confirmPassword;
	    },
	    
	    this.hasSpecialCharacter = function(variable) {
	    		return hasSpecialCharacter(variable);
	    }
	    
	 }]);
})();