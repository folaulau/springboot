(function() {
	'use strict';

	angular.module('springboot').controller('ValidationController', ValidationController);

	ValidationController.$inject = [ '$window','$scope', '$http', '$log', '$state', 'Validator'];

	function ValidationController($window, $scope, $http, $log, $state, Validator) {
		$log.log("Validation Controller");
		var validator = this;
		
		validator.pwdData = {};
		validator.passwordValidationFormStatus = false;
		validator.confirmPasswordStatus = false;
		
		init();
		
		function init(){
			
		}
		
		validator.submitPasswordValidation = function(){
			$log.log("submit password validation");
		}
		
		validator.validatePassword = function(password){
			$log.log("password, "+password+", confirmPassword: "+validator.pwdData.passwordConfirm);
			let status = true;
			
			if(Validator.validateLength(password)){
				$("#lengthRequirement").removeClass("passwordRequirementInvalid").addClass("passwordRequirementValid");
			}else{
				status = false;
				$("#lengthRequirement").removeClass("passwordRequirementValid").addClass("passwordRequirementInvalid");
			}
			
			if(Validator.validateContainNumber(password)){
				$("#numberRequirement").removeClass("passwordRequirementInvalid").addClass("passwordRequirementValid");
			}else{
				status = false;
				$("#numberRequirement").removeClass("passwordRequirementValid").addClass("passwordRequirementInvalid");
			}
			
			if(Validator.validateUppercaseLetter(password)){
				$("#uppercaseRequirement").removeClass("passwordRequirementInvalid").addClass("passwordRequirementValid");
			}else{
				status = false;
				$("#uppercaseRequirement").removeClass("passwordRequirementValid").addClass("passwordRequirementInvalid");
			}
			
			return status;
		}
		
		validator.validateConfirmPassword = function(passwordConfirm){
			$log.log("password, "+validator.pwdData.password+", confirmPassword: "+passwordConfirm);
			let status = true;
			if(Validator.arePasswordsTheSame(validator.pwdData.password, passwordConfirm)){
				validator.confirmPasswordStatus = false;
				
			}else{
				status = false;
				validator.confirmPasswordStatus = true;
			}
			return status;
		}
		
		validator.validate = function(){
			let pwdResult = validator.validatePassword(validator.pwdData.password);
			
			let confirmPwdResult = validator.validateConfirmPassword(validator.pwdData.passwordConfirm);
			
			validator.passwordValidationFormStatus = (pwdResult && confirmPwdResult);
			
		}
	}
	
	
	
	// directives
	  
	angular.module('springboot').directive("compareTo", [function() {
		return {
			require: "ngModel",
				scope: {
					otherModelValue: "=compareTo"
				},
				link: function(scope, element, attributes, ngModel) {
					ngModel.$validators.compareTo = function(modelValue) {
			          return modelValue == scope.otherModelValue;
			        };

			        scope.$watch("otherModelValue", function() {
			          ngModel.$validate();
			        });
				}
		    };
	}]);
})();
