(function() {
	'use strict';

	angular.module('springboot').controller('FormController', FormController);

	FormController.$inject = [ '$window','$scope', '$http', '$log', '$state', 'FormService'];

	function FormController($window, $scope, $http, $log, $state, FormService) {
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
		
		//===============upload file to memory============
		var file = document.getElementById('file');
		file.addEventListener('change', function(e) {
			$log.log("uploading file");
			var file = e.target.files[0];
	        	var reader = new FileReader();
	    		reader.readAsDataURL(file);
			reader.onload = function(event) {
				$log.log("file uploaded "+file.name);
				form.files.push(file);
				$scope.$apply();
			}
		});
		
		var files = document.getElementById('files');
		files.addEventListener('change', function(e) {
			$log.log("uploading file");
	        for(var i=0;i<e.target.files.length;i++){
	        		var file = loadFile(e.target.files[i]);
	        }
	    });
		
		function loadFile(file){
			var reader = new FileReader();
	    		reader.readAsDataURL(file);
			reader.onload = function(event) {
				$log.log("file loaded "+file.name);
				form.files.push(file);
				$scope.$apply();
			}
		}
		
		function addToAttachments(attachment)
		{
			if(form.attachments==null || form.attachments.length>0){
				form.attachments = [];
			}
			
			form.attachments.push(attachment);
		}
		
		function addListToAttachments(attachments)
		{
			if(form.attachments==null || form.attachments.length>0){
				form.attachments = [];
			}
			for(var i=0;i<attachments.length;i++){
				form.attachments.push(attachments[i]);
			}
			
		}
		
		form.uploadFile = function(){
			var formData = new FormData();
            formData.append('file', form.files[0]);
            
			FormService.uploadFile(formData)
			.then(function(result){
				$log.log("success");
				$log.log(result);
				addToAttachments(result.data);
			}).catch(function(error){
				$log.log("error");
				$log.log(error);
			});
			
		}
		
		function printFiles(){
			for(var i=0;i<form.files.length;i++){
				$log.log(form.files[i]);
			}
		}
		
		form.uploadFiles = function(){
			var formData = new FormData();
			printFiles();
			
			for(var i=0;i<form.files.length;i++){
				//append() will append the new value onto the end of the existing set of values.
				formData.append('files', form.files[i]);
			}
            
            $log.log("number of files to upload: "+form.files.length);
            
			
            	FormService.uploadFiles(formData)
    			.then(function(result){
    				$log.log("success");
    				$log.log(result);
    				addListToAttachments(result.data);
    			}).catch(function(error){
    				$log.log("error");
    				$log.log(error);
    			});
		}
	}
})();
