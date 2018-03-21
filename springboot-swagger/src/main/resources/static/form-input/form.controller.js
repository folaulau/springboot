(function() {
	'use strict';

	angular.module('springboot').controller('FormInputController', FormInputController);

	FormInputController.$inject = [ '$window','$scope', '$http', '$log', '$state', 'FormInputService'];

	function FormInputController($window, $scope, $http, $log, $state, FormInputService) {
		$log.log("form controller");
		var form = this;
		form.data = {};
		form.data.address = {};
		form.currentUser = {};
		form.files = [];
		init();
		
		function init(){
			get(2);
			
			form.data.name = "laulau";
			form.data.age = 5;
			form.data.address.street = "111th st";
			form.data.address.state = "CA";
			
		}
		
		function get(uid){
			$log.log("get user");
			
			FormInputService.getUser(5).then(
				function(user){
					$log.log("success");
					$log.log(user);
					form.currentUser = user.data;
				}).catch(function(error){
					$log.log("error");
					$log.log(error);
			});
			
		}
		
		var file = document.getElementById('file');
		file.addEventListener('change', function(e) {
			$log.log("uploading file");
			var file = e.target.files[0];
	        	var reader = new FileReader();
	    		reader.readAsDataURL(file);
			reader.onload = function(event) {
				$log.log("file uploaded "+file.name);
				//form.files.push(file);
				form.data.file = file;
				$scope.$apply();
			}
		});
		
		form.submit = function(){
			$log.log("submit form");
			$log.log(form.data);
			var formData = new FormData();
			formData.append("user",angular.toJson(form.data));
			formData.append("file",form.data.file);
			//formData.append("age",form.data.age);
			//formData.append("address",form.data.address);
			
			FormInputService.addUserModel(formData)
			.then(function(user){
					$log.log("success");
					$log.log(user);
					//form.currentUser = user.data;
				}).catch(function(error){
					$log.log("error");
					$log.log(error);
			});
		}
		
		
		
	}
})();
