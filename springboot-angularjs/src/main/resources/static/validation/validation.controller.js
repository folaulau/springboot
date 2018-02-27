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
			$log.log("password, "+password+", confirmPassword: "+validator.pwdData.confirmPassword);
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
			
			if(Validator.hasSpecialCharacter(password)){
				$("#symbolRequirement").removeClass("passwordRequirementInvalid").addClass("passwordRequirementValid");
			}else{
				status = false;
				$("#symbolRequirement").removeClass("passwordRequirementValid").addClass("passwordRequirementInvalid");
			}
			
			$log.log("password status: "+status);
			$scope.passwordValidation['password'].$setValidity("invalidPassword", status);
			
		}
		
		validator.validateConfirmPassword = function(confirmPassword){
			$log.log("password, "+validator.pwdData.password+", confirmPassword: "+confirmPassword);
			let status = true;
			
			if(Validator.arePasswordsTheSame(validator.pwdData.password, validator.pwdData.confirmPassword)==false){
				status = false;
			}
			
			$log.log("confirm password status: "+status);
			$scope.passwordValidation['confirmPassword'].$setValidity("invalidConfirmPassword", status);
			
		}
		
	}
	
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
