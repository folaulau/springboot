(function() {
	'use strict';

	angular.module('springboot').controller('FormInputController', FormInputController);

	FormInputController.$inject = [ '$window','$scope', '$http', '$log', '$state', 'FormService'];

	function FormInputController($window, $scope, $http, $log, $state, FormService) {
		$log.log("form controller");
		var form = this;
		form.data = {};
		form.currentUser = {};
		form.files = [];
		form.attachments = [];
		init();
		
		function init(){
			get(2);
		}
		
		function get(uid){
			$log.log("get user");
			
			FormService.getResourceUser(uid).$promise.then(function(data){
				$log.log("resource data");
				$log.log(data);
				form.currentUser = data;
			}).catch(function(error){
				$log.log("resource error");
				$log.log(error);
			});
			//$log.log(form.currentUser);
			
			FormService.getUser(5).then(
				function(user){
					$log.log("success");
					$log.log(user);
					form.currentUser = user.data;
				}).catch(function(error){
					$log.log("error");
					$log.log(error);
			});
			
		}
		
		
	}
})();
