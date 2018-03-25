(function() {
	'use strict';

	angular.module('springboot').controller('FormUploadDownloadController', FormUploadDownloadController);

	FormUploadDownloadController.$inject = [ '$window','$scope', '$http', '$log', '$state', 'FormService'];

	function FormUploadDownloadController($window, $scope, $http, $log, $state, FormService) {
		$log.log("form upload download controller");
		var form = this;
		form.data = {};
		form.currentUser = {};
		form.files = [];
		form.attachments = [];
		form.download = {};
		init();
		
		function init(){
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
		
		var candidateFile = document.getElementById('candidateFile');
		candidateFile.addEventListener('change', function(e) {
			$log.log("uploading candidate file");
	        for(var i=0;i<e.target.files.length;i++){
	        	var file = loadFile(e.target.files[i]);
	        }
	    });
		
		var attachment = document.getElementById('attachment');
		attachment.addEventListener('change', function(e) {
			$log.log("uploading attachment");
	        for(var i=0;i<e.target.files.length;i++){
	        	var file = loadFile(e.target.files[i]);
	        }
	    });
		var attachments = document.getElementById('attachments');
		attachments.addEventListener('change', function(e) {
			$log.log("uploading attachment");
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
			if(form.attachments==null || form.attachments.length==0){
				form.attachments = [];
			}
			
			form.attachments.push(attachment);
		}
		
		function addListToAttachments(attachments)
		{
			if(form.attachments==null || form.attachments.lengt==0){
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
			$log.log("print out files");
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
		
		form.uploadAttachment = function(){
			var formData = new FormData();
			printFiles();
			
			var user = {};
			user.name = "test name";
			user.age = 100;
			formData.append("file",form.files[0]);
			formData.append("user",angular.toJson(user));
			
            $log.log("save attachment");
            $log.log(formData);
            
        	FormService.uploadAttachment(formData)
			.then(function(result){
				$log.log("success");
				$log.log(result);
				addListToAttachments(result.data);
			}).catch(function(error){
				$log.log("error");
				$log.log(error);
			});
		}
		
		form.uploadAttachments = function(){
			var formData = new FormData();
			printFiles();
			
			var user = {};
			user.name = "test name";
			user.age = 100;
			formData.append("user",angular.toJson(user));
			
			for(var i=0;i<form.files.length;i++){
				formData.append('files', form.files[i]);
			}
			
            $log.log("save attachments");
            $log.log(formData);
            
        	FormService.uploadAttachments(formData)
			.then(function(result){
				$log.log("success");
				$log.log(result);
				addListToAttachments(result.data);
			}).catch(function(error){
				$log.log("error");
				$log.log(error);
			});
		}
		
		form.saveCandidate = function(){
			var formData = new FormData();
			printFiles();
			
			var candidate = {};
			candidate.name = "test name";
			candidate.file = form.files[0];
			
			formData.append("name","test name");
			if(form.files.length>0){
				formData.append("file",form.files[0]);
				formData.append("attachments",form.files[0]);
				formData.append("attachments",form.files[0]);
			}

			
            $log.log("save candidate");
            $log.log(formData);
            
        	FormService.saveMyCandidate(formData)
			.then(function(result){
				$log.log("success");
				$log.log(result);
				addToAttachments(result.data);
			}).catch(function(error){
				$log.log("error");
				$log.log(error);
			});
		}
		
		
		//===================Download================
		form.downloadOneFile = function(filename){
			$log.log("download one file: "+filename);
			if(filename!=null && filename.length>0){
				$window.open("/api/download/"+filename, '_blank');
			}else{
				$log.log("need filename");
			}
			
		}
	}
})();
