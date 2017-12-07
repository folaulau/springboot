(function() {
	'use strict';

	angular.module('springboot').controller('FormController', FormController);

	FormController.$inject = [ '$window','$scope', '$http', '$log', '$state', 'FormService'];

	function FormController($window, $scope, $http, $log, $state, FormService) {
		$log.log("form controller");
		var form = this;
		form.data = {};
		form.currentUser = {};
		
		init();
		
		function init(){
			get(2);
		}
		
		function get(uid){
			
			FormService.getUser(uid).then(
				function(user){
					$log.log("success");
					$log.log(user);
					form.currentUser = user.data;
				}).catch(function(error){
					$log.log("error");
					$log.log(error);
			});
			
		}
		
		form.submit = function(){
			var params = "name="+form.data.name+"&age="+form.data.age;
			FormService.addUser(params, form.data).then(
				function(user){
					$log.log("success");
					$log.log(user);
					form.currentUser = user.data;
				}).catch(function(error){
					$log.log("error");
					$log.log(error);
				}
			);
		}
		
		form.upload = function(){
			FormService.uploadFile()
		}
	}
})();
